package com.questy.utils;

import static java.lang.System.out;

import com.questy.admin.dao.GeneralEmailDao;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VarsTest {

    @Test
    public void initialize () throws SQLException {

        new Vars();
        new GeneralEmailDao();

        Connection newConn = PooledConnector.start();
        newConn.setAutoCommit(true);
        String sql = "select 'a'";
        PreparedStatement ps = newConn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        out.println(rs.getMetaData());

        out.println("domain " + Vars.domain);
        out.println("boolean check == " + (Vars.sendEmails instanceof Boolean));
        out.println("mockUserAgent " + Vars.testingMockUserAgent);
        out.println("emailTemplateDomain " + Vars.emailTemplateDomain);
        out.println("sqlUsername " + Vars.sqlUsername);
        out.println("sqlPassword " + Vars.sqlPassword);


    }

}
