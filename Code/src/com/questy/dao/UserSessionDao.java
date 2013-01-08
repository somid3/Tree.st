package com.questy.dao;

import com.questy.domain.UserSession;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSessionDao extends ParentDao {

    public static Integer updateDetailsByUserIdAndChecksum (
            Connection conn,
            Integer userId,
            String checksum,
            String httpAgent) throws SQLException {

        conn = start(conn);
        
        String sql =
            "update `user_sessions` " +
            "set `updates` = `updates` + 1, " +
            "`updated_on` = NOW(), " +
            "`http_agent` = ? " +
            "where `user_id` = ? " +
            "and `checksum` = ? " +
            "order by `updated_on` desc, `id` desc " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, httpAgent);
        ps.setInt(2, userId);
        ps.setString(3, checksum);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static UserSession getByUserIdAndChecksum (
            Connection conn,
            Integer userId,
            String checksum) throws SQLException {

        conn = start(conn);
        
        String sql =
            "select * " +
            "from `user_sessions` " +
            "where `user_id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ResultSet rs = ps.executeQuery();

        UserSession out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer deleteByPersistentAndUpdatedBefore (
            Connection conn,
            Boolean persistent,
            java.util.Date updatedBefore) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `user_sessions` " +
            "where `persistent` = ? " +
            "and `updated_on` <= ?;";

        // Converting to sql timestamp
        Timestamp updatedBeforeSql = new Timestamp(updatedBefore.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, persistent);
        ps.setTimestamp(2, updatedBeforeSql);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert (
            Connection conn,
            Integer userId,
            String checksum,
            String httpAgent,
            Boolean persistent) throws SQLException {

        conn = start(conn);
        
        String sql =
            "insert into `user_sessions` (" +
            "`user_id`, " +
            "`checksum`, " +
            "`http_agent`, " +
            "`created_on`, " +
            "`updated_on`," +
            "`persistent`" +
            ") values (?, ?, ?, NOW(), NOW(), ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setString(2, checksum);
        ps.setString(3, httpAgent);
        ps.setBoolean(4, persistent);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserSession loadPrimitives (ResultSet rs) throws SQLException {
        UserSession out = new UserSession();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setChecksum(DatabaseUtils.getString(rs, "checksum"));
        out.setHttpAgent(DatabaseUtils.getString(rs, "http_agent"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setUpdatedOn(DatabaseUtils.getTimestamp(rs, "updated_on"));
        out.setPersistent(DatabaseUtils.getBoolean(rs, "persistent"));
        return out;
    }

}
