package com.questy.dao;

import com.questy.domain.UserMessageGroup;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMessageGroupDao extends ParentDao {

    public static UserMessageGroup getByNetworkIdAndFromUserIdAndToUserId(
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_message_groups` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ResultSet rs = ps.executeQuery();

        UserMessageGroup out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<UserMessageGroup> getByNetworkIdAndFromUserId (
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_message_groups` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "order by `updated_on` desc " +
            "limit ?, ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<UserMessageGroup> out = new ArrayList<UserMessageGroup>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static void updateReadByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            Boolean read,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_message_groups` " +
            "set `read` = ? " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, read);
        ps.setInt(2, networkId);
        ps.setInt(3, fromUserId);
        ps.setInt(4, toUserId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateSummaryByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            String summary,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_message_groups` " +
            "set `summary` = ? " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, summary);
        ps.setInt(2, networkId);
        ps.setInt(3, fromUserId);
        ps.setInt(4, toUserId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateUpdatedOnByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_message_groups` " +
            "set `updated_on` = NOW() " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateRepliedByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            Boolean replied,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_message_groups` " +
            "set `replied` = ? " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, replied);
        ps.setInt(2, networkId);
        ps.setInt(3, fromUserId);
        ps.setInt(4, toUserId);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer countByNetworkIdAndFromUserId (
            Connection conn,
            Integer networkId,
            Integer fromUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_message_groups` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer countUnReadByNetworkIdAndFromUserId (
        Connection conn,
        Integer networkId,
        Integer fromUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_message_groups` " +
            "where `read` = false " +
            "and `network_id` = ? " +
            "and `from_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
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
            Boolean read,
            Boolean replied) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_message_groups` (" +
            "`updated_on`, " +
            "`network_id`, " +
            "`from_user_id`, " +
            "`to_user_id`, " +
            "`read`, " +
            "`replied`" +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ps.setBoolean(4, read);
        ps.setBoolean(5, replied);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserMessageGroup loadPrimitives (ResultSet rs) throws SQLException {
        UserMessageGroup out = new UserMessageGroup();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUpdatedOn(DatabaseUtils.getTimestamp(rs, "updated_on"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setFromUserId(DatabaseUtils.getInt(rs, "from_user_id"));
        out.setToUserId(DatabaseUtils.getInt(rs, "to_user_id"));
        out.setRead(DatabaseUtils.getBoolean(rs, "read"));
        out.setReplied(DatabaseUtils.getBoolean(rs, "replied"));
        out.setSummary(DatabaseUtils.getString(rs, "summary"));
        return out;
    }

}
