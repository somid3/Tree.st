package com.questy.dao;

import com.questy.domain.UserAlphaSetting;
import com.questy.enums.UserAlphaSettingEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAlphaSettingDao extends ParentDao {

    public static UserAlphaSetting getByUserIdAndSettingEnum(
        Connection conn,
        Integer userId,
        UserAlphaSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_alpha_settings` " +
            "where `user_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, settingEnum.getId());
        ResultSet rs = ps.executeQuery();

        UserAlphaSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static List<UserAlphaSetting> getBySettingEnum (
        Connection conn,
        UserAlphaSettingEnum settingEnum,
        SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `user_alpha_settings` " +
            "where `setting_id` = ? " +
            "order by `user_id` desc " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, settingEnum.getId());
        ps.setInt(2, limit.getStartFrom());
        ps.setInt(3, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<UserAlphaSetting> out = new ArrayList<UserAlphaSetting>();
        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static void updateByUserIdAndSettingEnum(
        Connection conn,
        Integer userId,
        UserAlphaSettingEnum settingEnum,
        String settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "update `user_alpha_settings` " +
            "set `setting_value` = ? " +
            "where `user_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, settingValue);
        ps.setInt(2, userId);
        ps.setInt(3, settingEnum.getId());
        ps.execute();

        end(conn, ps, null);
    }

    public static Integer deleteByUserIdAndSettingEnum(
            Connection conn,
            Integer userId,
            UserAlphaSettingEnum settingEnum) throws SQLException {

        conn = start(conn);

        String sql =
            "delete " +
            "from `user_alpha_settings` " +
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
        UserAlphaSettingEnum settingEnum,
        String settingValue) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `user_alpha_settings` (" +
            "`user_id`, " +
            "`setting_id`, " +
            "`setting_value` " +
            ") values (?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, settingEnum.getId());
        ps.setString(3, settingValue);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static UserAlphaSetting loadPrimitives (ResultSet rs) throws SQLException {
        UserAlphaSetting out = new UserAlphaSetting();

        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setValue(DatabaseUtils.getString(rs, "setting_value"));
        out.setSettingEnum(UserAlphaSettingEnum.getById(DatabaseUtils.getInt(rs, "setting_id")));

        return out;
    }

}
