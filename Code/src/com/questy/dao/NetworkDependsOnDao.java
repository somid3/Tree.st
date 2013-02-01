package com.questy.dao;

import com.questy.domain.NetworkDependsOn;
import com.questy.enums.RoleEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkDependsOnDao extends ParentDao {

    public static List<NetworkDependsOn> getAll (Connection conn) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `network_depends_on`";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<NetworkDependsOn> out = new ArrayList<NetworkDependsOn>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static List<NetworkDependsOn> getByNetworkId(Connection conn, Integer networkId) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `network_depends_on` " +
            "where `network_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        List<NetworkDependsOn> out = new ArrayList<NetworkDependsOn>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    public static Integer insert(
            Connection conn,
            Integer network_id,
            Integer depends_on,
            RoleEnum role) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `network_depends_on` (" +
            "`created_on`, " +
            "`network_id`, " +
            "`depends_on`, " +
            "`role` " +
            ") values (NOW(), ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, network_id);
        ps.setInt(2, depends_on);
        ps.setInt(3, role.getId());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static NetworkDependsOn loadPrimitives (ResultSet rs) throws SQLException {
        NetworkDependsOn out = new NetworkDependsOn();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setDependsOn(DatabaseUtils.getInt(rs, "depends_on"));
        out.setRole(
            RoleEnum.getById(DatabaseUtils.getInt(rs, "role"))
        );

        return out;
    }

}
