
package com.library.dao;

import com.library.entity.PhieuTra;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuTraDAO extends LibraryDAO<PhieuTra, Integer> {
    
    final String INSERT_SQL = "INSERT INTO PHIEUTRA(MAPM, MASACH, SOTIENCOC, TIENPHAT)VALUES(?, ?, ?, ?)";
    final String UPDATE_SQL = "update PHIEUTRA set SOTIENCOC=?, TIENPHAT=? where MAPM=? and MASACH=?";
    final String SELECT_ALL_SQL = "select *from PHIEUTRA";
    final String SELECT_BY_ID_SQL = "select *from PHIEUTRA where MAPM=?";

    @Override
    public void insert(PhieuTra pt) {
        XJdbc.update(INSERT_SQL, pt.getMaPM(), pt.getMaSach(), pt.getSoTienCoc(), pt.getTienPhat());
    }

    @Override
    public void update(PhieuTra pt) {
        XJdbc.update(UPDATE_SQL, pt.getSoTienCoc(), pt.getTienPhat(), pt.getMaPM(), pt.getMaSach());
    }

    @Override
    public List<PhieuTra> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public PhieuTra selectByID(Integer id) {
        List<PhieuTra> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhieuTra> selectBySQL(String sql, Object... args) {
        List<PhieuTra> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                PhieuTra pt = new PhieuTra();

                pt.setMaPM(rs.getInt("MAPM"));
                pt.setMaSach(rs.getString("MASACH"));
                pt.setSoTienCoc(rs.getFloat("SOTIENCOC"));
                pt.setTienPhat(rs.getFloat("TIENPHAT"));                
                list.add(pt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<PhieuTra> selectByPM(Integer maPM) {
        return selectBySQL(SELECT_BY_ID_SQL, maPM);
    }
    
}
