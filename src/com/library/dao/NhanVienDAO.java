/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.dao;

import com.library.entity.NhanVien;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NhanVienDAO extends LibraryDAO<NhanVien, String> {
    
    final String insert_SQL = "insert into NhanVien (maNV, tenNV, matKhau, gioiTinh, NgaySinh, SDT, email, diaChi, vaiTro, trangThai) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String update_SQL = "update NhanVien set tenNV = ?, matKhau = ?, gioiTinh = ?, NgaySinh = ?, SDT = ?, email = ?, diaChi = ?, vaiTro = ?, trangThai = ? where maNV = ?";
    final String select_All_SQL = "select * from NhanVien";
    final String select_ByID_SQL = "select * from NhanVien  where maNV = ?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.update(insert_SQL, entity.getMaNV(), entity.getTenNV(), entity.getMatKhau(), 
                entity.getGioiTinh(), entity.getNgaySinh(), entity.getSdt(), entity.getEmail(), 
                entity.getDiaChi(), entity.getVaiTro(), entity.getTrangThai());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.update(update_SQL, entity.getTenNV(), entity.getMatKhau(), 
                entity.getGioiTinh(), entity.getNgaySinh(), entity.getSdt(), entity.getEmail(), 
                entity.getDiaChi(), entity.getVaiTro(), entity.getTrangThai(), entity.getMaNV());
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySQL(select_All_SQL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = selectBySQL(select_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> listNV = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String matKhau = rs.getString(3);
                boolean gioiTinh = rs.getBoolean(4);
                Date ngaySinh = rs.getDate(5);
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String diaChi = rs.getString(8);
                boolean vaiTro = rs.getBoolean(9);
                boolean trangThai = rs.getBoolean(10);
                
                listNV.add(new NhanVien(maNV, tenNV, matKhau, gioiTinh, ngaySinh, sdt, email, diaChi, vaiTro, trangThai));
            }
        } catch (Exception e) {
        }
        return listNV;
    }
}
