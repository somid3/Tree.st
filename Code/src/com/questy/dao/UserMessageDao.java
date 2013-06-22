package com.questy.dao;

import com.questy.domain.UserLink;
import com.questy.domain.UserMessage;
import com.questy.domain.UserMessageGroup;
import com.questy.enums.UserLinkDirectionEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMessageDao extends ParentDao {

    public static List<UserMessage> getByNetworkIdAndBetweenUserIds (
        Connection conn,
        Integer networkId,
        Integer userId1,
        Integer userId2,
        SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_messages` " +
            "where `network_id` = ? " +
            "and ((`from_user_id` = ? and `to_user_id` = ?) or (`from_user_id` = ? and `to_user_id` = ?)) " +
            "order by `created_on` asc " +
            "limit ?, ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId1);
        ps.setInt(3, userId2);
        ps.setInt(4, userId2);
        ps.setInt(5, userId1);
        ps.setInt(6, limit.getStartFrom());
        ps.setInt(7, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<UserMessage> out = new ArrayList<UserMessage>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndFromUserIdAndCreatedAfter (
            Connection conn,
            Integer networkId,
            Integer userId,
            java.util.Date createdAfter) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_messages` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `created_on` >= ? " +
            "limit 1;";

        // Converting to sql timestamp
        Timestamp createdAfterSql = new Timestamp(createdAfter.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setTimestamp(3, createdAfterSql);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId,
            String message) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_messages` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`from_user_id`, " +
            "`to_user_id`, " +
            "`message`" +
            ") values (NOW(), ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ps.setString(4, message);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserMessage loadPrimitives (ResultSet rs) throws SQLException {
        UserMessage out = new UserMessage();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setFromUserId(DatabaseUtils.getInt(rs, "from_user_id"));
        out.setToUserId(DatabaseUtils.getInt(rs, "to_user_id"));
        out.setMessage(DatabaseUtils.getString(rs, "message"));
        return out;
    }

}
