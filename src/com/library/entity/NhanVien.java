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
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String matKhau;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String Sdt;
    private String email;
    private String diaChi;
    private String vaiTro;
    private boolean trangThai;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }
    
    public NhanVien(String maNV, String tenNV, String matKhau, boolean gioiTinh, Date ngaySinh, String Sdt, String email, String diaChi, String vaiTro, boolean trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.Sdt = Sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
