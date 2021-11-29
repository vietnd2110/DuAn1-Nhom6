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
public class ThongKeDAO {
    
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
        String cols[] = {"Makh","TENKH","GIOITINH","SDT","MASACH","TENSACH","NGAYMUON","NGAYTRA"};
        return getListArray(sql, cols, trangThai);
    }
     public List<Object[]> getSachMuonTheoNgay(int ngay ){
        String sql = "{CALL  sp_muonsachTheoNgay(?)}";
        String cols[] = {"MASACH","TENSACH","TENTL","TACGIA","NAMXB","NXB","NGAYMUON","NGAYTRA"};
        return getListArray(sql, cols, ngay);
    }
      public List<Object[]> getTienPhatTheoThang(int thang ){
        String sql = "{CALL  TIENPHAT_THEOTHANG(?)}";
        String cols[] = {"MAKH","TENKH","GIOITINH","MASACH","TENSACH","NGAYMUON","NGAYTRA","NGAYTHUCTRA","TINHTRANGSACH","TIENPHAT"};
        return getListArray(sql, cols, thang);
    }
    
}
