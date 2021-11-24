package com.library.dao;

import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import com.library.entity.PhieuMuon;
import com.library.helper.XJdbc;
import com.library.helper.XMgsbox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao extends LibraryDAO<PhieuMuon, Integer> {

    final String INSERT_SQL = "INSERT INTO PHIEUMUON(MAKH,MANV,NGAYMUON,NGAYTRA,SOTIENCOC,TRANGTHAI, SOLUONGMUON)VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "update PHIEUMUON set MAKH=?, MANV=?, NGAYMUON=?, NGAYTRA=?, SOTIENCOC=?, TRANGTHAI=? where MAPM=?";
    final String SELECT_ALL_SQL = "select *from PHIEUMUON";
    final String SELECT_BY_ID_SQL = "select *from PHIEUMUON where MAPM=?";
    final String update_TrangThai  = "update PHIEUMUON set TRANGTHAI=? where MAPM=?";
    final String Select_Top1_MaPM = "select top 1 MAPM from PHIEUMUON order by MAPM desc";
    

    @Override
    public void insert(PhieuMuon pm) {
        XJdbc.update(INSERT_SQL, pm.getMaKH(), pm.getMaNV(), pm.getNgayMuon(), pm.getNgayTra(),
                pm.getSoTienCoc(), pm.getTrangThai());
    }

    @Override
    public void update(PhieuMuon pm) {
        XJdbc.update(UPDATE_SQL, pm.getMaKH(), pm.getMaNV(), pm.getNgayMuon(), pm.getNgayTra(), 
                pm.getSoTienCoc(), pm.getTrangThai(), pm.getMaPm());
    }

    @Override
    public List<PhieuMuon> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public PhieuMuon selectByID(Integer id) {
        List<PhieuMuon> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhieuMuon> selectBySQL(String sql, Object... args) {
        List<PhieuMuon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                PhieuMuon pm = new PhieuMuon();

                pm.setMaPm(rs.getInt("MAPM"));
                pm.setMaKH(rs.getString("MAKH"));
                pm.setMaNV(rs.getString("MANV"));
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
    
    public List<PhieuMuon> timKiemPM(String maPM) {
        return selectBySQL(SELECT_BY_ID_SQL, maPM);
    }
    
    public void updateTrangThai(PhieuMuon pm) {
        XJdbc.update(update_TrangThai, pm.getTrangThai(), pm.getMaPm());
    }

    public String selectTopMaPM() {
        Connection con = XJdbc.getConnection();
        String sql = Select_Top1_MaPM;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("MAPM");
            
        } catch (SQLException ex) {
            XMgsbox.alert(null, "Lỗi Truy Vấn Dữ Liệu");
            ex.printStackTrace();
        }
        return null;
    } 
}
