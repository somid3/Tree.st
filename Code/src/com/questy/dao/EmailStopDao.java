package com.questy.dao;

import com.questy.domain.EmailStop;
import com.questy.enums.GlobalEventEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;

public class EmailStopDao extends ParentDao {

    public static EmailStop getByUserIdAndEmailEvent(Connection conn, Integer userId, GlobalEventEnum event) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `email_stops` " +
            "where `user_id` = ? " +
            "and `event` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, event.getValue());
        ResultSet rs = ps.executeQuery();

        EmailStop out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer insert (
            Connection conn,
            Integer userId,
            GlobalEventEnum event) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `email_stops` (" +
            "`created_on`, " +
            "`user_id`, " +
            "`event`" +
            ") values (NOW(), ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, userId);
        ps.setInt(2, event.getValue());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static EmailStop loadPrimitives (ResultSet rs) throws SQLException {
        EmailStop out = new EmailStop();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setGlobalEvent(
            GlobalEventEnum.getByValue(DatabaseUtils.getInt(rs, "event"))
        );

        return out;
    }

}
