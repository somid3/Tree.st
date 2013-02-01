package com.questy.dao;

import com.questy.domain.SharedItem;
import com.questy.domain.SmartGroup;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SharedItemDao extends ParentDao {

    public static List<SharedItem> getByNetworkId (
        Connection conn,
        Integer networkId,
        SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `hidden` is not true " +
            "order by `created_on` desc, `id` desc " +
            "limit ?, ?;";


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, limit.getStartFrom());
        ps.setInt(3, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<SharedItem> out = new ArrayList<SharedItem>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkId (
        Connection conn,
        Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `hidden` is not true;";


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndSmartGroupRefAndCreatedAfter(
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            java.util.Date createdAfter) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `created_on` >= ? " +
            "and `hidden` is not true;";

        // Converting to sql timestamp
        Timestamp createdAfterSql = new Timestamp(createdAfter.getTime());

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setTimestamp(3, createdAfterSql);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static List<SharedItem> getByNetworkIdAndSmartGroupRefAndCreatedAfter (
         Connection conn,
         Integer networkId,
         Integer smartGroupRef,
         java.util.Date createdAfter,
         SqlLimit limit) throws SQLException {

         conn = start(conn);

         String sql =
             "select * " +
             "from `shared_items` " +
             "where `network_id` = ? " +
             "and `smart_group_ref` = ? " +
             "and `created_on` >= ? " +
             "limit ?,?;";

         // Converting to sql timestamp
         Timestamp createdAfterSql = new Timestamp(createdAfter.getTime());

         PreparedStatement ps = conn.prepareStatement(sql);
         ps.setInt(1, networkId);
         ps.setInt(2, smartGroupRef);
         ps.setTimestamp(3, createdAfterSql);
         ps.setInt(4, limit.getStartFrom());
         ps.setInt(5, limit.getDuration());
         ResultSet rs = ps.executeQuery();

         List<SharedItem> out = new ArrayList<SharedItem>();

         while (rs.next())
             out.add(loadPrimitives(rs));

         end(conn, ps, rs);
         return out;
     }

    public static Integer countByNetworkIdAndUserId (
        Connection conn,
        Integer networkId,
        Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `hidden` is not true;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

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
            "from `shared_items` " +
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

    public static List<SharedItem> getByNetworkIdAndSmartGroupRef (
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `hidden` is not true " +
            "order by `created_on` desc, `id` desc " +
            "limit ?, ?;";


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<SharedItem> out = new ArrayList<SharedItem>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static SharedItem getByNetworkIdAndSmartGroupRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, sharedItemRef);
        ResultSet rs = ps.executeQuery();

        SharedItem out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<SharedItem> getByNetworkIdAndUserId (
        Connection conn,
        Integer networkId,
        Integer userId,
        SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `hidden` is not true " +
            "order by `created_on` desc, `id` desc " +
            "limit ?, ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<SharedItem> out = new ArrayList<SharedItem>();

        while (rs.next())
            out.add(loadPrimitives(rs));

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
            "from `shared_items` " +
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

    public static List<SharedItem> findByNetworkIdAndText (
        Connection conn,
        Integer networkId,
        String searchText,
        SqlLimit limit) throws SQLException {

    conn = start(conn);

    String sql = "select * " +
        "from `shared_items` " +
        "where `network_id` = ? " +
        "and upper(`text`) like upper(?) " +
        "and `hidden` is not true " +
        "order by `created_on` desc, `id` desc " +
        "limit ?,?;";

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, networkId);
    ps.setString(2, searchText);
    ps.setInt(3, limit.getStartFrom());
    ps.setInt(4, limit.getDuration());
    ResultSet rs = ps.executeQuery();

    List<SharedItem> out = new ArrayList<SharedItem>();

    while (rs.next())
        out.add(loadPrimitives(rs));

    end(conn, ps, rs);
    return out;
}


    public static void incrementTotalCommentsByNetworkIdAndSmartGroupRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer ref,
        Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_items` " +
            "set `total_comments` = `total_comments` + ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static void incrementUpVotesByNetworkIdAndSmartGroupRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer ref,
        Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_items` " +
            "set `up_votes` = `up_votes` + ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static void incrementDownVotesByNetworkIdAndSmartGroupRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer ref,
        Integer incrementBy) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_items` " +
            "set `down_votes` = `down_votes` + ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, incrementBy);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer deleteByUserIdAndNetworkId(Connection conn, Integer networkId, Integer userId) throws SQLException  {
        conn = start(conn);

        String sql =
            "delete " +
            "from `shared_items` " +
            "where `network_id` = ? " +
            "and `user_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert(
        Connection conn,
        Integer networkId,
        Integer userId,
        Integer smartGroupRef,
        Integer ref,
        String text) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `shared_items` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`user_id`, " +
            "`smart_group_ref`, " +
            "`ref`, " +
            "`text`" +
            ") values (NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, ref);
        ps.setString(5, text);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static void updateHiddenByNetworkIdAndSmartGroupRefAndRef(
        Connection conn,
        Integer networkId,
        Integer smartGroupRef,
        Integer ref,
        Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `shared_items` " +
            "set `hidden` = ? " +
            "where `network_id` = ? " +
            "and `smart_group_ref` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.setInt(4, ref);
        ps.execute();

        end(conn, ps, null);
    }




    private static SharedItem loadPrimitives (ResultSet rs) throws SQLException {
        SharedItem out = new SharedItem();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setSharedItemRef(DatabaseUtils.getInt(rs, "ref"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setSmartGroupRef(DatabaseUtils.getInt(rs, "smart_group_ref"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setTotalComments(DatabaseUtils.getInt(rs, "total_comments"));
        out.setText(DatabaseUtils.getString(rs, "text"));
        out.setHidden(DatabaseUtils.getBoolean(rs, "hidden"));
        out.setUpVotes(DatabaseUtils.getInt(rs, "up_votes"));
        out.setDownVotes(DatabaseUtils.getInt(rs, "down_votes"));

        return out;
    }

}
