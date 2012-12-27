package com.questy.dao;

import com.questy.domain.User;
import com.questy.utils.DatabaseUtils;
import com.questy.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ParentDao {

    public static User getByEmail(
            Connection conn,
            String email) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `email` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        User out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static User getByEmailAndPasswordHash (
            Connection conn,
            String email,
            String passwordHash) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `email` = ? " +
            "and `password_hash` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, passwordHash);
        ResultSet rs = ps.executeQuery();

        User out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }



    public static User getById (
            Connection conn,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        User u = null;

        while (rs.next())
            u = loadPrimitives(rs);

        end(conn, ps, rs);
        return u;
    }

    public static List<User> findByNetworkIdAndName (
            Connection conn,
            Integer networkId,
            String searchFullName) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `id` in (select `user_id` from `users_to_networks` where `network_id` = ?) " +
            "and upper(concat(`first_name`, ' ', `last_name`, ' ', `email`)) like upper(?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setString(2, searchFullName);
        ResultSet rs = ps.executeQuery();

        List<User> out = new ArrayList<User>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static User getByIdAndChecksum (
            Connection conn,
            Integer userId,
            String checksum) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ResultSet rs = ps.executeQuery();

        User u = null;

        while (rs.next())
            u = loadPrimitives(rs);

        end(conn, ps, rs);
        return u;
    }

    public static User getByIdAndSaltChecksum (
            Connection conn,
            Integer userId,
            String saltChecksum) throws SQLException {

        // Ensuring salt checksum is long enough to avoid any hacks
        if (saltChecksum.length() < 15)
                throw new RuntimeException("Salt checksum must be greater than or equal to 15 characters");

        // Modifying salt checksum to do a regular expression search on the database
        saltChecksum = saltChecksum + '%';

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `id` = ? " +
            "and `password_salt` like ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, saltChecksum);
        ResultSet rs = ps.executeQuery();

        User u = null;

        while (rs.next())
            u = loadPrimitives(rs);

        end(conn, ps, rs);
        return u;
    }




    public static List<User> getAllFaceless (
            Connection conn) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users` " +
            "where `face_on` is null;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<User> u = new ArrayList<User>();

        while (rs.next())
            u.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return u;
    }

    public static void updateFaceByUserId (
            Connection conn,
            Integer userId,
            Integer faceRef,
            String faceUrl) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users` " +
            "set `face_url` = ?, " +
            "`face_ref` = ?, " +
            "`face_on` = NOW() " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);

        // Can be used to set the user face to null
        if (faceUrl == null || faceRef == null) {

            // Setting profile face details to null
            ps.setNull(1, Types.VARCHAR);
            ps.setInt(2, Types.INTEGER);

        } else {

            // Updating user face
            ps.setString(1, faceUrl);
            ps.setInt(2, faceRef);
        }

        ps.setInt(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updatePasswordByUserId (
            Connection conn,
            Integer userId,
            String passwordText) throws SQLException {

        conn = start(conn);

        // Creating the password salt
        String passwordSalt = StringUtils.random(256);

        // Hashing the password
        String passwordHash = hashPassword(passwordText, passwordSalt);

        String sql =
            "update `users` " +
            "set `password_hash` = ?, " +
            "`password_salt` = ? " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, passwordHash);
        ps.setString(2, passwordSalt);
        ps.setInt(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateNameByUserId (
            Connection conn,
            Integer userId,
            String firstName,
            String lastName) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users` " +
            "set `first_name` = ?, " +
            "`last_name` = ? " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setInt(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateEmailByUserId (
            Connection conn,
            Integer userId,
            String email) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users` " +
            "set `email` = ? " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setInt(2, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
            Connection conn,
            String email,
            String passwordText,
            String firstName,
            String lastName) throws SQLException {

        conn = start(conn);

        // Creating the password salt
        String passwordSalt = StringUtils.random(256);

        // Hashing the password
        String passwordHash = hashPassword(passwordText, passwordSalt);

        // Creating the user checksum
        String checksum = StringUtils.random();

        String sql =
            "insert into `users` (" +
            "`created_on`, " +
            "`email`, " +
            "`password_hash`, " +
            "`password_salt`, " +
            "`first_name`, " +
            "`last_name`, " +
            "`checksum`" +
            ") values (NOW(), ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, email.trim());
        ps.setString(2, passwordHash);
        ps.setString(3, passwordSalt);
        ps.setString(4, firstName.trim());
        ps.setString(5, lastName.trim());
        ps.setString(6, checksum);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }



    private static User loadPrimitives (ResultSet rs) throws SQLException {
        User out = new User();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setEmail(DatabaseUtils.getString(rs, "email"));
        out.setPasswordHash(DatabaseUtils.getString(rs, "password_hash"));
        out.setPasswordSalt(DatabaseUtils.getString(rs, "password_salt"));
        out.setFirstName(DatabaseUtils.getString(rs, "first_name"));
        out.setLastName(DatabaseUtils.getString(rs, "last_name"));
        out.setFaceUrl(DatabaseUtils.getString(rs, "face_url"));
        out.setFaceOn(DatabaseUtils.getTimestamp(rs, "face_on"));
        out.setFaceRef(DatabaseUtils.getInt(rs, "face_ref"));
        return out;
    }

    /**
     * DO NOT MODIFY THIS METHOD OR ELSE USERS WILL NOT BE ABLE TO LOG
     * IN UNLESS THEY RESET THEIR PASSWORDS
     */
    public static String hashPassword (
            String passwordText,
            String passwordSalt) {

        final String charset = "UTF-8";
        final int iterations = 100000;
        byte[] input = null;

        try {

            // Retrieving SHA-512 hashing algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();

            // Adding salt to first hash
            digest.update(passwordSalt.getBytes(charset));

            // Doing the first hash
            input = digest.digest(passwordText.getBytes(charset));

            // Hashing multiples, this should take 100 milliseconds
            for (int i = 0; i < iterations; i++) {

                digest.reset();
                input = digest.digest(input);

            }

            // Return the hash if all went well
            return Base64.encodeBase64String(input);

        } catch ( Exception e) {

            e.printStackTrace();
            System.exit(1);

        }

        return null;
    }


}
