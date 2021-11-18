
package com.library.dao;

import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import com.library.entity.PhieuMuon;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PhieuMuonDao extends LibraryDAO<PhieuMuon, String>{

    final String INSERT_SQL="insert into PHIEUMUON values(?,?,?,?,?,?,?)";
    final String UPDATE_SQL="update PHIEUMUON set MAKH=?, MANV=?, NGAYMUON=?, NGAYTRA=?, SOTIENCOC=?, TRANGTHAI=? "
                              + "where MAPM=?";
    final String DELETE_SQL="delete from PHIEUMUON where MAPM=?"; 
    final String SELECT_ALL_SQL="select *from PHIEUMUON";
    final String SELECT_BY_ID_SQL="select *from PHIEUMUON where MAPM=?"; 
    
    @Override
    public void insert(PhieuMuon pm) {
        XJdbc.update(INSERT_SQL, pm.getMaPm(),pm.getKh().getMaKH(),pm.getNv().getMaNV(),pm.getNgayMuon(),pm.getNgayTra(),
      pm.getSoTienCoc(),pm.getTrangThai());
    }

    @Override
    public void update(PhieuMuon pm) {
      XJdbc.update(UPDATE_SQL,pm.getKh().getMaKH(),pm.getNv().getMaNV(),pm.getNgayMuon(),pm.getNgayTra(),
      pm.getSoTienCoc(),pm.getTrangThai(),pm.getMaPm());
    }


    @Override
    public List<PhieuMuon> selectAll() {
     return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public PhieuMuon selectByID(String key) {
       List<PhieuMuon> list=selectBySQL(SELECT_BY_ID_SQL, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhieuMuon> selectBySQL(String sql, Object... args) {
       List<PhieuMuon> list=new ArrayList<>();
        try {
            ResultSet rs=XJdbc.query(sql, args);
            while (rs.next()) {                
                PhieuMuon pm=new PhieuMuon();
                
                pm.setMaPm(rs.getString("MAPM"));
                pm.setKh(new KhachHang(rs.getString("MAKH")));
                pm.setNv(new NhanVien(rs.getString("MANV")));
                pm.setNgayMuon(rs.getDate("NGAYMUON"));
                pm.setNgayTra(rs.getDate("NGAYTRA"));
                pm.setSoTienCoc(rs.getFloat("SOTIENCOC"));
                pm.setTrangThai(rs.getString("TrangThai"));
                list.add(pm);     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   
}
