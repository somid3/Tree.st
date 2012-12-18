package com.questy.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DatabaseUtils {

   @Deprecated
   public static java.sql.Connection getConnection() {

        java.sql.Connection con = null;

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/questy?zeroDateTimeBehavior=convertToNull", "root", "K1ng.k0ng");

        } catch(Exception e) {
          System.err.println("Exception: " + e.getMessage());
        }

        return con;
    }                      

    public static Integer getFirstGeneratedKey (ResultSet rs) throws SQLException {
        Integer id = null;
        if (rs.next())
            id = rs.getInt(1);

        return id;
    }

    public static Integer getInt(ResultSet resultSet, String columnName) throws SQLException {

        Integer out = resultSet.getInt(columnName);
        if (resultSet.wasNull())
            return null;

        return out;
        
    }

    public static Float getFloat(ResultSet resultSet, String columnName) throws SQLException {

        Float out = resultSet.getFloat(columnName);
        if (resultSet.wasNull())
            return null;

        return out;

    }

    public static Double getDouble(ResultSet resultSet, String columnName) throws SQLException {

        Double out = resultSet.getDouble(columnName);
        if (resultSet.wasNull())
            return null;

        return out;

    }

    public static String getString(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getString(columnName);
    }

    public static Timestamp getTimestamp(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getTimestamp(columnName);
    }

    public static Boolean getBoolean(ResultSet resultSet, String columnName) throws SQLException {
        return resultSet.getBoolean(columnName);
    }
}
