
package com.library.form;

import com.library.dao.SachDAO;
import com.library.dao.theLoaiDAO;
import com.library.entity.TheLoai;
import com.library.entity.Sach;
import com.library.helper.XImages;
import com.library.helper.XJdbc;
import com.library.helper.XMgsbox;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class ql_sach_tai_lieu extends javax.swing.JFrame {

    DefaultTableModel mol=new DefaultTableModel();
    DefaultComboBoxModel<TheLoai> cbo_model=new DefaultComboBoxModel<>();
    SimpleDateFormat spd=new SimpleDateFormat("dd-MM-yyyy");//Đổi yyyy-MM-dd -> dd-MM-yyyy
    SachDAO sDao=new SachDAO();
    theLoaiDAO tlDao=new theLoaiDAO();
    int index;
   
    public ql_sach_tai_lieu() {
        initComponents();
        setLocationRelativeTo(null);
        cboMaTl.removeAllItems();
        cbo_model=(DefaultComboBoxModel)cboMaTl.getModel();//Combo box
        
        mol=(DefaultTableModel)tblSach.getModel();
        String []col={"Mã sách","Mã TL","Nhà XB","Tên sách","Nơi đặt","Giá tiền","Tác giả","Năm XB"};
        for (String x : col) {
            mol.addColumn(x);
        }
        filltoCbo();
        fillToTable(null);
        init();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void init(){
         setTitle("Library management");
         setIconImage(XImages.getAppIcon());
         lblMatl.setEditable(false);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnFind = new javax.swing.JButton();
        txtFind = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        cboMaTl = new javax.swing.JComboBox<>();
        txtTenSAch = new javax.swing.JTextField();
        txtNamXb = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        txtNhaXuatBan = new javax.swing.JTextField();
        txtGiaTien = new javax.swing.JTextField();
        txtNoiDat = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnFix = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        lblMatl = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 233, 196));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 25)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_books_36px_2 1.png"))); // NOI18N
        jLabel1.setText("Quản lý sách tài liệu");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel2.setBackground(new java.awt.Color(245, 194, 136));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnFind.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_28px 1.png"))); // NOI18N
        btnFind.setText("Tìm kiếm");
        btnFind.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        txtFind.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        txtFind.setText("Tim theo ten sach");
        txtFind.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFindMousePressed(evt);
            }
        });
        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel3.setText("Mã sách");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel4.setText("Mã thể loại");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel5.setText("Tên sách");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel6.setText("Năm xuất bản");

        cboMaTl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMaTl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMaTlItemStateChanged(evt);
            }
        });
        cboMaTl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaTlActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel7.setText("Tác giả");

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel8.setText("Nhà xuất bản");

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel9.setText("Giá tiền");

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel10.setText("Nơi đặt");

        btnAdd.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Create.png"))); // NOI18N
        btnAdd.setText("Thêm ");
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnFix.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnFix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit.png"))); // NOI18N
        btnFix.setText("Sửa");
        btnFix.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFixActionPerformed(evt);
            }
        });

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSach);

        lblMatl.setBackground(new java.awt.Color(102, 102, 102));
        lblMatl.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        lblMatl.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel11.setText("Tên thể loại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5))
                                        .addGap(33, 33, 33))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(65, 65, 65)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(50, 50, 50)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboMaTl, 0, 189, Short.MAX_VALUE)
                            .addComponent(txtMaSach)
                            .addComponent(txtTenSAch)
                            .addComponent(txtNamXb)
                            .addComponent(lblMatl))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(232, 232, 232)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(43, 43, 43)
                                    .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(79, 79, 79)
                                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNoiDat, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(268, 268, 268))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnFix, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(76, 76, 76)
                                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(cboMaTl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMatl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTenSAch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtNoiDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNamXb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFix, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
            index=tblSach.getSelectedRow();
            showDetail(index);
    }//GEN-LAST:event_tblSachMouseClicked

    private void cboMaTlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMaTlItemStateChanged
              clickCbo();

    }//GEN-LAST:event_cboMaTlItemStateChanged

    private void cboMaTlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaTlActionPerformed
  
    }//GEN-LAST:event_cboMaTlActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
           if (check()) {
               try {
                   Sach s=add();
                   sDao.insert(s);   
                   XMgsbox.alert(this, "Thêm thành công !");
                   int row= fillToTable(s.getMaSach());
                   tblSach.setRowSelectionInterval(row, row);
               } catch (ParseException ex) {
                   Logger.getLogger(ql_sach_tai_lieu.class.getName()).log(Level.SEVERE, null, ex);
               }
            
               
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnFixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFixActionPerformed
        index=tblSach.getSelectedRow();
        if (index>=0) {
            if (checkForUpdate()) {
                try {
                  Sach s;
                  s = add();
                  sDao.update(s);
                  XMgsbox.alert(this, "Update thành công !");
                  int row= fillToTable(s.getMaSach());
                  tblSach.setRowSelectionInterval(row, row);
                 
                } catch (ParseException ex) {
                   ex.printStackTrace();
                }  
            }
        }else{
            XMgsbox.alert(this, "Bạn chưa chọn đối tượng");
        }
    }//GEN-LAST:event_btnFixActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
              Sach s = sDao.findByName(txtFind.getText());
        if (s == null) {
            XMgsbox.alert(this, "Không tìm thấy");

        } else {
            XMgsbox.alert(this, "Tìm thấy");
            fillTableForFind();
        }
        
    }//GEN-LAST:event_btnFindActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
            
    }//GEN-LAST:event_txtFindActionPerformed

    private void txtFindMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFindMousePressed
          if (evt.getClickCount()==1) {
            txtFind.setText("");
            fillToTable(null);
        }
    }//GEN-LAST:event_txtFindMousePressed
    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ql_sach_tai_lieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ql_sach_tai_lieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ql_sach_tai_lieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ql_sach_tai_lieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ql_sach_tai_lieu().setVisible(true);
            }
        });
    }
   
    //Đổ dữ liệu vào Combobox
    void filltoCbo(){
        for (TheLoai x : tlDao.selectAll()) {
            cbo_model.addElement(new TheLoai(x.getMaTl(), x.getTenTl()));
            
        }
    }
    int fillToTable(String code){
        mol.setRowCount(0);
        int i=0;
        int row=0;
        for (Sach x : sDao.selectAll()) {
            if (code!=null && x.getMaSach().equalsIgnoreCase(code)) {
                row=i;
            }
            mol.addRow(new Object[]{x.getMaSach(),x.getTl().getMaTl(),x.getTenSach(),x.getNoiDat(),
            x.getGiaTien(),x.getTacGia(),formatDate(x.getNamXB())});
           i++;
        }
        return row;
    }
    void fillTableForFind(){
      
        try {
            Connection con;
            con=XJdbc.ketNoi();
            String select_nameBook="select *From sach where tensach like ?";
            PreparedStatement pstm=con.prepareStatement(select_nameBook);
            pstm.setString(1, "%"+txtFind.getText()+"%");
            ResultSet rs=pstm.executeQuery();
            mol.setRowCount(0);
            while (rs.next()) {                
                String ms=rs.getString(1);
                String mtl=rs.getString(2);
                String nxb=rs.getString(3);
                String tensach=rs.getString(4);
                String noiDat=rs.getString(5);
                float giaTien=rs.getFloat(6);
                String tg=rs.getString(7);
                Date namxb=rs.getDate(8);
                mol.addRow(new Object[]{ms,mtl,nxb,tensach,noiDat,giaTien,tg,namxb});
            }
            if (mol.getRowCount()>0) {
                index=0;
                tblSach.setRowSelectionInterval(index, index);
            }
        } catch (Exception e) {
        }
    }
    //Ép kiểu dữ liệu date-> String
    String formatDate(Date date){
        return spd.format(date);
    }
    //click vào table thì hiển thị lên bảng
    void showDetail(int index){
        txtMaSach.setText(mol.getValueAt(index, 0)+"");
        cbo_model.setSelectedItem(mol.getValueAt(index, 1)+"");
        txtNhaXuatBan.setText(mol.getValueAt(index, 2)+"");
        txtTenSAch.setText(mol.getValueAt(index, 3)+"");
        txtNoiDat.setText(mol.getValueAt(index, 4)+"");
        txtGiaTien.setText(mol.getValueAt(index, 5)+"");
        txtTacGia.setText(mol.getValueAt(index, 6)+"");
        txtNamXb.setText(mol.getValueAt(index, 7)+"");
    }
    void clickCbo(){
         try {
             Connection con;
             con=XJdbc.ketNoi();
             TheLoai c=(TheLoai) cboMaTl.getSelectedItem();
             String sql="Select *from THELOAI where matl=?";
             PreparedStatement pstm=con.prepareStatement(sql);
             pstm.setString(1, c.getMaTl());
             lblMatl.setText(c.getTenTl());
        } catch (Exception e) {
        }
    }
    Sach add() throws ParseException{
        Sach s=new Sach();
        String maTl=cboMaTl.getSelectedItem()+"";
        
        s.setMaSach(txtMaSach.getText());
        s.setTl(new TheLoai(maTl, ""));
        s.setnXB(txtNhaXuatBan.getText());
        s.setTenSach(txtTenSAch.getText());
        s.setNoiDat(txtNoiDat.getText());
        s.setGiaTien(Float.parseFloat(txtGiaTien.getText()));
        s.setTacGia(txtTacGia.getText());
        s.setNamXB(spd.parse(txtNamXb.getText()));
        return s;
    }
    //check for add
    boolean check(){
        //check empty
        if (txtMaSach.getText().isEmpty() || txtNhaXuatBan.getText().isEmpty() ||
            txtTenSAch.getText().isEmpty() || txtNoiDat.getText().isEmpty() || 
            txtGiaTien.getText().isEmpty() ||txtTacGia.getText().isEmpty() || txtNamXb.getText().isEmpty()
            ) {
            XMgsbox.alert(this, "Không để trống !");
            return false;
        }
        //check price
        try {
            float money=Float.parseFloat(txtGiaTien.getText());
            if (money<0) {
                XMgsbox.alert(this, "Giá phải lớn hơn 0 !");
                txtGiaTien.requestFocus();
                return false;
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Giá phải là số");
            return false;
        }
        //check for duplicate code
        for(Sach x: sDao.selectAll()){
            if (x.getMaSach().equalsIgnoreCase(txtMaSach.getText())) {
                XMgsbox.alert(this, "Không để trùng Mã Sách !");
                txtMaSach.requestFocus();
                return false;
            }
        }
        Date date=new Date();
        String check=spd.format(date);
        if (txtNamXb.getText().length() ==check.length()) {
            return true;
        }else{
            XMgsbox.alert(this, "Năm Xuất Bản sai định dạng: dd-MM-yyyy hoặc yyyy-MM-dd");
            return false;
        }
    }
    //check for update
    boolean checkForUpdate(){
        if (txtMaSach.getText().isEmpty() || txtNhaXuatBan.getText().isEmpty() ||
            txtTenSAch.getText().isEmpty() || txtNoiDat.getText().isEmpty() || 
            txtGiaTien.getText().isEmpty() ||txtTacGia.getText().isEmpty() || txtNamXb.getText().isEmpty()
            ) {
            XMgsbox.alert(this, "Không để trống !");
            return false;
        }
        //check price
        try {
            float money=Float.parseFloat(txtGiaTien.getText());
            if (money<0) {
                XMgsbox.alert(this, "Giá phải lớn hơn 0 !");
                txtGiaTien.requestFocus();
                return false;
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Giá phải là số");
            return false;
        }
        //check date
        Date date=new Date();
        String check=spd.format(date);
        if (txtNamXb.getText().length() ==check.length()) {
            return true;
        }else{
            XMgsbox.alert(this, "Năm Xuất Bản sai định dạng: dd-MM-yyyy hoặc yyyy-MM-dd");
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFix;
    private javax.swing.JComboBox<String> cboMaTl;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lblMatl;
    private javax.swing.JTable tblSach;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNamXb;
    private javax.swing.JTextField txtNhaXuatBan;
    private javax.swing.JTextField txtNoiDat;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSAch;
    // End of variables declaration//GEN-END:variables
}
