package com.questy.dao;

import com.questy.domain.SharedComment;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SharedCommentDao extends ParentDao {


    /**
     * Do not add the "hidden" on the where clause because this method is used to limit the user from adding
     * superfluous messages
     */
    public static Integer countByNetworkIdAndUserIdAndCreatedAfter (
        Connection conn,
        Integer networkId,
        Integer userId,
        java.util.Date createdAfter) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `shared_comments` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
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

    public static List<SharedComment> getByNetworkIdAndSmartGroupRefAndSharedItemRef(
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_comments` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `hidden` is not true " +
            "order by `created_on` asc, `id` asc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, sharedItemRef);
        ps.setInt(4, limit.getStartFrom());
        ps.setInt(5, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<SharedComment> out = new ArrayList<SharedComment>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static SharedComment getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            Integer sharedCommentRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_comments` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `ref` = ? " +
            "and `hidden` is not true;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, sharedItemRef);
        ps.setInt(4, sharedCommentRef);
        ResultSet rs = ps.executeQuery();

        SharedComment out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }


    public static Integer getMaxRefByNetworkIdAndSmartGroupRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`ref`) as `max_ref` " +
            "from `shared_comments` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ResultSet rs = ps.executeQuery();
        
        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static void incrementUpVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer ref,
        Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_comments` " +
            "set `up_votes` = `up_votes` + ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static void incrementDownVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer ref,
        Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_comments` " +
            "set `down_votes` = `down_votes` + ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
            Connection conn,
            Integer networkId,
            Integer userId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            Integer ref,
            String text) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `shared_comments` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`user_id`, " +
            "`smart_group_ref`, " +
            "`shared_item_ref`, " +
            "`ref`, " +
            "`text`" +
            ") values (NOW(), ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, ref);
        ps.setString(6, text);
        ps.execute();
        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static void updateHiddenByNetworkIdAndSmartGroupRefAndSharedItemIdAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer ref,
        Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_comments` " +
            "set `hidden` = ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `shared_item_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, sharedItemRef);
        ps.setInt(5, ref);
        ps.execute();

        end(conn, ps, null);
    }

    private static SharedComment loadPrimitives (ResultSet rs) throws SQLException {
        SharedComment out = new SharedComment();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setSmartGroupRef(DatabaseUtils.getInt(rs, "smart_group_ref"));
        out.setSharedItemRef(DatabaseUtils.getInt(rs, "shared_item_ref"));
        out.setSharedCommentRef(DatabaseUtils.getInt(rs, "ref"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setText(DatabaseUtils.getString(rs, "text"));
        out.setHidden(DatabaseUtils.getBoolean(rs, "hidden"));
        out.setUpVotes(DatabaseUtils.getInt(rs, "up_votes"));
        out.setDownVotes(DatabaseUtils.getInt(rs, "down_votes"));

        return out;
    }

}
