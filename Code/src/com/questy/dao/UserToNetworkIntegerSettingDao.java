package com.questy.dao;

import com.questy.domain.UserIntegerSetting;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserToNetworkIntegerSettingDao extends ParentDao {

    public static UserIntegerSetting getByUserIdAndSettingEnum(
        Connection conn,
        Integer userId,
        UserIntegerSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_integer_settings` " +
            "where `user_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, settingEnum.getId());
        ResultSet rs = ps.executeQuery();

        UserIntegerSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<UserIntegerSetting> getBySettingEnumAndValue(
        Connection conn,
        UserIntegerSettingEnum settingEnum,
        Integer value) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_integer_settings` " +
            "where `setting_id` = ? " +
            "and `setting_value` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingEnum.getId());
        ps.setInt(2, value);

        ResultSet rs = ps.executeQuery();

        List<UserIntegerSetting> out = new ArrayList<UserIntegerSetting>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static void updateByUserIdAndSetting(
        Connection conn,
        Integer userId,
        UserIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_integer_settings` " +
            "set `setting_value` = ? " +
            "where `user_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingValue);
        ps.setInt(2, userId);
        ps.setInt(3, settingEnum.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer deleteByUserIdAndSettingEnum(
        Connection conn,
        Integer userId,
        UserIntegerSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `user_integer_settings` " +
            "where `user_id` = ? " +
            "and `setting_id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, settingEnum.getId());
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }



    public static Integer insert (
        Connection conn,
        Integer userId,
        UserIntegerSettingEnum settingEnum,
        Integer settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_integer_settings` (" +
            "`user_id`, " +
            "`setting_id`, " +
            "`setting_value` " +
            ") values (?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, settingEnum.getId());
        ps.setInt(3, settingValue);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserIntegerSetting loadPrimitives (ResultSet rs) throws SQLException {
        UserIntegerSetting out = new UserIntegerSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setValue(DatabaseUtils.getInt(rs, "setting_value"));
        out.setSettingEnum(UserIntegerSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));

        return out;
    }

}
