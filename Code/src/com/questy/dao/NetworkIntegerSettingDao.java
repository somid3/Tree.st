package com.questy.dao;

import com.questy.domain.NetworkIntegerSetting;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NetworkIntegerSettingDao extends ParentDao {

    public static NetworkIntegerSetting getByNetworkIdAndSetting(Connection conn, Integer networkId, NetworkIntegerSettingEnum setting) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `network_integer_settings` " +
            "where `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, setting.getId());
        ResultSet rs = ps.executeQuery();

        NetworkIntegerSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    private static NetworkIntegerSetting loadPrimitives (ResultSet rs) throws SQLException {
        NetworkIntegerSetting out = new NetworkIntegerSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setValue(DatabaseUtils.getInt(rs, "setting_value"));
        out.setSettingEnum(NetworkIntegerSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));

        return out;
    }

}
