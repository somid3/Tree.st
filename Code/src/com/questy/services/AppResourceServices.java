package com.questy.services;

import com.questy.dao.AppResourceDao;
import com.questy.dao.UserDao;
import com.questy.domain.AppResource;
import com.questy.domain.User;
import com.questy.enums.AppEnum;
import com.questy.enums.AppResourceTypeEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.utils.FileUtils;
import com.questy.utils.ImageUtils;
import com.questy.utils.StringUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class AppResourceServices extends ParentService  {

    public static Integer saveTemporaryFace (
            Integer userId,
            HttpServletRequest request) throws SQLException, IOException {

        // Currently non-transactional
        Connection conn = null;

        /**
         * Disable all previous temporary resources. Temporary are those that were simply
         * uploaded, but were not confirmed by the user as uploaded. For example, when a user
         * uploads a photo it is denoted as temporary. When the user selects the face on the
         * photo, such photo is no longer temporary
         */
        AppResourceDao.updateHiddenByUserIdAndAppAndTemp(conn, userId, AppEnum.FACES, true, true);

        try {

            // Save temporary face file and create thumbnails
            return saveTemporaryFaceTypes(userId, request);
        } catch (Exception e) {
            throw new UIException(e.getMessage());
        }
    }

    public static void setFace (
            Integer userId,
            Integer ref,
            String resourceChecksum,
            Integer x1,
            Integer y1,
            Integer width,
            Integer height) throws SQLException, IOException {

        // Currently non-transactional
        Connection conn = null;



        /**
         * Image or file related service calls. These calls are done first so that if there are any
         * exceptions, the database calls will not have been executed
         */

        // Reading scaled image
        AppResource scaledResource = AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(conn, userId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, ref, resourceChecksum);
        String scaledFilePath = scaledResource.getFilePath();
        BufferedImage scaledImage = ImageIO.read(new File(scaledFilePath));

        // Reading uploaded image
        AppResource uploadedResource = AppResourceDao.getByUserIdAndAppAndTypeAndRef(conn, userId, AppEnum.FACES, AppResourceTypeEnum.FACE_UPLOADED, ref);
        String uploadedFilePath = uploadedResource.getFilePath();
        BufferedImage uploadedImage = ImageIO.read(new File(uploadedFilePath));

        // Calculating transformation ratios
        Tuple<BigDecimal, BigDecimal> transform = ImageUtils.getTransformationRatio(
                uploadedImage.getWidth(),
                uploadedImage.getHeight(),
                scaledImage.getWidth(),
                scaledImage.getHeight(),
                50);

        // Calculating coordinates of face from uploaded image
        x1 = new BigDecimal(x1).multiply(transform.getX()).intValue();
        width = new BigDecimal(width).multiply(transform.getX()).intValue();
        y1 = new BigDecimal(y1).multiply(transform.getY()).intValue();
        height = new BigDecimal(height).multiply(transform.getY()).intValue();

        // Making sure final width is not larger than uploaded image
        Integer totalWidth = x1 + width;
        if (totalWidth > uploadedImage.getWidth())
            width = width - (totalWidth - uploadedImage.getWidth());

        // Making sure final width is not larger than uploaded image
        Integer totalHeight = y1 + height;
        if (totalHeight > uploadedImage.getHeight())
            height = height - (totalHeight - uploadedImage.getHeight());

        // Cropping and resizing the face
        BufferedImage croppedImage = Scalr.crop(uploadedImage, x1, y1, width, height);
        BufferedImage faceImage = Scalr.resize(croppedImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, 160, Scalr.OP_ANTIALIAS);



        /**
         * Database related service calls
         */

        // Updating resource as no longer temporary
        AppResourceDao.updateTemporaryByUserIdAndAppAndRef(conn, userId, AppEnum.FACES, ref, false);

        // Disable all previous temporary resources
        AppResourceDao.updateHiddenByUserIdAndAppAndTemp(conn, userId, AppEnum.FACES, true, true);

        // Hide all other face types for the same ref
        AppResourceDao.updateHiddenByUserIdAndAppAndTypeAndRef(conn, userId, AppEnum.FACES, AppResourceTypeEnum.FACE, ref, true);

        // Saving new face resource to database
        AppResource faceResource = insertAndRetrieve(userId, AppEnum.FACES, AppResourceTypeEnum.FACE, ref, false);

        // Updating user to have the correct face ref
        UserDao.updateFaceByUserId(conn, userId, ref, faceResource.getUrl());



        /**
         * Saving image
         */
        String faceFilePath = faceResource.getFilePath();
        File faceFile = new File(faceFilePath);
        ImageIO.write(faceImage, "jpg", faceFile);

    }


    private static Integer saveTemporaryFaceTypes(Integer userId, HttpServletRequest request) throws SQLException, IOException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving next application resources reference
        Integer nextRef = AppResourceDao.getNextRefByUserIdAndApp(conn, userId, AppEnum.FACES);

            // Saving temporary in whatever format
            String tmpFilePath = null;
            {
                AppResource resource = insertAndRetrieve(userId, AppEnum.FACES, AppResourceTypeEnum.FACE_UPLOADED, nextRef, true);
                String filePath = resource.getFilePath();
                FileUtils.save(filePath, request);
                tmpFilePath = filePath;
            }

            // Saving as original in jpg format
            String originalFilePath = null;
            {
                AppResource resource = insertAndRetrieve(userId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL, nextRef, true);
                String filePath = resource.getFilePath();
                BufferedImage image = ImageIO.read(new File(tmpFilePath));
                File file = new File(filePath);
                ImageIO.write(image, "jpg", file);
                originalFilePath = filePath;
            }

            // Saving scaled in jpg format
            {
                AppResource resource = insertAndRetrieve(userId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, nextRef, true);
                String filePath = resource.getFilePath();
                BufferedImage image = ImageIO.read(new File(tmpFilePath));
                File file = new File(filePath);
                image = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 500, 500, Scalr.OP_ANTIALIAS);
                ImageIO.write(image, "jpg", file);
            }

        return nextRef;

    }

    /**
     * Creates a new user resource and returns it
     *
     * @throws SQLException
     */
    public static AppResource insertAndRetrieve (
        Integer userId,
        AppEnum application,
        AppResourceTypeEnum type,
        Integer ref,
        Boolean isTemporary) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        String checksum = StringUtils.random();

        // Inserting resource to database
        AppResourceDao.insert(conn, userId, application, type, isTemporary, ref, checksum);

        return AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(conn, userId, application, type, ref, checksum);
    }

    public static void hideByUserIdAndAppAndTypeAndRefAndChecksum (
            Integer userId,
            AppEnum application,
            AppResourceTypeEnum type,
            Integer ref,
            String checksum) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Attempting to retrieve face
        AppResource resource = AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(conn, userId, application, type, ref, checksum);

        if (resource != null) {

            // Hide the application resource
            AppResourceDao.updateHiddenByUserIdAndAppAndRef(conn, userId, application, ref, true);

            // Is this a user face?
            if (application.equals(AppEnum.FACES)) {


                // Check if this is the profile face of the user
                User user = UserDao.getById(conn, userId);

                if (user.getFaceRef().equals(ref)) {

                    // Yes, this is the user's profile face. Set user face to null
                    UserDao.updateFaceByUserId(null, userId, null, null);

                }

            }


        }

    }



}
