package com.questy.dao;

import com.questy.domain.Network;
import com.questy.utils.DatabaseUtils;
import com.questy.utils.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkDao extends ParentDao {

    public static List<Network> getAll (Connection conn) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `networks`";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Network> out = new ArrayList<Network>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static void updateTotalMembers(Connection conn, Integer networkId, Integer totalMembers) throws SQLException {
        conn = start(conn);

        String sql =
         "update `networks` " +
         "set `total_members` = ? " +
         "where `id` = ? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, totalMembers);
        ps.setInt(2, networkId);
        ps.execute();

        end(conn, ps, null);
    }

    public static Network getById(Connection conn, Integer id) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `networks` " +
            "where `id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Network out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }


    public static Network getByIdAndChecksum(
            Connection conn,
            Integer id, String checksum) throws SQLException {

        conn = start(conn);

        if (id == null) return null;
        if (checksum == null) return null;

        String sql =
            "select * " +
            "from `networks` " +
            "where `id` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, checksum);
        ResultSet rs = ps.executeQuery();

        Network out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer insert(
            Connection conn,
            String name) throws SQLException {

        String checksum = StringUtils.random();

        conn = start(conn);

        String sql =
            "insert into `networks` (" +
            "`created_on`, " +
            "`name`, " +
            "`global`, " +
            "`checksum` " +
            ") values (NOW(), ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, checksum);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static Network loadPrimitives (ResultSet rs) throws SQLException {
        Network out = new Network();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setTotalMembers(DatabaseUtils.getInt(rs, "total_members"));
        out.setName(DatabaseUtils.getString(rs, "name"));
        out.setChecksum(DatabaseUtils.getString(rs, "checksum"));

        return out;
    }

}
