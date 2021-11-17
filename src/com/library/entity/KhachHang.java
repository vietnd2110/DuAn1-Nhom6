/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.entity;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String matKhau;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String Sdt;
    private String email;
    private String diaChi;
    private String maNV;
    private boolean trangThai;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String matKhau, boolean gioiTinh, Date ngaySinh, String Sdt, String email, String diaChi, String maNV, boolean trangThai) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.Sdt = Sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.maNV = maNV;
        this.trangThai = trangThai;
    }
    
    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getMaNV() {
        return maNV;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
