
package com.library.dao;

import com.library.entity.CTPhieuMuon;
import com.library.entity.PhieuMuon;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CTPhieuMuonDAO extends LibraryDAO<CTPhieuMuon, String>{

    final String INSERT_SQL="insert into CTPHIEUMUON values(?,?,?,?,?)";
    final String UPDATE_SQL="update CTPHIEUMUON set MASACH=?,TINHTRANGSACH=?,TIENPHAT=?,NGAYTHUCTRA=? where MAPM=?";
    final String SELECT_ALL_SQL="select *from CTPHIEUMUON";
    final String SELECT_BY_ID_SQL="select *from CTPHIEUMUON where MAPM=?"; 
    
    
    @Override
    public void insert(CTPhieuMuon ctpm) {
         XJdbc.update(INSERT_SQL, ctpm.getPm().getMaPm(),ctpm.getMaSach(),ctpm.getTinhTrangSach(),
         ctpm.getTienPhat(),ctpm.getNgayThucTra());
    }

    @Override
    public void update(CTPhieuMuon ctpm) {
        XJdbc.update(UPDATE_SQL, ctpm.getMaSach(),ctpm.getTinhTrangSach(),ctpm.getTienPhat(),ctpm.getNgayThucTra()
        ,ctpm.getPm().getMaPm());
    }

   
    @Override
    public List<CTPhieuMuon> selectAll() {
       return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public CTPhieuMuon selectByID(String key) {
       List<CTPhieuMuon> list=selectBySQL(SELECT_BY_ID_SQL,key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<CTPhieuMuon> selectBySQL(String sql, Object... args) {
      List<CTPhieuMuon> list=new ArrayList<>();
        try {
            ResultSet rs=XJdbc.query(sql, args);
            while (rs.next()) {                
                CTPhieuMuon c=new CTPhieuMuon();
                c.setPm(new PhieuMuon(rs.getInt(1)));
                c.setMaSach(rs.getString(2));
                c.setTinhTrangSach(rs.getString(3));
                c.setTienPhat(rs.getFloat(4));
                c.setNgayThucTra(rs.getDate(5));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
