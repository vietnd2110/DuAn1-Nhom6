/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.helper;

import com.library.entity.KhachHang;
import com.library.entity.NhanVien;

/**
 *
 * @author LENOVO
 */
public class XAuther {
    public static NhanVien USER = null;
    
    public static KhachHang UserKH = null;

    public static void clear() {
        XAuther.USER = null;
    }

    public static boolean isLogin() {
        return XAuther.USER != null;
    }
    
    public static boolean isLoginKH() {
        return XAuther.UserKH != null;
    }
    
    public static boolean isManager() {
        return XAuther.isLogin() && USER.getVaiTro();
    }
}
