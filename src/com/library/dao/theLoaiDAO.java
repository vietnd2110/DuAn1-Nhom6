
package com.library.dao;

import com.library.entity.TheLoai;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class theLoaiDAO extends LibraryDAO<TheLoai, String>{
    final String selec_by_id = "select *from THELOAI where MATL=?";
    final String select_All_SQL = "select *from THELOAI";

    @Override
    public void insert(TheLoai entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TheLoai entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TheLoai selectByID(String id) {
     List<TheLoai> list=selectBySQL(selec_by_id, id);
          if (list.isEmpty()) {
                return null;
        }
          return list.get(0);
    }

    @Override
    public List<TheLoai> selectBySQL(String sql, Object... args) {
        List<TheLoai> list=new ArrayList<>();
        try {
            ResultSet rs=XJdbc.query(sql, args);
            while (rs.next()) {                
                TheLoai tl=new TheLoai();
                tl.setMaTl(rs.getString(1));
                tl.setTenTl(rs.getString(2));
                list.add(tl);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public List<TheLoai> selectAll() {
       return selectBySQL(select_All_SQL);
    }
    
}
