package com.questy.admin.dao;

import com.questy.admin.domain.MITEmail;
import com.questy.dao.ParentDao;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MITEmailDao extends ParentDao {

    public static List<MITEmail> getUnsentBySchool(Connection conn) throws SQLException {
        conn = start(conn);

        String sql =
            "select * " +
            "from `mit_emails` " +
            "where `year` is not null " +
            "and `sent_on` is null;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<MITEmail> out = new ArrayList<MITEmail>();

        while (rs.next())
            out.add(loadPrimitives(rs));

        end(conn, ps, rs);
        return out;
    }


    public static void updateSentOnById (
            Connection conn,
            Integer id,
            java.util.Date sentOn) throws SQLException {

        conn = start(conn);

        Timestamp sentOnSql = new Timestamp(sentOn.getTime());

        String sql =
            "update `mit_emails` " +
            "set `sent_on` = ? " +
            "where `id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setTimestamp(1, sentOnSql);
        ps.setInt(2, id);

        ps.executeUpdate();

        end(conn, ps, null);
    }


    public static Integer insert(
        Connection conn,
        String dept,
        String email,
        String name,
        String school,
        String title,
        String year
    ) throws SQLException {

        conn = start(conn);

        String sql =
         "insert into `mit_emails` (" +
         "`dept`, " +
         "`email`, " +
         "`name`, " +
         "`school`, " +
         "`title`, " +
         "`year`" +
         ") values (?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        ps.setString(1, dept);
        ps.setString(2, email);
        ps.setString(3, name);
        ps.setString(4, school);
        ps.setString(5, title);
        ps.setString(6, year);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static MITEmail loadPrimitives (ResultSet rs) throws SQLException {
        MITEmail out = new MITEmail();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setDepartment(DatabaseUtils.getString(rs, "dept"));
        out.setEmail(DatabaseUtils.getString(rs, "email"));
        out.setName(DatabaseUtils.getString(rs, "name"));
        out.setSchool(DatabaseUtils.getString(rs, "school"));
        out.setSentOn(DatabaseUtils.getTimestamp(rs, "sent_on"));
        out.setTitle(DatabaseUtils.getString(rs, "title"));
        out.setYear(DatabaseUtils.getString(rs, "year"));
        return out;
    }

}
