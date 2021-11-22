package com.library.form;

import com.library.dao.PhieuMuonDao;
import com.library.entity.PhieuMuon;
import com.library.helper.XAuther;
import com.library.helper.XCheck;
import com.library.helper.XDate;
import com.library.helper.XImages;
import com.library.helper.XJdbc;
import com.library.helper.XMgsbox;
import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ThemPhieuMuon_form extends javax.swing.JFrame {

    PhieuMuonDao daoPM = new PhieuMuonDao();
    int index;
    int check = 1;
    public Connection conn = XJdbc.getConnection();
    //dùng after để so sánh ngày mượnn bé hơn ngày trả 

    /*ví dụ if ngaymuon.after(ngaytra){
        //do code here }
     */
    public ThemPhieuMuon_form() {
        initComponents();
        init();
        fillToTablePM();
        btnSuaPM.setEnabled(false);
        txtMaNV.setText(XAuther.USER.getMaNV());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Thư Viện Đại Học Hà Nội - Hanoi University Library");
    }

    void fillToTablePM() {
        DefaultTableModel model = (DefaultTableModel) tblBangPM.getModel();
        model.setRowCount(0);   //đưa số dòng về 0 (xóa bảng)
        try {
            List<PhieuMuon> listPM = daoPM.selectAll();
            for (PhieuMuon pm : listPM) {
                Object[] row = {
                    pm.getMaPm(),
                    pm.getMaKH(),
                    pm.getMaNV(),
                    XDate.toString(pm.getNgayMuon()),
                    XDate.toString(pm.getNgayTra()),
                    pm.getSoTienCoc(),
                    pm.getTrangThai(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setFormPM(PhieuMuon pm) {
        txtMaPM.setText(pm.getMaPm() + "");
        txtMaKH.setText(pm.getMaKH());
        txtMaNV.setText(pm.getMaNV());
        txtNgayMuon.setText(XDate.toString(pm.getNgayMuon()));
        txtNgayTra.setText(XDate.toString(pm.getNgayTra()));
        txtSoTienCoc.setText(pm.getSoTienCoc() + "");
        cboTrangThai.setSelectedItem(pm.getTrangThai());
    }

    PhieuMuon getFormPM() {
        PhieuMuon pm = new PhieuMuon();
        pm.setMaKH(txtMaKH.getText());
        pm.setMaNV(txtMaNV.getText());
        pm.setNgayMuon(XDate.toDate(txtNgayMuon.getText()));
        pm.setNgayTra(XDate.toDate(txtNgayTra.getText()));
        pm.setSoTienCoc(Float.parseFloat(txtSoTienCoc.getText()));
        pm.setTrangThai(cboTrangThai.getSelectedItem() + "");
        return pm;
    }

    PhieuMuon getFormSuaPM() {
        PhieuMuon pm = new PhieuMuon();
        pm.setMaKH(txtMaKH.getText());
        pm.setMaNV(txtMaNV.getText());
        pm.setNgayMuon(XDate.toDate(txtNgayMuon.getText()));
        pm.setNgayTra(XDate.toDate(txtNgayTra.getText()));
        pm.setSoTienCoc(Float.parseFloat(txtSoTienCoc.getText()));
        pm.setTrangThai(cboTrangThai.getSelectedItem() + "");
        pm.setMaPm(Integer.parseInt(txtMaPM.getText()));
        return pm;
    }

    void setTrangPM() {
        txtMaPM.setBackground(Color.white);
        txtMaKH.setBackground(Color.white);
        txtMaNV.setBackground(Color.white);
        txtNgayMuon.setBackground(Color.white);
        txtNgayTra.setBackground(Color.white);
        txtSoTienCoc.setBackground(Color.white);
        cboTrangThai.setSelectedIndex(0);
    }

    void clearPM() {
        setTrangPM();
        setFormPM(new PhieuMuon());
    }

    void editPM() {
        try {
            Integer maPM = (Integer) tblBangPM.getValueAt(index, 0);  //lấy maPM từ bảng theo index
            PhieuMuon pm = daoPM.selectByID(maPM); //dùng maPM tìm ra đối tượng PhieuMuon
            if (pm != null) {
                this.setFormPM(pm);   //điền thông tin dt PhieuMuon lên form
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insertPM() {
        PhieuMuon pm = getFormPM();
        try {
            daoPM.insert(pm);
            this.fillToTablePM();    //cập nhật lại bảng pm
            this.clearPM();
            XMgsbox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Thêm mới thất bại!");
        }
    }

    void updatePM() {
        PhieuMuon pm = getFormSuaPM();
        try {
            daoPM.update(pm);
            this.fillToTablePM();    //cập nhật lại bảng pm
            XMgsbox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Cập nhật thất bại!");
        }
    }

    void checkDL() {
        float tienCoc;
        try {
            tienCoc = Float.parseFloat(txtSoTienCoc.getText());
            if (tienCoc < 0) {
                XMgsbox.alert(this, "Số tiền cọc phải lớn hơn 0");
                txtSoTienCoc.requestFocus();
                txtSoTienCoc.setBackground(Color.red);
                check = 0;
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Thu nhập không phải là số");
            txtSoTienCoc.requestFocus();
            txtSoTienCoc.setBackground(Color.red);
            check = 0;
        }
    }

    private boolean checkSoLuongMuon(String maKH) {
        String sql = "SELECT SOLUONGMUON FROM PHIEUMUON WHERE MAKH like '%" + maKH + "%'";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int result = Integer.parseInt(rs.getString("SOLUONGMUON"));
                if (result == 3) {
                    return false;
                }
            }

        } catch (Exception e) {

        }
        return true;
    }

    public boolean check14Ngay(JTextField txt, JTextField txt2) {
        txt.setBackground(white);
        java.util.Date date = XDate.toDate(txt.getText());
        java.util.Date date2 = XDate.toDate(txt2.getText());
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date);
        c2.setTime(date2);
        long a = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        if (a >= 14) {
            txt2.setBackground(pink);
            XMgsbox.alert(this, "Ngày trả không được lớn hơn ngày mượn quá 14 ngày");
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtMaPM = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgayMuon = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSoTienCoc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangPM = new javax.swing.JTable();
        btnThemPM = new javax.swing.JButton();
        btnSuaPM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        jLabel1.setText("Thêm Phiếu Mượn");

        jPanel1.setBackground(new java.awt.Color(238, 232, 171));

        txtMaPM.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel7.setText("Tiền Cọc");

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel8.setText("Trạng Thái");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Mã PM");

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setText("Mã KH");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel4.setText("Mã NV");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Mượn");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel6.setText("Ngày Trả");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa Duyệt", "Chưa Trả", "Đã Trả" }));

        tblBangPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã PM", "Mã KH", "Mã NV", "Ngày Mượn", "Ngày Trả", "Số Tiền Cọc", "Trạng Thái"
            }
        ));
        tblBangPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangPMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangPM);

        btnThemPM.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnThemPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnThemPM.setText("Thêm");
        btnThemPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPMActionPerformed(evt);
            }
        });

        btnSuaPM.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnSuaPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit.png"))); // NOI18N
        btnSuaPM.setText("Sửa");
        btnSuaPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(51, 51, 51)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSoTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnThemPM, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(417, 417, 417)
                    .addComponent(jLabel6)
                    .addContainerGap(440, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSoTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemPM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaPM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(101, 101, 101)
                    .addComponent(jLabel6)
                    .addContainerGap(408, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPMActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaKH)
                && XCheck.checkNullText(txtMaNV)
                && XCheck.checkNullText(txtNgayMuon)
                && XCheck.checkNullText(txtNgayTra)) {
            if (XCheck.checkMaNV(txtMaKH)
                    && XCheck.checkDate(txtNgayMuon)
                    && XCheck.checkDate(txtNgayTra)) {
                if (check14Ngay(txtNgayMuon, txtNgayTra)) {
                    if (checkSoLuongMuon(txtMaKH.getText()) == true) {
                        insertPM();
                    } else {
                        XMgsbox.alert(this, "Khách Hàng Này Đã Mượn Tối Đa 3 Cuốn Sách");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThemPMActionPerformed

    private void btnSuaPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaPMActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaKH)
                && XCheck.checkNullText(txtMaNV)
                && XCheck.checkNullText(txtNgayMuon)
                && XCheck.checkNullText(txtNgayTra)) {
            if (XCheck.checkMaNV(txtMaKH)
                    && XCheck.checkDate(txtNgayMuon)
                    && XCheck.checkDate(txtNgayTra)) {
                if (check14Ngay(txtNgayMuon, txtNgayTra)) {
                    checkDL();
                    if (check == 1) {
                        this.updatePM();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSuaPMActionPerformed

    private void tblBangPMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangPMMouseClicked
        // TODO add your handling code here:
        this.index = tblBangPM.rowAtPoint(evt.getPoint());//lấy vị trí dòng được chọn
        if (this.index >= 0) {
            this.editPM();
            btnSuaPM.setEnabled(true);
        }
    }//GEN-LAST:event_tblBangPMMouseClicked

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
            java.util.logging.Logger.getLogger(ThemPhieuMuon_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuMuon_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuMuon_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemPhieuMuon_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemPhieuMuon_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaPM;
    private javax.swing.JButton btnThemPM;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBangPM;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaPM;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtSoTienCoc;
    // End of variables declaration//GEN-END:variables
}
