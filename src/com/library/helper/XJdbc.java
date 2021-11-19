/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helper;

import java.sql.*;

public class XJdbc {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/qltv";
    private static String user = "root";
    private static String pass = "130602";
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            
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
            PreparedStatement pstmt= getStm(sql, args);
            try{
                pstmt.executeUpdate();
            }finally{
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
}
