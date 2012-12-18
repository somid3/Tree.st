package com.questy.dao;

import com.questy.domain.AppResource;
import com.questy.enums.AppEnum;
import com.questy.enums.AppResourceTypeEnum;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppResourceDao extends ParentDao {

    public static List<AppResource> getByUserIdAndAppAndTypeAndTemp(
            Connection conn,
            Integer userId,
            AppEnum app,
            AppResourceTypeEnum type,
            Boolean isTemporary) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `app_resources` " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `type` = ? " +
            "and `temporary` = ? " +
            "and `hidden` is not true " +
            "order by `created_on` desc, `id` desc;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, app.getValue());
        ps.setInt(3, type.getValue());
        ps.setBoolean(4, isTemporary);
        ResultSet rs = ps.executeQuery();

        List<AppResource> out = new ArrayList<AppResource>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }

    public static AppResource getByUserIdAndAppAndTypeAndRefAndChecksum (
            Connection conn,
            Integer userId,
            AppEnum app,
            AppResourceTypeEnum type,
            Integer ref,
            String checksum) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `app_resources` " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `type` = ? " +
            "and `ref` = ? " +
            "and `checksum` = ? " +
            "limit 1;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, app.getValue());
        ps.setInt(3, type.getValue());
        ps.setInt(4, ref);
        ps.setString(5, checksum);
        ResultSet rs = ps.executeQuery();

        AppResource out = null;

        while (rs.next())
            out = loadPrimitives(rs);

        end(conn, ps, rs);
        return out;
    }

    public static AppResource getByUserIdAndAppAndTypeAndRef (
                Connection conn,
                Integer userId,
                AppEnum app,
                AppResourceTypeEnum type,
                Integer ref) throws SQLException {

            conn = start(conn);

            String sql =
                "select * " +
                "from `app_resources` " +
                "where `user_id` = ? " +
                "and `app` = ? " +
                "and `type` = ? " +
                "and `ref` = ? " +
                "and `hidden` is not true " +
                "limit 1;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, app.getValue());
            ps.setInt(3, type.getValue());
            ps.setInt(4, ref);
            ResultSet rs = ps.executeQuery();

            AppResource out = null;

            while (rs.next())
                out = loadPrimitives(rs);

            end(conn, ps, rs);
            return out;
        }


    public static Integer updateHiddenByUserIdAndAppAndTemp(
            Connection conn,
            Integer userId,
            AppEnum app,
            Boolean isTemporary,
            Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `app_resources` " +
            "set `hidden` = ? " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `temporary` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, userId);
        ps.setInt(3, app.getValue());
        ps.setBoolean(4, isTemporary);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer updateHiddenByUserIdAndAppAndRef (
            Connection conn,
            Integer userId,
            AppEnum app,
            Integer ref,
            Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `app_resources` " +
            "set `hidden` = ? " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, userId);
        ps.setInt(3, app.getValue());
        ps.setInt(4, ref);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }

    public static Integer updateHiddenByUserIdAndAppAndTypeAndRef (
            Connection conn,
            Integer userId,
            AppEnum app,
            AppResourceTypeEnum type,
            Integer ref,
            Boolean hidden) throws SQLException {

        conn = start(conn);

        String sql =
            "update `app_resources` " +
            "set `hidden` = ? " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `type` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, hidden);
        ps.setInt(2, userId);
        ps.setInt(3, app.getValue());
        ps.setInt(4, type.getValue());
        ps.setInt(5, ref);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    public static Integer updateTemporaryByUserIdAndAppAndRef(
            Connection conn,
            Integer userId,
            AppEnum app,
            Integer ref,
            Boolean isTemporary) throws SQLException {

        conn = start(conn);

        String sql =
            "update `app_resources` " +
            "set `temporary` = ? " +
            "where `user_id` = ? " +
            "and `app` = ? " +
            "and `ref` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, isTemporary);
        ps.setInt(2, userId);
        ps.setInt(3, app.getValue());
        ps.setInt(4, ref);
        Integer out = ps.executeUpdate();

        end(conn, ps, null);
        return out;
    }


    private static Integer getMaxRefByUserIdAndApp(
            Connection conn,
            Integer userId, AppEnum app) throws SQLException {

        conn = start(conn);

        String sql =
            "select max(`ref`) as `max_ref` " +
            "from `app_resources` " +
            "where `user_id` = ? " +
            "and `app` = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setInt(2, app.getValue());
        ResultSet rs = ps.executeQuery();

        Integer max = null;
        while (rs.next())
            max = DatabaseUtils.getInt(rs, "max_ref");

        end(conn, ps, rs);
        return max;
    }

    public static Integer getNextRefByUserIdAndApp (
            Connection conn,
            Integer userId,
            AppEnum app) throws SQLException {

        // Retrieving max ref
        Integer maxRef = getMaxRefByUserIdAndApp(conn, userId, app);

        /* Creating next ref, starting with 1 and not 0 */
        Integer nextRef = 1;
        if (maxRef != null)
            nextRef = maxRef + 1;

        return nextRef;
    }

    public static Integer insert (
            Connection conn,
            Integer userId,
            AppEnum application,
            AppResourceTypeEnum type,
            Boolean isTemporary,
            Integer ref,
            String checksum) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `app_resources` (" +
            "`user_id`, " +
            "`app`, " +
            "`ref`, " +
            "`checksum`, " +
            "`type`, " +
            "`created_on`, " +
            "`temporary`" +
            ") values (?, ?, ?, ?, ?, NOW(), ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.setInt(2, application.getValue());
        ps.setInt(3, ref);
        ps.setString(4, checksum);
        ps.setInt(5, type.getValue());
        ps.setBoolean(6, isTemporary);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);

        return generatedId;
    }

    private static AppResource loadPrimitives(ResultSet rs) throws SQLException {
        AppResource out = new AppResource();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setUserId(DatabaseUtils.getInt(rs, "user_id"));
        out.setRef(DatabaseUtils.getInt(rs, "ref"));
        out.setChecksum(DatabaseUtils.getString(rs, "checksum"));
        out.setApp(AppEnum.getByValue(DatabaseUtils.getInt(rs, "app")));
        out.setType(AppResourceTypeEnum.getByValue(DatabaseUtils.getInt(rs, "type")));
        out.setCreatedOn(DatabaseUtils.getTimestamp(rs, "created_on"));
        out.setTemporary(DatabaseUtils.getBoolean(rs, "temporary"));
        out.setHidden(DatabaseUtils.getBoolean(rs, "hidden"));
        return out;
    }

}
