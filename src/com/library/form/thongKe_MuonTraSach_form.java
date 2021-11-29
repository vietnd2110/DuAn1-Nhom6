package com.library.form;

import com.library.dao.PhieuMuonDao;
import com.library.dao.ThongKeDao;
import com.library.helper.XImages;
import com.library.helper.XJdbc;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class thongKe_MuonTraSach_form extends javax.swing.JFrame {

    ThongKeDao dao = new ThongKeDao();
    PhieuMuonDao pmDao = new PhieuMuonDao();
    SimpleDateFormat spd = new SimpleDateFormat("dd-MM-yyyy");
    Connection con;
    DefaultTableModel mol = new DefaultTableModel();
    DefaultComboBoxModel cbo_model;

    public thongKe_MuonTraSach_form() {
        initComponents();
        setTitle("Libray management");
        setLocationRelativeTo(null);
        mol = (DefaultTableModel) tblThongKeMuonSach.getModel();
        cbo_model = (DefaultComboBoxModel) cboTrangThai.getModel();
        con = XJdbc.ketNoi();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addCbo();
        loadColumn();
        init();
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        ImageIcon icon = new ImageIcon(getClass().getResource(""));
        Image img = icon.getImage();
    }

    void addCbo() {
        DefaultComboBoxModel cbo_model = (DefaultComboBoxModel) cboTrangThai.getModel();
        cbo_model.removeAllElements();
        cbo_model.addElement("Chọn trạng thái");
        cbo_model.addElement("Chưa duyệt");
        cbo_model.addElement("Chưa trả");
    }

    void fillTable(String index) {
        try {
            String tt = cboTrangThai.getSelectedItem() + "";
            String sql = "select KH.MAKH, TENKH, GIOITINH, SDT, S.MaSach, TenSach, NgayMuon, NgayTra\n"
                    + "  from KHACHHANG KH inner join PHIEUMUON PM on Kh.MAKH = PM.MAKH\n"
                    + "inner join CTPHIEUMUON CT  on CT.MAPM = PM.MAPM\n"
                    + "inner join SACH S on S.MASACH = CT.MASACH\n"
                    + "where PM.TRANGTHAI = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, tt);
            ResultSet rs = pstm.executeQuery();
            mol.setRowCount(0);
            while (rs.next()) {
                String makh = rs.getString(1);
                String tenkh = rs.getString(2);
                String gt = rs.getString(3);
                String sdt = rs.getString(4);
                String ms = rs.getString(5);
                String ts = rs.getString(6);
                Date nm = rs.getDate(7);
                Date nt = rs.getDate(8);
                mol.addRow(new Object[]{makh, tenkh, gt, sdt, ms, ts, nm, nt});
            }
        } catch (Exception e) {
        }
    }
    void loadColumn() {
        DefaultTableModel mol = (DefaultTableModel) tblThongKeMuonSach.getModel();
        String[] column = {"Mã KH", "Tên KH", "Giới tính", "SDT", "Mã Sách", "Tên sách", "Ngày mượn", "Ngày trả"};
        for (String x : column) {
            mol.addColumn(x);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeMuonSach = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        cboTrangThai.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        cboTrangThai.setForeground(new java.awt.Color(255, 255, 255));
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trạng thái");

        tblThongKeMuonSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblThongKeMuonSach);

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_microsoft_people_36px 1.png"))); // NOI18N
        jLabel1.setText("Thống kê mượn trả sách");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel2)
                        .addGap(56, 56, 56)
                        .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiActionPerformed
        String index = cboTrangThai.getSelectedItem() + "";
        fillTable(index);
    }//GEN-LAST:event_cboTrangThaiActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

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
            java.util.logging.Logger.getLogger(thongKe_MuonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thongKe_MuonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thongKe_MuonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thongKe_MuonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new thongKe_MuonTraSach_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThongKeMuonSach;
    // End of variables declaration//GEN-END:variables
}
