package com.questy.dao;

import com.questy.domain.UserToNetwork;
import com.questy.enums.AllMembersViewEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserToNetworkDao extends ParentDao {

    public static List<UserToNetwork> getByNetworkIdAndLowestRoleOrderedByPoints(
            Connection conn,
            Integer networkId,
            RoleEnum lowestRole,
            AllMembersViewEnum orderBy,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql = null;

        switch (orderBy) {
            case BY_POINTS:
                sql = "select * from `users_to_networks` where `network_id` = ? and `role` >= ? and `removed_on` is null order by `current_points` desc, `id` desc limit ?, ?;";
                break;
            case BY_JOINED:
                sql = "select * from `users_to_networks` where `network_id` = ? and `role` >= ? and `removed_on` is null order by `created_on` desc, `id` desc limit ?, ?;";
                break;
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, lowestRole.getId());
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<UserToNetwork> out = new ArrayList<UserToNetwork>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToNetwork> getByUserIdAndLowestRole(
            Connection conn,
            Integer userId,
            RoleEnum lowestRole,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users_to_networks` " +
            "where `user_id` = ? " +
            "and `role` >= ? " +
            "and `removed_on` is null " +
            "order by `network_id` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, lowestRole.getId());
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<UserToNetwork> out = new ArrayList<UserToNetwork>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    public static UserToNetwork getByUserIdAndNetworkId(
            Connection conn,
            Integer userId,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `users_to_networks` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `removed_on` is null;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ResultSet rs = ps.executeQuery();

        UserToNetwork u = null;

        while (rs.next())
            u = loadPrimitives(rs);

        end(conn, ps, rs);
        return u;
    }


    public static Integer deleteByUserIdAndNetworkId(Connection conn, Integer userId, Integer networkId) throws SQLException  {
       conn = start(conn);

       String sql =
           "delete " +
           "from `users_to_networks` " +
           "where `network_id` = ? " +
           "and `user_id` = ?;";

       PreparedStatement ps = conn.prepareStatement(sql);
       ps.setInt(1, networkId);
       ps.setInt(2, userId);
       Integer out = ps.executeUpdate();

       end(conn, ps, null);
       return out;
   }

    public static Integer insert (
            Connection conn,
            Integer userId,
            Integer networkId,
            RoleEnum role) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `users_to_networks` (" +
            "`created_on`, " +
            "`user_id`, " +
            "`network_id`, " +
            "`role`" +
            ") values (NOW(), ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setDouble(3,  role.getId());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static void incrementPointsByUserIdAndNetworkId (
            Connection conn,
            Integer userId,
            Integer networkId,
            Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users_to_networks` " +
            "set `current_points` = `current_points` + ? " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setDouble(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void incrementSharedUpVotesByUserIdAndNetworkId (
            Connection conn,
            Integer userId,
            Integer networkId,
            Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users_to_networks` " +
            "set `shared_up_votes` = `shared_up_votes` + ? " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setDouble(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void incrementSharedDownVotesByUserIdAndNetworkId (
            Connection conn,
            Integer userId,
            Integer networkId,
            Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users_to_networks` " +
            "set `shared_down_votes` = `shared_down_votes` + ? " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setDouble(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updatePointsPerLink(
            Connection conn,
            Integer networkId,
            Integer userId,
            Integer pointsPerLink) throws SQLException {

        conn = start(conn);

        String sql =
            "update `users_to_networks` " +
            "set `points_per_link` = ? " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pointsPerLink);
        ps.setInt(2, networkId);
        ps.setInt(3, userId);
        ps.execute();

        end(conn, ps, null);
    }

    private static UserToNetwork loadPrimitives (ResultSet rs) throws SQLException {
        UserToNetwork out = new UserToNetwork();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setRemovedOn(DatabaseUtils.getTimestamp(rs, "removed_on"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));

        out.setCurrentPoints(DatabaseUtils.getInt(rs, "current_points"));
        out.setSharedUpVotes(DatabaseUtils.getInt(rs, "shared_up_votes"));
        out.setSharedDownVotes(DatabaseUtils.getInt(rs, "shared_down_votes"));

        out.setPointsPerLink(DatabaseUtils.getInt(rs, "points_per_link"));
        out.setRole(RoleEnum.getById(DatabaseUtils.getInt(rs, "role")));
        return out;
    }


}
