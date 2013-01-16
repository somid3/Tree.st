package com.questy.dao;

import com.questy.domain.NetworkIntegerSetting;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkIntegerSettingDao extends ParentDao {

    public static List<NetworkIntegerSetting> getByNetworkId (Connection conn, Integer networkId) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `network_integer_settings` " +
            "where `network_id` = ? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        List<NetworkIntegerSetting> out = new ArrayList<NetworkIntegerSetting>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static NetworkIntegerSetting getByNetworkIdAndSettingEnum(Connection conn, Integer networkId, NetworkIntegerSettingEnum setting) throws SQLException {
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

    public static void updateByNetworkIdAndSettingEnum(
        Connection conn,
        Integer networkId,
        NetworkIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "update `network_integer_settings` " +
            "set `setting_value` = ? " +
            "where `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingValue);
        ps.setInt(2, networkId);
        ps.setInt(3, settingEnum.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
        Connection conn,
        Integer networkId,
        NetworkIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `network_integer_settings` (" +
            "`network_id`, " +
            "`setting_id`, " +
            "`setting_value` " +
            ") values (?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, settingEnum.getId());
        ps.setInt(3, settingValue);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static NetworkIntegerSetting loadPrimitives (ResultSet rs) throws SQLException {
        NetworkIntegerSetting out = new NetworkIntegerSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setValue(DatabaseUtils.getInt(rs, "setting_value"));
        out.setSettingEnum(NetworkIntegerSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));

        return out;
    }

}
