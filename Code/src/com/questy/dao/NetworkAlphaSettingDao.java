package com.questy.dao;

import com.questy.domain.NetworkAlphaSetting;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;

public class NetworkAlphaSettingDao extends ParentDao {

    public static NetworkAlphaSetting getByNetworkIdAndSetting(Connection conn, Integer networkId, NetworkAlphaSettingEnum setting) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `network_alpha_settings` " +
            "where `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, setting.getId());
        ResultSet rs = ps.executeQuery();

        NetworkAlphaSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static NetworkAlphaSetting getByValueAndSetting(Connection conn, String value, NetworkAlphaSettingEnum setting) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `network_alpha_settings` " +
            "where `setting_value` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, value);
        ps.setInt(2, setting.getId());
        ResultSet rs = ps.executeQuery();

        NetworkAlphaSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    private static NetworkAlphaSetting loadPrimitives (ResultSet rs) throws SQLException {
        NetworkAlphaSetting out = new NetworkAlphaSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setValue(DatabaseUtils.getString(rs, "setting_value"));
        out.setSettingEnum(NetworkAlphaSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));

        return out;
    }
}
