/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.form.kh;

import com.library.form.login.form_login_DoiMatKhau;
import com.library.form.login.form_login_DangNhap;
import com.library.helper.XImages;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author manh truong
 */
public class form_khachhang_MainFormKhachHang extends javax.swing.JFrame {

    /**
     * Creates new form form
     */
    public form_khachhang_MainFormKhachHang() {
        initComponents();
        //set màu cho menu item
        setColor(mniMuonTraSach);
        setColor(mniDx);
        setColor(mniDoiMk);
        init();
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Thư Viện Đại Học Hà Nội - Hanoi University Library");
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss - a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lblTime.setText(format.format(new Date()));
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        bntDangXuat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnTTmuonSach = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnNoiQuy = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniDx = new javax.swing.JMenuItem();
        mniDoiMk = new javax.swing.JMenuItem();
        mn1 = new javax.swing.JMenu();
        mniMuonTraSach = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(219, 233, 245));

        jPanel1.setBackground(new java.awt.Color(88, 128, 162));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/android-chrome-512x512.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(225, 225, 225))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(new java.awt.Color(219, 233, 245));
        jToolBar1.setRollover(true);

        bntDangXuat.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        bntDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_exit_36px_1 1.png"))); // NOI18N
        bntDangXuat.setText("Đăng xuất");
        bntDangXuat.setFocusable(false);
        bntDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bntDangXuat.setMargin(new java.awt.Insets(5, 25, 2, 20));
        bntDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bntDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(bntDangXuat);
        jToolBar1.add(jSeparator1);

        btnTTmuonSach.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        btnTTmuonSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reading_45px 1.png"))); // NOI18N
        btnTTmuonSach.setText("Thông tin mượn sách");
        btnTTmuonSach.setFocusable(false);
        btnTTmuonSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTTmuonSach.setMargin(new java.awt.Insets(2, 25, 2, 25));
        btnTTmuonSach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTTmuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTmuonSachActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTTmuonSach);
        jToolBar1.add(jSeparator2);

        btnNoiQuy.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        btnNoiQuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Amazon_Kindle_45px 1.png"))); // NOI18N
        btnNoiQuy.setText("Nội quy");
        btnNoiQuy.setFocusable(false);
        btnNoiQuy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNoiQuy.setMargin(new java.awt.Insets(2, 25, 2, 20));
        btnNoiQuy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnNoiQuy);

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_school_45px 1.png"))); // NOI18N
        jLabel2.setText("Thư viện Đại học Hà Nội");

        lblTime.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/DongHo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(240, 20, 255));
        jMenu1.setText("Hệ thống");
        jMenu1.setMargin(new java.awt.Insets(0, 10, 0, 10));

        mniDx.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDx.setBackground(new java.awt.Color(102, 102, 102));
        mniDx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniDx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_export_45px 1.png"))); // NOI18N
        mniDx.setText("Đăng xuất");
        mniDx.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniDx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDxActionPerformed(evt);
            }
        });
        jMenu1.add(mniDx);

        mniDoiMk.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mniDoiMk.setBackground(new java.awt.Color(102, 102, 102));
        mniDoiMk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniDoiMk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_change_45px 1.png"))); // NOI18N
        mniDoiMk.setText("Đổi mật khẩu");
        mniDoiMk.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniDoiMk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoiMkActionPerformed(evt);
            }
        });
        jMenu1.add(mniDoiMk);

        jMenuBar1.add(jMenu1);

        mn1.setBackground(new java.awt.Color(204, 0, 255));
        mn1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mn1.setText("Thông tin");
        mn1.setMargin(new java.awt.Insets(0, 10, 0, 10));

        mniMuonTraSach.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_MASK));
        mniMuonTraSach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniMuonTraSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_important_book_36px_2 1.png"))); // NOI18N
        mniMuonTraSach.setText("Mượn sách");
        mniMuonTraSach.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        mniMuonTraSach.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniMuonTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniMuonTraSachActionPerformed(evt);
            }
        });
        mn1.add(mniMuonTraSach);

        jMenuBar1.add(mn1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniDoiMkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMkActionPerformed
        form_login_DoiMatKhau dmk = new form_login_DoiMatKhau();
        dmk.setVisible(true);
    }//GEN-LAST:event_mniDoiMkActionPerformed

    private void bntDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntDangXuatActionPerformed
        int y = JOptionPane.showConfirmDialog(
                this, "Bạn có muốn đăng xuất không ?", "Đăng xuất", JOptionPane.YES_NO_OPTION);

        if (y == JOptionPane.YES_OPTION) {           
            JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
            form_login_DangNhap l = new form_login_DangNhap();
            l.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_bntDangXuatActionPerformed

    private void mniDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDxActionPerformed
        int y = JOptionPane.showConfirmDialog(
                this, "Bạn có muốn đăng xuất không ?", "Đăng xuất", JOptionPane.YES_NO_OPTION);

        if (y == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
            form_login_DangNhap l = new form_login_DangNhap();
            l.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_mniDxActionPerformed

    private void mniMuonTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniMuonTraSachActionPerformed
        form_khachhang_MuonSach fs = new form_khachhang_MuonSach();
        fs.setVisible(true);
    }//GEN-LAST:event_mniMuonTraSachActionPerformed

    private void btnTTmuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTmuonSachActionPerformed
        form_khachhang_MuonSach fs = new form_khachhang_MuonSach();
        fs.setVisible(true);
    }//GEN-LAST:event_btnTTmuonSachActionPerformed

    void setColor(JMenuItem item) {
        item.setOpaque(true);
        item.setBackground(Color.gray);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_khachhang_MainFormKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_khachhang_MainFormKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_khachhang_MainFormKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_khachhang_MainFormKhachHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_khachhang_MainFormKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntDangXuat;
    private javax.swing.JButton btnNoiQuy;
    private javax.swing.JButton btnTTmuonSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JMenu mn1;
    private javax.swing.JMenuItem mniDoiMk;
    private javax.swing.JMenuItem mniDx;
    private javax.swing.JMenuItem mniMuonTraSach;
    // End of variables declaration//GEN-END:variables
}
