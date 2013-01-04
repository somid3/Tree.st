package com.questy.admin.dao;

import com.questy.admin.domain.GeneralEmail;
import com.questy.dao.ParentDao;
import com.questy.utils.DatabaseUtils;
import com.sun.corba.se.pept.transport.ContactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: Shlomo
 * Date: 1/3/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneralEmailDao extends ParentDao{
    public static List<GeneralEmail> getUnsent(Connection conn) throws SQLException {
        conn = start(conn);

        String sql =
                "select * " +
                        "from `general_emails` " +
                        "where `sent_on` is null;";

        PreparedStatement ps = conn.prepareStatement(sql);
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

    public static boolean exists(GeneralEmail c)  throws SQLException
    {
        Connection conn = null;
        int count = 0;
        conn = start(conn);

        String sql = "SELECT " + "*" + " FROM " + "`general_emails`" + " WHERE " + "email = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,c.getEmail());
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            count++;
        }

        if(count==0)
            return false;
        return true;
    }

    public static Integer insert(
            Connection conn, GeneralEmail c
    ) throws SQLException {

        conn = start(conn);

        String sql =
                "insert into `general_emails` (" +
                        "`first_name`, " +
                        "`email`, " +
                        "`organization`, " +
                        "`url` " +
                        ") values (?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


        ps.setString(1, c.getFirst_name());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getOrganization());
        ps.setString(4, c.getUrl());
        ps.execute();

        Integer generatedId = DatabaseUtils.getFirstGeneratedKey(ps.getGeneratedKeys());

        end(conn, ps, null);
        return generatedId;
    }

    private static GeneralEmail loadPrimitives (ResultSet rs) throws SQLException {
        GeneralEmail out = new GeneralEmail();
        out.setId(DatabaseUtils.getInt(rs, "id"));
        out.setFirst_name(DatabaseUtils.getString(rs, "first_name"));
        out.setEmail(DatabaseUtils.getString(rs, "email"));
        out.setOrganization(DatabaseUtils.getString(rs, "organization"));
        out.setUrl(DatabaseUtils.getString(rs, "url"));
        out.setSentOn(DatabaseUtils.getTimestamp(rs, "sent_on"));
        return out;
    }

}
