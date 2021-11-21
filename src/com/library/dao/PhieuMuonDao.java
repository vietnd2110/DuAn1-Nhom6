package com.library.dao;

import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import com.library.entity.PhieuMuon;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDao extends LibraryDAO<PhieuMuon, Integer> {

    final String INSERT_SQL = "INSERT INTO PHIEUMUON(MAKH,MANV,NGAYMUON,NGAYTRA,SOTIENCOC,TRANGTHAI, SOLUONGMUON)VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "update PHIEUMUON set MAKH = ?, MANV = ?, NGAYMUON = ?, NGAYTRA = ?, SOTIENCOC = ?, TRANGTHAI = ? where MAPM = ?";
    final String SELECT_ALL_SQL = "select *from PHIEUMUON";
    final String SELECT_BY_ID_SQL = "select *from PHIEUMUON where MAPM=?";
    final String update_SoLuongMuon = "update PHIEUMUON set SOLUONGMUON = SOLUONGMUON + 1 where MAPM = ?";
    final String update_TruSoLuongMuon = "update PHIEUMUON set SOLUONGMUON = SOLUONGMUON - 1 where MAPM = ?";

    @Override
    public void insert(PhieuMuon pm) {
        XJdbc.update(INSERT_SQL, pm.getMaKH(), pm.getMaNV(), pm.getNgayMuon(), pm.getNgayTra(),
                pm.getSoTienCoc(), pm.getTrangThai(), pm.getSoLuongMuon());
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
                pm.setSoLuongMuon(rs.getInt("SOLUONGMUON"));
                list.add(pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateSLMuon(String maPM) {
        XJdbc.update(update_SoLuongMuon, maPM);
    }
    
    public void updateTruSLMuon(String maPM) {
        XJdbc.update(update_TruSoLuongMuon, maPM);
    }
    
    public List<PhieuMuon> timKiemPM(String maPM) {
        return selectBySQL(SELECT_BY_ID_SQL, maPM);
    }

}
