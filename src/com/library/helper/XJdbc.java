/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helper;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XJdbc {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=QLTV";
    private static String user = "sa";
    private static String pass = "songlong";
    
    

    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {

        }
    }

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            String dbUrl = url;
            return DriverManager.getConnection(dbUrl, user, pass);
        } catch (Exception ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static PreparedStatement getStm(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement pstm = null;
        if (sql.trim().startsWith("{")) {
            pstm = con.prepareCall(sql);
        } else {
            pstm = con.prepareStatement(sql);
        }

        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i + 1, args[i]);
        }
        return pstm;
    }

    public static void update(String sql, Object... args) {
        try {
            PreparedStatement pstmt = getStm(sql, args);
            try {
                pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement pstm = getStm(sql, args);
            return pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Connection ketNoi() {
        try {
            String user = "sa";
            String pass = "songlong";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLTV";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
