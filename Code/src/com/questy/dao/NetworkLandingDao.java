package com.questy.dao;

import com.questy.domain.NetworkDependsOn;
import com.questy.domain.NetworkLanding;
import com.questy.enums.RoleEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkLandingDao extends ParentDao {

    public static NetworkLanding getByNetworkIdAndRef(Connection conn, Integer networkId, Integer ref) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `network_landings` " +
            "where `network_id` = ? " +
            "and `ref` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, ref);
        ResultSet rs = ps.executeQuery();

        NetworkLanding out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    private static NetworkLanding loadPrimitives (ResultSet rs) throws SQLException {
        NetworkLanding out = new NetworkLanding();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));

        return out;
    }

}
