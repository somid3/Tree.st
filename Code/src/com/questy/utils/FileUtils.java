package com.questy.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;


public class FileUtils {

    public static void save (String filePath, HttpServletRequest request) {


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart)
            return;

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(Vars.uploadMaxFileSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(Vars.resourcesTempFilePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Maximum file size to be uploaded.
        upload.setSizeMax( Vars.uploadMaxFileSize);
        upload.setFileSizeMax( Vars.uploadMaxFileSize);

        try{

            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while ( i.hasNext () ) {

                FileItem fi = (FileItem)i.next();

                if ( !fi.isFormField () ) {

                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();

                    // Create file to be saved
                    File file = new File( filePath );

                    // Create directory structure needed
                    file.getParentFile().mkdirs();

                    // Save file to disk
                    fi.write( file ) ;

                }
            }

        } catch(Exception ex) {

            ex.printStackTrace();

        }

    }
}
