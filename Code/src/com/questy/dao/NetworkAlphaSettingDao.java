package com.questy.dao;

import com.questy.domain.NetworkAlphaSetting;
import com.questy.domain.NetworkIntegerSetting;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkAlphaSettingDao extends ParentDao {

    public static NetworkAlphaSetting getByNetworkIdAndSettingEnum(Connection conn, Integer networkId, NetworkAlphaSettingEnum setting) throws SQLException {
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

    public static List<NetworkAlphaSetting> getByNetworkId (Connection conn, Integer networkId) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `network_alpha_settings` " +
            "where `network_id` = ? ";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ResultSet rs = ps.executeQuery();

        List<NetworkAlphaSetting> out = new ArrayList<NetworkAlphaSetting>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    public static Integer deleteByValue (
            Connection conn,
            NetworkAlphaSettingEnum settingEnum,
            String value) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `network_alpha_settings` " +
            "where `setting_id` = ? " +
            "and `setting_value` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingEnum.getId());
        ps.setString(2, value);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    public static Integer deleteByNetworkIdAndSetting (
            Connection conn,
            Integer networkId,
            NetworkAlphaSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `network_alpha_settings` " +
            "where `network_id` = ? " +
            "and `setting_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, networkId);
        ps.setInt(2, settingEnum.getId());
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    public static void updateByNetworkIdAndSettingEnum(
        Connection conn,
        Integer networkId,
        NetworkAlphaSettingEnum settingEnum,
        String settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "update `network_alpha_settings` " +
            "set `setting_value` = ? " +
            "where `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, settingValue);
        ps.setInt(2, networkId);
        ps.setInt(3, settingEnum.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer insert (
        Connection conn,
        Integer networkId,
        NetworkAlphaSettingEnum settingEnum,
        String settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `network_alpha_settings` (" +
            "`network_id`, " +
            "`setting_id`, " +
            "`setting_value` " +
            ") values (?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, networkId);
        ps.setInt(2, settingEnum.getId());
        ps.setString(3, settingValue);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
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
