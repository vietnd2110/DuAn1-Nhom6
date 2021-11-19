
package com.library.entity;

import java.util.Date;


public class PhieuMuon {
   private int maPm;
   private KhachHang kh;
   private NhanVien nv;
   private Date ngayMuon;
   private Date ngayTra;
   private float soTienCoc;
   private String trangThai;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPm) {
        this.maPm = maPm;
    }

    public PhieuMuon(int maPm, KhachHang kh, NhanVien nv, Date ngayMuon, Date ngayTra, float soTienCoc, String trangThai) {
        this.maPm = maPm;
        this.kh = kh;
        this.nv = nv;
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

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
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
}
