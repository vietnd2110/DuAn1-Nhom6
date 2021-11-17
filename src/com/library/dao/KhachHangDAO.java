/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.dao;

import com.library.entity.KhachHang;
import com.library.helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class KhachHangDAO extends LibraryDAO<KhachHang, String> {
    
    final String insert_SQL = "insert into KhachHang (maKH, tenKH, matKhau, gioiTinh, NgaySinh, SDT, email, diaChi, maNV, trangThai) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String update_SQL = "update KhachHang set tenKH = ?, matKhau = ?, gioiTinh = ?, NgaySinh = ?, SDT = ?, email = ?, diaChi = ?, maNV = ?, trangThai = ? where maKH = ?";
    final String select_All_SQL = "select * from KhachHang";
    final String select_ByID_SQL = "select * from KhachHang where maKH = ?";

    @Override
    public void insert(KhachHang entity) {
        XJdbc.update(insert_SQL, entity.getMaKH(), entity.getTenKH(), entity.getMatKhau(), 
                entity.getGioiTinh(), entity.getNgaySinh(), entity.getSdt(), entity.getEmail(), 
                entity.getDiaChi(), entity.getMaNV(), entity.getTrangThai());
    }

    @Override
    public void update(KhachHang entity) {
        XJdbc.update(update_SQL, entity.getTenKH(), entity.getMatKhau(), 
                entity.getGioiTinh(), entity.getNgaySinh(), entity.getSdt(), entity.getEmail(), 
                entity.getDiaChi(), entity.getMaNV(), entity.getTrangThai(),  entity.getMaKH());
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySQL(select_All_SQL);
    }

    @Override
    public KhachHang selectByID(String id) {
        List<KhachHang> list = selectBySQL(select_ByID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> listKH = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while(rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String matKhau = rs.getString(3);
                boolean gioiTinh = rs.getBoolean(4);
                Date ngaySinh = rs.getDate(5);
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String diaChi = rs.getString(8);
                String maNV = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                
                listKH.add(new KhachHang(maKH, tenKH, matKhau, gioiTinh, ngaySinh, sdt, email, diaChi, maNV, trangThai));
            }
        } catch (Exception e) {
        }
        return listKH;
    } 
}
