package com.questy.dao;

import com.questy.domain.EmailConfirmation;
import com.questy.domain.Network;
import com.questy.utils.DatabaseUtils;
import com.questy.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailConfirmationDao extends ParentDao {

    public static EmailConfirmation getByUserId (Connection conn, Integer userId) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `email_confirmations` " +
            "where `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        EmailConfirmation out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<EmailConfirmation> getAllNonConfirmed (Connection conn) throws SQLException {
            conn = start(conn);

            String sql =
                "select * " +
                "from `email_confirmations` " +
                "where `confirmed_on` is null;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        List<EmailConfirmation> out = new ArrayList<EmailConfirmation>();

            while (rs.next())
                out.add(loadPrimitives(rs));

            end(conn, ps, rs);
            return out;
        }

    public static EmailConfirmation getByUserIdAndChecksum (Connection conn, Integer userId, String checksum) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `email_confirmations` " +
            "where `user_id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ResultSet rs = ps.executeQuery();

        EmailConfirmation out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static void updateSentEmailDetails(Connection conn, Integer userId) throws SQLException {
        conn = start(conn);

        String sql =
            "update `email_confirmations` " +
            "set `message_count` = `message_count` + 1, " +
            "`last_sent_on` = NOW() " +
            "where `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.executeUpdate();

        end(conn, ps, null);
    }

    public static Integer updateConfirmedDetails(Connection conn, Integer userId, String checksum) throws SQLException {
        conn = start(conn);

        String sql =
            "update `email_confirmations` " +
            "set `confirmed_on` = NOW() " +
            "where `user_id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert(
            Connection conn,
            Integer userId) throws SQLException {

        conn = start(conn);

        String checksum = StringUtils.random(10);

        String sql =
            "insert into `email_confirmations` (" +
            "`user_id`, " +
            "`checksum` " +
            ") values (?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static EmailConfirmation loadPrimitives (ResultSet rs) throws SQLException {
        EmailConfirmation out = new EmailConfirmation();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setConfirmedOn(DatabaseUtils.getTimestamp(rs, "confirmed_on"));
        out.setChecksum(DatabaseUtils.getString(rs, "checksum"));
        out.setMessageCount(DatabaseUtils.getInt(rs, "message_count"));
        out.setLastSentOn(DatabaseUtils.getTimestamp(rs, "last_sent_on"));
        out.setChangeEmailTo(DatabaseUtils.getString(rs, "change_email_to"));
        return out;
    }

}
