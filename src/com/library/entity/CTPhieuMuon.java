
package com.library.entity;

import java.util.Date;

public class CTPhieuMuon {
    private int maPM;
    private String maSach;
    private Date ngayThucTra;
    private String tinhTrangSach;
    private float tienPhat;

    public CTPhieuMuon() {
    }

    public CTPhieuMuon(int maPM, String maSach, Date ngayThucTra, String tinhTrangSach, float tienPhat) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.ngayThucTra = ngayThucTra;
        this.tinhTrangSach = tinhTrangSach;
        this.tienPhat = tienPhat;
    }

    

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }
    
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayThucTra() {
        return ngayThucTra;
    }

    public void setNgayThucTra(Date ngayThucTra) {
        this.ngayThucTra = ngayThucTra;
    }

    public String getTinhTrangSach() {
        return tinhTrangSach;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }   

    public float getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(float tienPhat) {
        this.tienPhat = tienPhat;
    }
}
