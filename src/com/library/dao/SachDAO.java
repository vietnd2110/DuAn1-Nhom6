package com.library.dao;

import com.library.helper.XJdbc;
import com.library.entity.Sach;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SachDAO extends LibraryDAO<Sach, String>{
    final String insert_SQL = "insert into SACH (MaSach, MaTL, NXB, TenSach, NoiDat, GiaTien, TacGia, NamXB) values (?, ?, ?, ?, ?, ?, ?, ?)";
    final String update_SQL = "update SACH set MaTL = ?, NXB = ?, TenSach = ?, NoiDat = ?, GiaTien = ?, TacGia = ?, NamXB = ? where MaSach = ?";
    final String select_All_SQL = "select * from SACH";
    final String select_ByID_SQL = "select * from SACH where MaSach = ?";
    
    @Override
    public void insert(Sach entity) {
        XJdbc.update(insert_SQL, entity.getMaSach(), entity.getMaTL(), entity.getnXB(), entity.getTenSach(), entity.getNoiDat(),
                    entity.getGiaTien(), entity.getTacGia(), entity.getNamXB());
    }

    @Override
    public void update(Sach entity) {
       XJdbc.update(update_SQL, entity.getMaSach(), entity.getMaTL(), entity.getnXB(), entity.getTenSach(), entity.getNoiDat(),
                    entity.getGiaTien(), entity.getTacGia(), entity.getNamXB());
    }

    @Override
    public List<Sach> selectAll() {
        return selectBySQL(select_All_SQL);
    }

    @Override
    public Sach selectByID(String id) {
          List<Sach> list = selectBySQL(select_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Sach> selectBySQL(String sql, Object... args) {
         List<Sach> listSach = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()) {
                String maSach = rs.getString(1);
                String maTL = rs.getString(2);
                String nXB = rs.getString(3);
                String tenSach = rs.getString(4);
                String noiDat = rs.getString(5);
                Double giaTien = rs.getDouble(6);
                String tacGia = rs.getString(7);
                Date namXB = rs.getDate(8);
                listSach.add(new Sach(maSach, maTL, nXB, tenSach, noiDat, giaTien, tacGia, namXB));
            }
        } catch (Exception e) {
        }
        return listSach;
        
    }
    
    
}
