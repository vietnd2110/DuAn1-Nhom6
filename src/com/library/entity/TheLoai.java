
package com.library.entity;


public class TheLoai {
    private String maTl;
    private String tenTl;

    public TheLoai() {
    }

    public TheLoai(String maTl, String tenTl) {
        this.maTl = maTl;
        this.tenTl = tenTl;
    }

    public String getMaTl() {
        return maTl;
    }

    public void setMaTl(String maTl) {
        this.maTl = maTl;
    }

    public String getTenTl() {
        return tenTl;
    }

    public void setTenTl(String tenTl) {
        this.tenTl = tenTl;
    }

    @Override
    public String toString() {
        return maTl;
    }
    
    
}
