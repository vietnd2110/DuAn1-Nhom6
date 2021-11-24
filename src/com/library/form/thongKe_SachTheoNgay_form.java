
package com.library.form;

import com.library.dao.PhieuMuonDao;
import com.library.dao.ThongKeDao;
import com.library.helper.XImages;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;


public class thongKe_SachTheoNgay_form extends javax.swing.JFrame {

    
    ThongKeDao dao=new ThongKeDao();
    PhieuMuonDao pmDao=new PhieuMuonDao();
    SimpleDateFormat spd=new SimpleDateFormat("dd-MM-yyyy");
    public thongKe_SachTheoNgay_form() {
        initComponents();
       
        setLocationRelativeTo(null);
        //table
      
       
//        String []col={"Mã Sách","Tên Sách","Thể loại","Tác giả","Năm XB","Nhà Xb","Ngày mượn","Ngày trả","Số lượng"};
//        for (String x : col) {
//            mol.addColumn(x);
//        }
       
//        fillCbo();
        
        addCbo();
        //logo
        init();
        setTitle("Libray management");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void init(){
        setIconImage(XImages.getAppIcon());
        loadColumn();
        
      
    }
    void addCbo(){
        DefaultComboBoxModel cbo_model=(DefaultComboBoxModel)cboDays.getModel();
        cbo_model.removeAllElements();
        cbo_model.addElement("Chọn ngày");
        for (int i = 1; i <= 31; i++) {
            cbo_model.addElement(i);
        }
    }
//    void fillCbo(){
//        cbo_model.removeAllElements();
//        List<Integer> list=pmDao.selectDays();
//        for (Integer x : list) {
//            cbo_model.addElement(x);
//        }
//    }
    void fillTable(int index){
        DefaultTableModel mol=(DefaultTableModel) tblThongKeMuonSach.getModel();
        mol.setRowCount(0);
        int days =(int) cboDays.getSelectedIndex();
        List<Object[]> list = dao.getSachMuonTheoNgay(days);
        for (Object[] x : list) {
            mol.addRow(x);
        }

    }
    void loadColumn(){
        DefaultTableModel mol=(DefaultTableModel) tblThongKeMuonSach.getModel();
        String []col={"Mã Sách","Tên Sách","Thể loại","Tác giả","Năm XB","Nhà Xb",("Ngày mượn"),"Ngày trả","Số lượng"};
        for (String x : col) {
            mol.addColumn(x);
        } 
    }
 
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboDays = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKeMuonSach = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(183, 235, 231));

        cboDays.setFont(new java.awt.Font("Bahnschrift", 1, 14)); // NOI18N
        cboDays.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDays.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDaysItemStateChanged(evt);
            }
        });
        cboDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDaysActionPerformed(evt);
            }
        });

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
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_reading_36px_1 1.png"))); // NOI18N
        jLabel1.setText("Thống kê Sách mượn theo ngày");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(139, 139, 139)
                .addComponent(cboDays, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(293, 293, 293))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDays, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
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

    private void cboDaysItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDaysItemStateChanged
           
    }//GEN-LAST:event_cboDaysItemStateChanged

    private void cboDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDaysActionPerformed
        int index=cboDays.getSelectedIndex();
        fillTable(index);
        System.out.println(index);
    }//GEN-LAST:event_cboDaysActionPerformed

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
            java.util.logging.Logger.getLogger(thongKe_SachTheoNgay_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thongKe_SachTheoNgay_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thongKe_SachTheoNgay_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thongKe_SachTheoNgay_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new thongKe_SachTheoNgay_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboDays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThongKeMuonSach;
    // End of variables declaration//GEN-END:variables
}
