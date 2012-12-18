package com.questy.dao;

import com.questy.domain.NetworkIntegerSetting;
import com.questy.domain.UserIntegerSetting;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIntegerSettingDao extends ParentDao {

    public static UserIntegerSetting getByUserIdAndSetting(Connection conn, Integer userId, UserIntegerSettingEnum setting) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `user_integer_settings` " +
            "where `user_id` = ? " +
            "and `setting_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, setting.getId());
        ResultSet rs = ps.executeQuery();

        UserIntegerSetting out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
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
