
package com.library.entity;

public class PhieuTra {
    private int maPM;
    private String maSach;
    private float soTienCoc;
    private float tienPhat;
    
    
    public float getTienHoanTra() {
        float soTienHT = 0;
        if (tienPhat < soTienCoc) {
            return soTienHT = soTienCoc - tienPhat;
        }
        return 0;
    }
    
    public float getTienThanhToan() {
        float soTienTT = 0;
        if (tienPhat > soTienCoc) {
            return soTienTT = tienPhat - soTienCoc;
        }
        return 0;
    }

    public PhieuTra() {
    }

    public PhieuTra(int maPM, String maSach, float soTienCoc, float tienPhat) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.soTienCoc = soTienCoc;
        this.tienPhat = tienPhat;
    }

    public int getMaPM() {
        return maPM;
    }

    public String getMaSach() {
        return maSach;
    }

    public float getSoTienCoc() {
        return soTienCoc;
    }

    public float getTienPhat() {
        return tienPhat;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setSoTienCoc(float soTienCoc) {
        this.soTienCoc = soTienCoc;
    }

    public void setTienPhat(float tienPhat) {
        this.tienPhat = tienPhat;
    }
    
}


