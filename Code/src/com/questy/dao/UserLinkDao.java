package com.questy.dao;

import com.questy.domain.UserLink;
import com.questy.enums.UserLinkDirectionEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserLinkDao extends ParentDao {

    public static Integer countByNetworkIdAndFromUserIdAndNotDirection(
            Connection conn,
            Integer networkId,
            Integer userId,
            UserLinkDirectionEnum notDirection) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_links` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `direction` != ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, notDirection.getId());
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }


    public static List<UserLink> getByNetworkIdAndFromUserIdAndNotDirection (
            Connection conn,
            Integer networkId,
            Integer userId,
            UserLinkDirectionEnum direction,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_links` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `direction` != ? " +
            "order by `created_on` desc, `id` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, direction.getId());
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<UserLink> out = new ArrayList<UserLink>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndFromUserIdAndCreatedAfterAndDirection(
            Connection conn,
            Integer networkId,
            Integer userId,
            java.util.Date createdAfter,
            UserLinkDirectionEnum notDirection) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_links` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `created_on` >= ? " +
            "and `direction` = ? " +
            "limit 1;";

        // Converting to sql timestamp
        Timestamp createdAfterSql = new Timestamp(createdAfter.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setTimestamp(3, createdAfterSql);
        ps.setInt(4, notDirection.getId());
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }






    public static List<UserLink> getByNetworkIdAndFromUserId (
            Connection conn,
            Integer networkId,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_links` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "order by `created_on` desc, `id` desc;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();

        List<UserLink> out = new ArrayList<UserLink>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static UserLink getByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_links` " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ResultSet rs = ps.executeQuery();

        UserLink out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static void updateDirectionByNetworkIdAndFromUserIdAndToUserId (
            Connection conn,
            UserLinkDirectionEnum direction,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_links` " +
            "set `direction` = ? " +
            "where `network_id` = ? " +
            "and `from_user_id` = ? " +
            "and `to_user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, direction.getId());
        ps.setInt(2, networkId);
        ps.setInt(3, fromUserId);
        ps.setInt(4, toUserId);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer fromUserId,
            Integer toUserId,
            UserLinkDirectionEnum direction) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_links` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`from_user_id`, " +
            "`to_user_id`, " +
            "`direction`" +
            ") values (NOW(), ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, fromUserId);
        ps.setInt(3, toUserId);
        ps.setInt(4, direction.getId());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserLink loadPrimitives (ResultSet rs) throws SQLException {
        UserLink out = new UserLink();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setFromUserId(DatabaseUtils.getInt(rs, "from_user_id"));
        out.setToUserId(DatabaseUtils.getInt(rs, "to_user_id"));
        out.setDirection(
            UserLinkDirectionEnum.getById(DatabaseUtils.getInt(rs, "direction"))
        );
        return out;
    }

}
