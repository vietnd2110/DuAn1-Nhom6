package com.library.form;

import com.library.dao.KhachHangDAO;
import com.library.dao.NhanVienDAO;
import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import com.library.helper.XAuther;
import com.library.helper.XImages;
import com.library.helper.XMgsbox;
import static java.awt.Color.red;
import static java.awt.Color.white;
import javax.swing.JOptionPane;

public class login_Form extends javax.swing.JFrame {

    NhanVienDAO dao = new NhanVienDAO();
    KhachHangDAO daoKH = new KhachHangDAO();

    public login_Form() {
        initComponents();
        init();
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Thư Viện Đại Học Hà Nội - Hanoi University Library");
    }

    void login() {
        String manv = txtMaTK.getText();
        String matKhau = new String(txtMatKhau.getPassword());
        try {
            NhanVien user = dao.selectByID(manv);
            KhachHang user2 = daoKH.selectByID(manv);
            if (user != null) {    //nếu manv đúng
                String matKhau2 = user.getMatKhau();
                boolean trangThai = user.getTrangThai();
                if (matKhau.equals(matKhau2)) {  //nếu mật khẩu đúng
                    if (trangThai == false) { // check trạng thái tài khoản
                        XMgsbox.alert(this, "Tài khoản này đã bị khóa");
                    } else {
                        XAuther.USER = user;
                        XMgsbox.alert(this, "Đăng nhập thành công!");
                        main_form_QL mainQL = new main_form_QL();
                        mainQL.setVisible(true);
                        this.dispose();
                    }
                } else {
                    XMgsbox.alert(this, "Sai mật khẩu!");
                }
            } else if (user2 != null) {    //nếu maKH đúng
                String matKhau3 = user2.getMatKhau();
                boolean trangThai2 = user2.getTrangThai();
                if (matKhau.equals(matKhau3)) {  //nếu mật khẩu đúng
                    if (trangThai2 == false) { // check trạng thái tài khoản
                        XMgsbox.alert(this, "Tài khoản này đã bị khóa");
                    } else {
                        XAuther.UserKH = user2;
                        XMgsbox.alert(this, "Đăng nhập thành công!");
                        main_form_KH mainKH = new main_form_KH();
                        mainKH.setVisible(true);
                        this.dispose();
                    }
                } else {
                    XMgsbox.alert(this, "Sai mật khẩu!");
                }
            } else {
                XMgsbox.alert(this, "Sai tên đăng nhập!");
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaTK = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        btnDangNhap = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(237, 231, 223));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/android-chrome-192x192.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel2.setText("Đăng Nhập");

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Unknown person.png"))); // NOI18N
        jLabel3.setText("Tài khoản");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Unknown person.png"))); // NOI18N
        jLabel4.setText("Mật khẩu");

        txtMaTK.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N

        btnDangNhap.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user.png"))); // NOI18N
        btnDangNhap.setText("Đăng nhập");
        btnDangNhap.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/log-out.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaTK, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                    .addComponent(txtMatKhau)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnDangNhap)
                        .addGap(45, 45, 45)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(234, 234, 234))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDangNhap)
                    .addComponent(btnThoat))
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        txtMaTK.setBackground(white);
        txtMatKhau.setBackground(white);
        if (txtMaTK.getText().trim().length() > 0) {
            if (txtMatKhau.getPassword().length > 0) {
                this.login();
            } else {
                txtMatKhau.setBackground(red);
                XMgsbox.alert(this, "Không được để trống mật khẩu");
            }
        } else {
            txtMaTK.setBackground(red);
            XMgsbox.alert(this, "Không được để trống tên đăng nhập");
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        int q = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không ?", "Thoát", JOptionPane.YES_NO_OPTION);
        if (q == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnThoatActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JButton btnThoat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMaTK;
    private javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
