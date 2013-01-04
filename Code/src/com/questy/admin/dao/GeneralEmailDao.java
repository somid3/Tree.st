package com.questy.admin.dao;

import com.questy.admin.domain.GeneralEmail;
import com.questy.dao.ParentDao;
import com.questy.helpers.SqlLimit;
import com.questy.utils.DatabaseUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralEmailDao extends ParentDao{

    public static List<GeneralEmail> getUnsentByIndustry(
            Connection conn,
            String industry,
            SqlLimit limit) throws SQLException {

        conn = start(conn);

        String sql =
            "select * " +
            "from `general_emails` " +
            "where `sent_on` is null " +
            "and `industry` = ? " +
            "limit ?,?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, industry);
        ps.setInt(2, limit.getStartFrom());
        ps.setInt(3, limit.getDuration());
        ResultSet rs = ps.executeQuery();

        List<GeneralEmail> out = new ArrayList<GeneralEmail>();

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
            "update `general_emails` " +
            "set `sent_on` = ? " +
            "where `id` = ?;";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setTimestamp(1, sentOnSql);
        ps.setInt(2, id);

        ps.executeUpdate();

        end(conn, ps, null);
    }

    public static Integer getCountByEmail(
            Connection conn,
            String email)  throws SQLException {

        conn = start(conn);

        String sql =
            "select count(*) as `count` " +
            "from `general_emails` " +
            "where `email` = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        Integer out = null;

        while (rs.next())
            out = DatabaseUtils.getInt(rs, "count");

        end(conn, ps, rs);
        return out;
    }

    public static Integer insert (
        Connection conn,
        String email,
        String fromUrl,
        String industry,
        String keyword1,
        String keyword2,
        String keyword3) throws SQLException {

        conn = start(conn);

        String sql =
            "insert into `general_emails` (" +
            "`email`, " +
            "`from_url`, " +
            "`industry`, " +
            "`keyword_1`, " +
            "`keyword_2`, " +
            "`keyword_3` " +
            ") values (?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        ps.setString(1, email);
        ps.setString(2, fromUrl);
        ps.setString(3, industry);
        ps.setString(4, keyword1);
        ps.setString(5, keyword2);
        ps.setString(6, keyword3);
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static GeneralEmail loadPrimitives (ResultSet rs) throws SQLException {
        GeneralEmail out = new GeneralEmail();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setEmail(DatabaseUtils.getString(rs, "email"));
        out.setFromUrl(DatabaseUtils.getString(rs, "from_url"));
        out.setKeyword1(DatabaseUtils.getString(rs, "keyword_1"));
        out.setKeyword2(DatabaseUtils.getString(rs, "keyword_2"));
        out.setKeyword3(DatabaseUtils.getString(rs, "keyword_3"));
        out.setSentOn(DatabaseUtils.getTimestamp(rs, "sent_on"));
        return out;
    }

}
