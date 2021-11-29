/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.form;

import com.library.helper.XAuther;
import com.library.helper.XImages;
import com.library.helper.XMgsbox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class main_form_QL extends javax.swing.JFrame {

    public main_form_QL() {
        initComponents();
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnThongKeMuonTra = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnSachTaiLieu = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnMuonTraSach = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniDx = new javax.swing.JMenuItem();
        mniDoiMk = new javax.swing.JMenuItem();
        mn1 = new javax.swing.JMenu();
        mnu1 = new javax.swing.JMenuItem();
        mnu2 = new javax.swing.JMenuItem();
        mniPm = new javax.swing.JMenuItem();
        mniSachTaiLieu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniThongKeMuonTs = new javax.swing.JMenuItem();
        mniSachMuonTheoDays = new javax.swing.JMenuItem();
        mniTTphat = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem6.setText("jMenuItem6");

        jMenuItem1.setText("jMenuItem1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(230, 202, 133));

        jPanel1.setBackground(new java.awt.Color(238, 232, 171));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/android-chrome-512x512.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(217, 217, 217))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(new java.awt.Color(230, 202, 133));
        jToolBar1.setRollover(true);

        btnDangXuat.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_exit_36px_2 1.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setMargin(new java.awt.Insets(5, 25, 2, 20));
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);
        jToolBar1.add(jSeparator1);

        btnThongKeMuonTra.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        btnThongKeMuonTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reading_45px 1.png"))); // NOI18N
        btnThongKeMuonTra.setText("Thống kê mượn trả sách");
        btnThongKeMuonTra.setFocusable(false);
        btnThongKeMuonTra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKeMuonTra.setMargin(new java.awt.Insets(2, 25, 2, 25));
        btnThongKeMuonTra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongKeMuonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeMuonTraActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongKeMuonTra);
        jToolBar1.add(jSeparator2);

        btnSachTaiLieu.setFont(new java.awt.Font("Bahnschrift", 0, 11)); // NOI18N
        btnSachTaiLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_books_36px_2 1.png"))); // NOI18N
        btnSachTaiLieu.setText("Sách tài liệu");
        btnSachTaiLieu.setFocusable(false);
        btnSachTaiLieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSachTaiLieu.setMargin(new java.awt.Insets(2, 25, 2, 20));
        btnSachTaiLieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSachTaiLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSachTaiLieuActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSachTaiLieu);
        jToolBar1.add(jSeparator3);

        btnMuonTraSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Amazon_Kindle_45px 1.png"))); // NOI18N
        btnMuonTraSach.setText("Mượn trả sách");
        btnMuonTraSach.setFocusable(false);
        btnMuonTraSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMuonTraSach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMuonTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonTraSachActionPerformed(evt);
            }
        });
        jToolBar1.add(btnMuonTraSach);

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
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setBorder(null);

        jMenu1.setBackground(new java.awt.Color(240, 20, 255));
        jMenu1.setText("Hệ thống");
        jMenu1.setMargin(new java.awt.Insets(0, 10, 0, 10));

        mniDx.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniDx.setBackground(new java.awt.Color(102, 102, 102));
        mniDx.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniDx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_exit_36px_2 1.png"))); // NOI18N
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
        mn1.setText("Quản Lý");
        mn1.setMargin(new java.awt.Insets(0, 12, 0, 12));

        mnu1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_MASK));
        mnu1.setBackground(new java.awt.Color(255, 102, 204));
        mnu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_people_36px 1.png"))); // NOI18N
        mnu1.setText("Nhân viên");
        mnu1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mnu1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mnu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu1ActionPerformed(evt);
            }
        });
        mn1.add(mnu1);

        mnu2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_MASK));
        mnu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mnu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_people_36px 1.png"))); // NOI18N
        mnu2.setText("Khách Hàng");
        mnu2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mnu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu2ActionPerformed(evt);
            }
        });
        mn1.add(mnu2);

        mniPm.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK));
        mniPm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniPm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_user_manual_36px 1.png"))); // NOI18N
        mniPm.setText("Mượn, trả sách");
        mniPm.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniPm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPmActionPerformed(evt);
            }
        });
        mn1.add(mniPm);

        mniSachTaiLieu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        mniSachTaiLieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniSachTaiLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_tasks_36px 1.png"))); // NOI18N
        mniSachTaiLieu.setText("Sách tài liệu");
        mniSachTaiLieu.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniSachTaiLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSachTaiLieuActionPerformed(evt);
            }
        });
        mn1.add(mniSachTaiLieu);

        jMenuBar1.add(mn1);

        jMenu3.setText("Thống kê");
        jMenu3.setMargin(new java.awt.Insets(0, 10, 0, 10));

        mniThongKeMuonTs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK));
        mniThongKeMuonTs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniThongKeMuonTs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reading_36px_1 1.png"))); // NOI18N
        mniThongKeMuonTs.setText("Mượn trả sách");
        mniThongKeMuonTs.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniThongKeMuonTs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongKeMuonTsActionPerformed(evt);
            }
        });
        jMenu3.add(mniThongKeMuonTs);

        mniSachMuonTheoDays.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.SHIFT_MASK));
        mniSachMuonTheoDays.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniSachMuonTheoDays.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_36px_2 1.png"))); // NOI18N
        mniSachMuonTheoDays.setText("Sách mượn theo ngày");
        mniSachMuonTheoDays.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniSachMuonTheoDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSachMuonTheoDaysActionPerformed(evt);
            }
        });
        jMenu3.add(mniSachMuonTheoDays);

        mniTTphat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.SHIFT_MASK));
        mniTTphat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniTTphat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_money_circulation_36px 1.png"))); // NOI18N
        mniTTphat.setText("Tổng tiền phạt theo tháng");
        mniTTphat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        mniTTphat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTTphatActionPerformed(evt);
            }
        });
        jMenu3.add(mniTTphat);

        jMenuBar1.add(jMenu3);

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

    private void mnu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu1ActionPerformed
        if (XAuther.isManager()) {
            main_form_QLNV nv = new main_form_QLNV();
            nv.setVisible(true);
        } else {
            XMgsbox.alert(this, "Bạn không có quyền vào mục này!");
        }
    }//GEN-LAST:event_mnu1ActionPerformed

    private void mniDoiMkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMkActionPerformed
        DoiMatKhau_form dmk = new DoiMatKhau_form();
        dmk.setVisible(true);
    }//GEN-LAST:event_mniDoiMkActionPerformed

    private void mniDxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDxActionPerformed
        int y = JOptionPane.showConfirmDialog(
                this, "Bạn có muốn đăng xuất không ?", "Đăng xuất", JOptionPane.YES_NO_OPTION);

        if (y == JOptionPane.YES_OPTION) {
            this.dispose();
            JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
            login_Form l = new login_Form();
            l.setVisible(true);
        }
    }//GEN-LAST:event_mniDxActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        int y = JOptionPane.showConfirmDialog(
                this, "Bạn có muốn đăng xuất không ?", "Đăng xuất", JOptionPane.YES_NO_OPTION);

        if (y == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
            login_Form l = new login_Form();
            l.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void mnu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu2ActionPerformed
        form_Qlkh kh = new form_Qlkh();
        kh.setVisible(true);
    }//GEN-LAST:event_mnu2ActionPerformed

    private void mniPmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPmActionPerformed
        ql_muonTraSach_form qlmt = new ql_muonTraSach_form();
        qlmt.setVisible(true);
    }//GEN-LAST:event_mniPmActionPerformed

    private void mniSachTaiLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSachTaiLieuActionPerformed
        ql_sach_tai_lieu qlS = new ql_sach_tai_lieu();
        qlS.setVisible(true);
    }//GEN-LAST:event_mniSachTaiLieuActionPerformed

    private void btnSachTaiLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSachTaiLieuActionPerformed
        ql_sach_tai_lieu qlS = new ql_sach_tai_lieu();
        qlS.setVisible(true);
    }//GEN-LAST:event_btnSachTaiLieuActionPerformed

    private void btnMuonTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonTraSachActionPerformed
        ql_muonTraSach_form qlmt = new ql_muonTraSach_form();
        qlmt.setVisible(true);
    }//GEN-LAST:event_btnMuonTraSachActionPerformed

    private void mniThongKeMuonTsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongKeMuonTsActionPerformed
        thongKe_SachTheoNgay_form tks = new thongKe_SachTheoNgay_form();
        tks.setVisible(true);
    }//GEN-LAST:event_mniThongKeMuonTsActionPerformed

    private void mniSachMuonTheoDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSachMuonTheoDaysActionPerformed
        thongKe_SachTheoNgay_form tks = new thongKe_SachTheoNgay_form();
        tks.setVisible(true);
    }//GEN-LAST:event_mniSachMuonTheoDaysActionPerformed

    private void mniTTphatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTTphatActionPerformed
        if (XAuther.isManager()) {
            XMgsbox.alert(this, "Bạn không có quyền vào phần này");
        } else {
            ThongKeTienPhat tkt = new ThongKeTienPhat();
            tkt.setVisible(true);
        }
    }//GEN-LAST:event_mniTTphatActionPerformed

    private void btnThongKeMuonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeMuonTraActionPerformed
        // TODO add your handling code here:
        thongKe_MuonTraSach_form tkemuontra = new thongKe_MuonTraSach_form();
        tkemuontra.setVisible(true);
    }//GEN-LAST:event_btnThongKeMuonTraActionPerformed

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
            java.util.logging.Logger.getLogger(main_form_QL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_form_QL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_form_QL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_form_QL.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new main_form_QL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnMuonTraSach;
    private javax.swing.JButton btnSachTaiLieu;
    private javax.swing.JButton btnThongKeMuonTra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JMenu mn1;
    private javax.swing.JMenuItem mniDoiMk;
    private javax.swing.JMenuItem mniDx;
    private javax.swing.JMenuItem mniPm;
    private javax.swing.JMenuItem mniSachMuonTheoDays;
    private javax.swing.JMenuItem mniSachTaiLieu;
    private javax.swing.JMenuItem mniTTphat;
    private javax.swing.JMenuItem mniThongKeMuonTs;
    private javax.swing.JMenuItem mnu1;
    private javax.swing.JMenuItem mnu2;
    // End of variables declaration//GEN-END:variables
}
