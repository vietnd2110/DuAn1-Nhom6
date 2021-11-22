/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.dao;

import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ThongKeDao {
    
    private List<Object[]> getListArray(String sql, String [] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {                
                Object[] ob = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    ob[i] = rs.getObject(cols[i]);
                }
                list.add(ob);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
    
    public List<Object[]> getSachMuonTraSach(String trangThai ){
        String sql = "{CALL  SACHMUON_TRASACH(?)}";
        String cols[] = {"Mã Sách","Tên Sách","Thể Loại","Tác Giả","Năm XB","NXB","Ngày Mượn","Số Lượng Mượn"};
        return getListArray(sql, cols);
    }
     public List<Object[]> getSachMuonTheoNgay(int ngay ){
        String sql = "{CALL  SACHMUON_THEONGAY(?)}";
        String cols[] = {"Mã Sách","Tên Sách","Thể Loại","Tác Giả","Năm XB","NXB","Ngày Mượn","Số Lượng Mượn"};
        return getListArray(sql, cols);
    }
      public List<Object[]> getTienPhatTheoThang(int thang ){
        String sql = "{CALL  TIENPHAT_THEOTHANG(?)}";
        String cols[] = {"Mã Kh","Tên kh","Giới Tính","Ngày Sinh","Sdt","Tiền Phạt"};
        return getListArray(sql, cols);
    }
    
}
