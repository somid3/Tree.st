package com.questy.dao;

import com.questy.domain.Network;
import com.questy.domain.Tooltip;
import com.questy.enums.TooltipEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TooltipDao extends ParentDao {

    public static Tooltip getByUserId(Connection conn, Integer userId) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `tooltips` " +
            "where `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        Tooltip out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static Integer updateOnTooltipByUserId(Connection conn, Integer userId, TooltipEnum onTooltip) throws SQLException {
        conn = start(conn);

        String sql =
            "update `tooltips` " +
            "set `on_tooltip` = ?, " +
            "`updated_on` = NOW() " +
            "where `user_id` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, onTooltip.getValue());
        ps.setInt(2, userId);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer insert (
            Connection conn,
            Integer userId,
            TooltipEnum onTooltip) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `tooltips` (" +
            "`created_on`, " +
            "`user_id`, " +
            "`on_tooltip`" +
            ") values (NOW(), ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setString(2, onTooltip.getValue());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static Tooltip loadPrimitives (ResultSet rs) throws SQLException {

        Tooltip out = new Tooltip();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setUpdatedOn(DatabaseUtils.getTimestamp(rs, "updated_on"));
        out.setOnTooltip(TooltipEnum.getByValue(DatabaseUtils.getString(rs, "on_tooltip")));

        return out;
    }

}
