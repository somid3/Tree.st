package com.questy.dao;

import com.questy.domain.SmartGroup;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmartGroupDao extends ParentDao {

    /**
     * Name of the internal smart group each user has, here they
     * store the search criteria for the last search they submitted
     */
    public static String SEARCH_NAME = "_search";

    /**
     * A ref that symbolizes the community which holds the smart group
     */
    public static int ANY_SMART_GROUP_REF = 0;

    public static SmartGroup getSearchByNetworkIdAndUserId(Connection conn, Integer networkId, Integer userId) throws SQLException {
        return getByNetworkIdAndUserIdAndName(conn, networkId, userId, SEARCH_NAME);
    }

    public static SmartGroup getByNetworkIdAndUserIdAndName (
            Connection conn,
            Integer networkId,
            Integer userId,
            String name) throws SQLException {

        conn = start(conn);


        String sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `name` = ? " +
            "and `hidden` is not true;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setString(3, name);

        ResultSet rs = ps.executeQuery();

        SmartGroup out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<SmartGroup> findByNetworkIdAndName (
            Connection conn,
            Integer networkId,
            String searchName,
            SmartGroupVisibilityEnum lowestVisibility,
            Integer createdByUserId) throws SQLException {

        conn = start(conn);

        String sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        if (createdByUserId != null) {

            sql = "select * " +
                "from `smart_groups` " +
                "where `network_id` = ? " +
                "and `user_id` = ? " +
                "and `visibility` >= ? " +
                "and upper(`name`) like upper(?) " +
                "and `name` != ? " +
                "and `hidden` is not true " +
                "order by `visibility` desc, `id` desc;";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, networkId);
            ps.setInt(2, createdByUserId);
            ps.setInt(3, lowestVisibility.getValue());
            ps.setString(4, searchName);
            ps.setString(5, SmartGroupDao.SEARCH_NAME);
            rs = ps.executeQuery();

        } else {

            sql = "select * " +
                "from `smart_groups` " +
                "where `network_id` = ? " +
                "and `visibility` >= ? " +
                "and upper(`name`) like upper(?) " +
                "and `name` != ? " +
                "and `hidden` is not true " +
                "order by `visibility` desc, `id` desc;";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, networkId);
            ps.setInt(2, lowestVisibility.getValue());
            ps.setString(3, searchName);
            ps.setString(4, SmartGroupDao.SEARCH_NAME);
            rs = ps.executeQuery();

        }


        List<SmartGroup> out = new ArrayList<SmartGroup>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<SmartGroup> getNonHiddenByNetworkIdAndLowestVisibility (
            Connection conn,
            Integer networkId,
            SmartGroupVisibilityEnum lowestVisibility) throws SQLException {

        conn = start(conn);

        String sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `visibility` >= ? " +
            "and `hidden` is not true " +
            "order by `visibility` desc, `results_count` desc;";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, lowestVisibility.getValue());
        rs = ps.executeQuery();


        List<SmartGroup> out = new ArrayList<SmartGroup>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<SmartGroup> getNonHiddenByNetworkIdAndVisibility (
            Connection conn,
            Integer networkId,
            SmartGroupVisibilityEnum visibility,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `visibility` = ? " +
            "and `hidden` is not true " +
            "order by `results_count` desc " +
            "limit ?, ?;";


        ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, visibility.getValue());
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        rs = ps.executeQuery();

        List<SmartGroup> out = new ArrayList<SmartGroup>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<SmartGroup> getNonHiddenByNetworkIdAndUserId (
            Connection conn,
            Integer networkId,
            Integer userId,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `hidden` is not true " +
            "order by `results_count` desc " +
            "limit ?, ?;";

        ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, limit.getStartFrom());
        ps.setInt(4, limit.getDuration());
        rs = ps.executeQuery();

        List<SmartGroup> out = new ArrayList<SmartGroup>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static SmartGroup getByNetworkIdAndRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ResultSet rs = ps.executeQuery();

        SmartGroup out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static SmartGroup getNonHiddenByNetworkIdAndRefAndLowestVisibility (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            SmartGroupVisibilityEnum lowestVisibility) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "and `visibility` >= ? " +
            "and `hidden` is not true " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, smartGroupRef);
        ps.setInt(3, lowestVisibility.getValue());
        ResultSet rs = ps.executeQuery();

        SmartGroup out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    /**
     * Only updates the query of the smart group
     */
    public static void updateQueryByNetworkIdAndRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            String query) throws SQLException {

        conn = start(conn);

        String sql =
            "update `smart_groups` " +
            "set `query` = ?, " +
            "`query_updated_on` = NOW(), " +
            "`query_updates` = `query_updates` + 1 " +
            "where `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, query);
        ps.setInt(2, networkId);
        ps.setInt(3, smartGroupRef);
        ps.execute();

        end(conn, ps, null);
    }

    /**
     * Used when a smart group is updated or transitioned
     * from a search smart group to a long term smart group
     *
     */
    public static void updateDetailsByNetworkIdAndRef (
            Connection conn,
            Integer networkId,
            Integer smartGroupRef,
            String name,
            String description,
            SmartGroupVisibilityEnum visibility) throws SQLException {

        conn = start(conn);

        String sql =
            "update `smart_groups` " +
            "set `name` = ?, " +
            "`description` = ?, " +
            "`visibility` = ? " +
            "where `network_id` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setInt(3, visibility.getValue());
        ps.setInt(4, networkId);
        ps.setInt(5, smartGroupRef);
        ps.execute();

        end(conn, ps, null);
    }

    /**
     * Used when the smart group results are recalculated
     *
     */
    public static void updateRefreshedOnByNetworkIdAndRef (
           Connection conn,
           Integer networkId,
           Integer smartGroupRef,
           Integer resultCount) throws SQLException {

       conn = start(conn);

       String sql =
           "update `smart_groups` " +
           "set `refreshed_on` = NOW(), " +
           "`results_count` = ? " +
           "where " +
           "`network_id` = ? " +
           "and `ref` = ?;";

       PreparedStatement ps = conn.prepareStatement(sql);
       ps.setInt(1, resultCount);
       ps.setInt(2, networkId);
       ps.setInt(3, smartGroupRef);
       ps.execute();

       end(conn, ps, null);
   }

    public static Integer insert(
            Connection conn,
            Integer networkId,
            Integer userId,
            Integer smartGroupRef,
            String name,String query) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `smart_groups` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`user_id`, " +
            "`ref`, " +
            "`name`, " +
            "`query`) " +
            "values (" +
            "NOW(), ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setInt(3, smartGroupRef);
        ps.setString(4, name);
        ps.setString(5, query);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    public static Integer getMaxRefByNetworkId(
            Connection conn,
            Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`ref`) as `max_ref` " +
            "from `smart_groups` " +
            "where `network_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static Integer countNonSearchByNetworkIdAndUserId(
            Connection conn,
            Integer networkId,
            Integer userId) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `user_id` = ? " +
            "and `name` != ?;";


        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, userId);
        ps.setString(3, SmartGroupDao.SEARCH_NAME);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer countByNetworkIdAndVisibility (
            Connection conn,
            Integer networkId,
            SmartGroupVisibilityEnum visibility) throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `smart_groups` " +
            "where `network_id` = ? " +
            "and `visibility` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, visibility.getValue());
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }


    public static void updateHiddenByNetworkIdAndRef (
        Connection conn,
        Integer networkId,
        Integer ref,
        Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `smart_groups` " +
            "set `hidden` = ? " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, networkId);
        ps.setInt(3, ref);
        ps.execute();

        end(conn, ps, null);
    }

    public static void removeSearchSmartGroups (List<SmartGroup> groups) {

        List<SmartGroup> filterOut = new ArrayList<SmartGroup>();

        // Group all the smart groups with a search name
        for (SmartGroup group : groups) {

            if (group.getName().equals(SmartGroupDao.SEARCH_NAME))
                filterOut.add(group);

        }

        // Removing all search smart groups from input
        groups.removeAll(filterOut);
    }



    private static SmartGroup loadPrimitives (ResultSet rs) throws SQLException {

        SmartGroup out = new SmartGroup();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setName(DatabaseUtils.getString(rs, "name"));
        out.setDescription(DatabaseUtils.getString(rs, "description"));
        out.setQuery(DatabaseUtils.getString(rs, "query"));
        out.setQueryUpdates(DatabaseUtils.getInt(rs, "query_updates"));
        out.setQueryUpdatedOn(DatabaseUtils.getTimestamp(rs, "query_updated_on"));
        out.setRefreshedOn(DatabaseUtils.getTimestamp(rs, "refreshed_on"));
        out.setIconOn(DatabaseUtils.getTimestamp(rs, "icon_on"));
        out.setHidden(DatabaseUtils.getBoolean(rs, "hidden"));
        out.setVisibility(
            SmartGroupVisibilityEnum.getByValue(DatabaseUtils.getInt(rs, "visibility"))
        );
        out.setResultsCount(DatabaseUtils.getInt(rs, "results_count"));


        return out;
    }

    public static boolean isNetworkRef (Integer smartGroupRef) {

        if (smartGroupRef.equals(ANY_SMART_GROUP_REF))
            return true;
        else
            return  false;

    }

}
