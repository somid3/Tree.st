package com.questy.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class PooledConnector {

    protected static ComboPooledDataSource connectionPool = null;

    // Initializing all singletons
    static {

        // Ensuring connection pool is a singleton
        if (connectionPool == null) {

            try {

                // Start connection pool
                ComboPooledDataSource cpds = new ComboPooledDataSource();
                cpds.setDriverClass("com.mysql.jdbc.Driver");

                cpds.setJdbcUrl("jdbc:mysql://localhost:3306/questy?zeroDateTimeBehavior=convertToNull");
                cpds.setUser("root");
                cpds.setPassword("K1ng.k0ng");

////////                cpds.setJdbcUrl("jdbc:mysql://72.167.164.208:3306/questy?zeroDateTimeBehavior=convertToNull");
////////                cpds.setUser("omid");
////////                cpds.setPassword("y0l0s3");

                                cpds.setJdbcUrl("jdbc:mysql://72.167.164.208:3306/questy?zeroDateTimeBehavior=convertToNull");
                                cpds.setUser("omid");
                                cpds.setPassword("y0l0s3");



                // Warn developer localhost is not the database of choice
                if (!cpds.getJdbcUrl().contains("localhost")) {

                    System.out.println("*** WARNING YOU ARE NOT CONNECTED TO THE LOCALHOST DATABASE! ***");
                    System.out.println("*** WARNING YOU ARE NOT CONNECTED TO THE LOCALHOST DATABASE! ***");
                    System.out.println("*** WARNING YOU ARE NOT CONNECTED TO THE LOCALHOST DATABASE! ***");
                    System.out.println("*** WARNING YOU ARE NOT CONNECTED TO THE LOCALHOST DATABASE! ***");

                }

                // Connection pool settings
                cpds.setMinPoolSize(5);
                cpds.setAcquireIncrement(10);
                cpds.setMaxPoolSize(100);

                // The most a connection can remain idle is one hour
                cpds.setMaxIdleTime(1 * 60 * 60);

                // Saving connection pool to singleton
                connectionPool = cpds;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns a connection either for transactional support, or not. If a the supplied
     * connection is null, then a new connection is retrieved from the connection pool
     * with auto-commit set to true! That way, when the #end() family of methods are called
     * the connection with auto-commit set to true will be closed and returned to the
     * connection pool
     * <p/>
     * If a connection is passed with auto-commit set to false, then the same connection
     * will be returned by the #start() family of methods, and the #end() family of
     * methods will not close such connection, it is expected that the transaction
     * opening method will take care of closing such connection
     *
     * @param conn
     * @return
     * @throws java.sql.SQLException
     */
    protected static Connection start(Connection conn) throws SQLException {

        if (conn == null) {

            Connection newConn = connectionPool.getConnection();
            newConn.setAutoCommit(true);
            return newConn;

        } else {

            return conn;

        }
    }

    /**
     * Closes all PreparedStatement and ResultSet objects passed to it. It will also
     * close all Connection objects with auto-commit set to true. If the Connection's
     * auto-commit is set to false, then that is a transactional connection, such
     * connections will not be closed at the DAO layer
     *
     * @param conn
     * @param sti
     * @param rs
     * @throws SQLException
     */
    protected static void end(Connection conn, Statement sti, ResultSet rs) throws SQLException {

        if (rs != null)
            rs.close();

        if (sti != null)
            sti.close();

        // Closing connections only if they are non-transactional
        if (conn != null && conn.getAutoCommit()) {

            conn.close();

        }

    }
}
