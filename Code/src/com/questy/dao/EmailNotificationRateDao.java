package com.questy.dao;

import com.questy.domain.EmailNotificationRate;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.enums.NetworkEventEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;

@Deprecated
public class EmailNotificationRateDao extends ParentDao {

    public static EmailNotificationRate getByNetworkIdAndSmartGroupRefAndUserIdAndEvent (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId,
            NetworkEventEnum event) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `email_notification_rates` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `user_id` = ? " +
            "and `event` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, userId);
        ps.setInt(4, event.getId());
        ResultSet rs = ps.executeQuery();

        EmailNotificationRate out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static void updateRate(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer userId,
        NetworkEventEnum event,
        EmailNotificationRateEnum rate) throws SQLException {

        conn = start(conn);

        String sql =
         "update `email_notification_rates` " +
         "set `rate` = ? " +
         "where `network_id` = ? " +
         "and `smart_group_ref` = ? " +
         "and `user_id` = ? " +
         "and `event` = ? " +
         "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, rate.getId());
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, userId);
        ps.setInt(5, event.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId,
            NetworkEventEnum event,
            EmailNotificationRateEnum rate) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `email_notification_rates` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`smart_group_ref`, " +
            "`user_id`, " +
            "`event`, " +
            "`rate`" +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, userId);
        ps.setInt(4, event.getId());
        ps.setInt(5, rate.getId());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static EmailNotificationRate loadPrimitives (ResultSet rs) throws SQLException {
        EmailNotificationRate out = new EmailNotificationRate();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setSmartGroupId(DatabaseUtils.getInt(rs, "smart_group_ref"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setEvent(NetworkEventEnum.getById(DatabaseUtils.getInt(rs, "event")));
        out.setRate(EmailNotificationRateEnum.getById(DatabaseUtils.getInt(rs, "rate")));
        return out;
    }

}
