package com.library.entity;

import java.util.Date;

public class Sach {
    private String maSach;
    private TheLoai tl;
    private String nXB;
    private String tenSach;
    private String noiDat;
    private double giaTien;
    private String tacGia;
    private Date namXB;

    public Sach() {
    }

    public Sach(String maSach, TheLoai tl, String nXB, String tenSach, String noiDat, double giaTien, String tacGia, Date namXB) {
        this.maSach = maSach;
        this.tl = tl;
        this.nXB = nXB;
        this.tenSach = tenSach;
        this.noiDat = noiDat;
        this.giaTien = giaTien;
        this.tacGia = tacGia;
        this.namXB = namXB;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public TheLoai getTl() {
        return tl;
    }

    public void setTl(TheLoai tl) {
        this.tl = tl;
    }

    public String getnXB() {
        return nXB;
    }

    public void setnXB(String nXB) {
        this.nXB = nXB;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNoiDat() {
        return noiDat;
    }

    public void setNoiDat(String noiDat) {
        this.noiDat = noiDat;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Date getNamXB() {
        return namXB;
    }

    public void setNamXB(Date namXB) {
        this.namXB = namXB;
    }

   
   
}
