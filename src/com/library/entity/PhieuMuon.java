
package com.library.entity;

import java.util.Date;


public class PhieuMuon {
   private int maPm;
   private String maKH;
   private String maNV;
   private Date ngayMuon;
   private Date ngayTra;
   private float soTienCoc;
   private String trangThai;
   

    public PhieuMuon() {
    }

    public PhieuMuon(int maPm, String maKH, String maNV, Date ngayMuon, Date ngayTra, float soTienCoc, String trangThai) {
        this.maPm = maPm;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
        this.soTienCoc = soTienCoc;
        this.trangThai = trangThai;
    }

    public int getMaPm() {
        return maPm;
    }

    public void setMaPm(int maPm) {
        this.maPm = maPm;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public float getSoTienCoc() {
        return soTienCoc;
    }

    public void setSoTienCoc(float soTienCoc) {
        this.soTienCoc = soTienCoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.maPm + "";
    }
    
}
