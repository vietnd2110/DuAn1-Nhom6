
package com.library.entity;

import java.util.Date;

public class CTPhieuMuon {
    private PhieuMuon pm;
    private String maSach;
    private Date ngayThucTra;
    private float tienPhat;
    private String tinhTrangSach;
    

    public CTPhieuMuon() {
    }

    public CTPhieuMuon(PhieuMuon pm, String maSach, Date ngayThucTra, String tinhTrangSach, float tienPhat) {
        this.pm = pm;
        this.maSach = maSach;
        this.ngayThucTra = ngayThucTra;
        this.tinhTrangSach = tinhTrangSach;
        this.tienPhat = tienPhat;
    }

    public PhieuMuon getPm() {
        return pm;
    }

    public void setPm(PhieuMuon pm) {
        this.pm = pm;
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
