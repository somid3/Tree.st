package com.questy.dao;

import com.questy.domain.Network;
import com.questy.domain.PasswordReset;
import com.questy.utils.DatabaseUtils;
import com.questy.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordResetDao extends ParentDao {

    public static PasswordReset getById (Connection conn, Integer id) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `password_resets` " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        PasswordReset out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static PasswordReset getByUserIdAndChecksum (Connection conn, Integer userId, String checksum) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `password_resets` " +
            "where `user_id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ResultSet rs = ps.executeQuery();

        PasswordReset out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static void updateCompletedOnByUserIdAndChecksum (Connection conn, Integer userId, String checksum) throws SQLException {
        conn = start(conn);

        String sql =
         "update `password_reset` " +
         "set `completed_on` = NOW() " +
         "where `user_id` = ? " +
         "and `checksum` = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
            Connection conn,
            Integer userId) throws SQLException {

        conn = start(conn);

        String checksum = StringUtils.random(10);

        String sql =
            "insert into `password_resets` (" +
            "`created_on`, " +
            "`user_id`, " +
            "`checksum` " +
            ") values (NOW(), ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static PasswordReset loadPrimitives (ResultSet rs) throws SQLException {
        PasswordReset out = new PasswordReset();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setCompletedOn(DatabaseUtils.getTimestamp(rs, "completed_on"));
        out.setChecksum(DatabaseUtils.getString(rs, "checksum"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));

        return out;
    }

}
