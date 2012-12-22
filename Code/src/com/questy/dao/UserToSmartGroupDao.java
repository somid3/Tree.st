package com.questy.dao;

import com.questy.domain.UserToSmartGroup;
import com.questy.enums.UserToSmartGroupStateEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserToSmartGroupDao extends ParentDao {


    public static List<UserToSmartGroup> getActiveByNetworkIdAndSmartGroupRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and (`member` is true or `state` = ?) " +
            "order by `state` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, UserToSmartGroupStateEnum.FAVORITE.getId());
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getByNetworkIdAndUserId (
            Connection conn,
            Integer networkId,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getActiveByNetworkIdAndUserId (
            Connection conn,
            Integer networkId,
            Integer userId,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and (`member` is true or `state` = ?) " +
            "order by `state` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, UserToSmartGroupStateEnum.FAVORITE.getId());
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getByNetworkIdAndUserIdAndState (
            Connection conn,
            Integer networkId,
            Integer userId,
            UserToSmartGroupStateEnum state,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `state` = ? " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, state.getId());
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndUserIdAndState (
            Connection conn,
            Integer networkId,
            Integer userId,
            UserToSmartGroupStateEnum state) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `state` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, state.getId());
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndUserIdAndMember (
                Connection conn,
                Integer networkId,
                Integer userId,
                Boolean member) throws SQLException {

            conn = start(conn);

            String sql =
                "select count(*) as `count` " +
                "from `user_to_smart_groups` " +
                "where `network_id` = ? " +
                "and `user_id` = ? " +
                "and `member` = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, networkId);
            ps.setInt(2, userId);
            ps.setBoolean(3, member);
            ResultSet rs = ps.executeQuery();

            Integer out = null;

            while (rs.next())
                out = DatabaseUtils.getInt(rs, "count");

            end(conn, ps, rs);
            return out;
        }

    public static UserToSmartGroup getByNetworkIdAndSmartGroupRefAndUserId (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, userId);

        ResultSet rs = ps.executeQuery();

        UserToSmartGroup out = null;
        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getMembersByNetworkIdAndCreatedAfter (
            Connection conn,
            Integer networkId,
            java.util.Date updatedAfter) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `member` is true " +
            "and `created_on` >= ?;";

        // Converting to sql timestamp
        Timestamp updatedAfterSql = new Timestamp(updatedAfter.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setTimestamp(2, updatedAfterSql);

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getMembersByNetworkIdAndUserIdAndCreatedAfter(
            Connection conn,
            Integer networkId,
            Integer userId,
            java.util.Date updatedAfter) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `member` is true " +
            "and `created_on` >= ?;";

        // Converting to sql timestamp
        Timestamp updatedAfterSql = new Timestamp(updatedAfter.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setTimestamp(3, updatedAfterSql);

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<UserToSmartGroup> getMembersByNetworkIdAndUserId (
            Connection conn,
            Integer networkId,
            Integer userId,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `member` is true " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());

        ResultSet rs = ps.executeQuery();

        List<UserToSmartGroup> out = new ArrayList<UserToSmartGroup>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    public static void updateStateByNetworkIdAndSmartGroupRefAndUserId(
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId,
            UserToSmartGroupStateEnum state) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_to_smart_groups` " +
            "set `state` = ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, state.getId());
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, userId);
        ps.execute();

        end(conn, ps, null);
    }

    public static void updateMemberByNetworkIdAndSmartGroupRef(Connection conn, Integer networkId, Integer smartGroupRef, Boolean member) throws SQLException {
        conn = start(conn);

        String sql =
         "update `user_to_smart_groups` " +
         "set `member` = ? " +
         "where `network_id` = ? " +
         "and `smart_group_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, member);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer updateMemberByNetworkIdAndSmartGroupRefAndUserId(Connection conn, Integer networkId, Integer smartGroupRef, Integer userId, Boolean member) throws SQLException {
        conn = start(conn);

        String sql =
            "update `user_to_smart_groups` " +
            "set `member` = ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, member);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, userId);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }



    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_to_smart_groups` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`smart_group_ref`, " +
            "`user_id` " +
            ") values (NOW(), ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, userId);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserToSmartGroup loadPrimitives (ResultSet rs) throws SQLException {
        UserToSmartGroup out = new UserToSmartGroup();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setSmartGroupRef(DatabaseUtils.getInt(rs, "smart_group_ref"));
        out.setState(UserToSmartGroupStateEnum.getByValue(DatabaseUtils.getInt(rs, "state")));
        out.setMember(DatabaseUtils.getBoolean(rs, "member"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));

        return out;
    }

}
