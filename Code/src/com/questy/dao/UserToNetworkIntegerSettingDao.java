package com.questy.dao;

import com.questy.domain.UserToNetworkIntegerSetting;
import com.questy.enums.UserToNetworkIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;

public class UserToNetworkIntegerSettingDao extends ParentDao {

    public static UserToNetworkIntegerSetting getByUserIdAndNetworkIdAndSettingEnum (
        Connection conn,
        Integer userId,
        Integer networkId,
        UserToNetworkIntegerSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_to_network_integer_settings` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, settingEnum.getId());
        ResultSet rs = ps.executeQuery();

        UserToNetworkIntegerSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static void updateByUserIdAndNetworkIdAndSetting(
        Connection conn,
        Integer userId,
        Integer networkId,
        UserToNetworkIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_to_network_integer_settings` " +
            "set `setting_value` = ? " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingValue);
        ps.setInt(2, userId);
        ps.setInt(3, networkId);
        ps.setInt(4, settingEnum.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer deleteByUserIdAndNetworkIdAndSettingEnum(
        Connection conn,
        Integer userId,
        Integer networkId,
        UserToNetworkIntegerSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `user_to_network_integer_settings` " +
            "where `user_id` = ? " +
            "and `network_id` = ? " +
            "and `setting_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, settingEnum.getId());
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }



    public static Integer insert (
        Connection conn,
        Integer userId,
        Integer networkId,
        UserToNetworkIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_to_network_integer_settings` (" +
            "`user_id`, " +
            "`network_id`, " +
            "`setting_id`, " +
            "`setting_value` " +
            ") values (?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, networkId);
        ps.setInt(3, settingEnum.getId());
        ps.setInt(4, settingValue);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserToNetworkIntegerSetting loadPrimitives (ResultSet rs) throws SQLException {
        UserToNetworkIntegerSetting out = new UserToNetworkIntegerSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setNetworkId(DatabaseUtils.getInt(rs, "network_id"));
        out.setValue(DatabaseUtils.getInt(rs, "setting_value"));
        out.setSettingEnum(UserToNetworkIntegerSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));

        return out;
    }

}
