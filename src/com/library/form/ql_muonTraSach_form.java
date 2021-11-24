package com.library.form;

import com.library.dao.CTPhieuMuonDAO;
import com.library.dao.PhieuMuonDao;
import com.library.entity.CTPhieuMuon;
import com.library.entity.PhieuMuon;
import com.library.helper.XCheck;
import com.library.helper.XDate;
import com.library.helper.XImages;
import com.library.helper.XJdbc;
import com.library.helper.XMgsbox;
import java.awt.Color;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ql_muonTraSach_form extends javax.swing.JFrame {

    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel mol_2 = new DefaultTableModel();
    PhieuMuonDao daoPM = new PhieuMuonDao();
    CTPhieuMuonDAO daoCTPM = new CTPhieuMuonDAO();
    int index;
    int check = 1;
    public Connection conn = XJdbc.getConnection();

    public ql_muonTraSach_form() {
        initComponents();
        //table 1
        mol.setColumnCount(0);
        mol = (DefaultTableModel) tblBangPM.getModel();
        String[] col = {"Mã PM", "Mã KH", "Mã NV", "Ngày mượn", "Ngày Trả", "Số tiền cọc", "Trạng thái"};
        for (String x : col) {
            mol.addColumn(x);
        }
        //table 2
        mol_2.setColumnCount(0);
        mol_2 = (DefaultTableModel) tblBangCTPM.getModel();
        String[] col2 = {"STT", "Mã PM", "Mã Sách", "Ngày thực trả", "Tình trạng sách", "Tiền phạt"};
        for (String x : col2) {
            mol_2.addColumn(x);
        }
        //logo
        init();
        fillToTablePM();
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

    void fillToTableCTPM() {
        DefaultTableModel model = (DefaultTableModel) tblBangCTPM.getModel();
        model.setRowCount(0);   //đưa số dòng về 0 (xóa bảng)
        try {
            Integer pm = Integer.parseInt(txtMaPM.getText());
            if (pm != null) {
                List<CTPhieuMuon> list = daoCTPM.selectByPM(pm);
                for (int i = 0; i < list.size(); i++) {
                    CTPhieuMuon ctpm = list.get(i);
                    Object[] row = {
                        i + 1,
                        ctpm.getMaPM(),
                        ctpm.getMaSach(),
                        XDate.toString(ctpm.getNgayThucTra()),
                        ctpm.getTinhTrangSach(),
                        ctpm.getTienPhat()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setFormPM(PhieuMuon pm) {
        txtMaPM.setText(pm.getMaPm() + "");
    }

    void setFormCTPM(CTPhieuMuon ctpm) {
        txtMaPM.setText(ctpm.getMaPM() + "");
        txtMaSach.setText(ctpm.getMaSach());
        txtNgayThucTra.setText(XDate.toString(ctpm.getNgayThucTra()));
        cboTinhTrang.setSelectedItem(ctpm.getTinhTrangSach());
        txtTienPhat.setText(ctpm.getTienPhat() + "");
    }

    CTPhieuMuon getFormCTPM() {
        CTPhieuMuon ctpm = new CTPhieuMuon();
        ctpm.setMaPM(Integer.parseInt(txtMaPM.getText()));
        ctpm.setMaSach(txtMaSach.getText());
        ctpm.setTinhTrangSach(cboTinhTrang.getSelectedItem() + "");
        ctpm.setTienPhat(Float.parseFloat(txtTienPhat.getText()));
        ctpm.setNgayThucTra(XDate.toDate(txtNgayThucTra.getText()));
        return ctpm;
    }

    void setTrangCTPM() {
        txtMaPM.setBackground(Color.white);;
        txtMaSach.setBackground(Color.white);
        txtNgayThucTra.setBackground(Color.white);
        txtTienPhat.setBackground(Color.white);
    }

    void clearCTPM() {
        setTrangCTPM();
        setFormCTPM(new CTPhieuMuon());
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

    void insertCTPM() {
        CTPhieuMuon ctpm = getFormCTPM();
        try {
            daoCTPM.insert(ctpm);
            this.fillToTableCTPM();    //cập nhật lại bảng pm
            XMgsbox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Thêm mới thất bại!");
        }
    }

    void updateCTPM() {
        CTPhieuMuon ctpm = getFormCTPM();
        try {
            daoCTPM.update(ctpm);
            this.fillToTableCTPM();    //cập nhật lại bảng pm
            XMgsbox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Cập nhật thất bại!");
        }
    }

    public boolean checkSoLuongMuonWithCTPM(String maPM) {
        String sql = "select SOLUONGMUON from PHIEUMUON, KHACHHANG where PHIEUMUON.MAKH = KHACHHANG.MAKH and MAPM like '%" + maPM + "%'";
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

    public boolean checkSoLuongMuonTraSach(String maPM) {
        String sql = "select SOLUONGMUON from PHIEUMUON, KHACHHANG where PHIEUMUON.MAKH = KHACHHANG.MAKH and MAPM like '%" + maPM + "%'";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int result = Integer.parseInt(rs.getString("SOLUONGMUON"));
                if (result == 0) {
                    return true;
                }
            }

        } catch (Exception e) {

        }
        return false;
    }
    
    void TraSach() {
        try {
            String maSach = txtMaSach.getText();
            Integer maPM = Integer.parseInt(txtMaPM.getText());
            daoCTPM.TraSach(maPM, maSach);
            fillToTableCTPM();
            XMgsbox.alert(this, "Trả Sách Thành Công");
        } catch (Exception e) {
            XMgsbox.alert(this, "Trả Sách Thất Bại");
        }
    }

    void loadTableTk() {
        DefaultTableModel model = (DefaultTableModel) tblBangPM.getModel();
        model.setRowCount(0);
        String timkiem = txtTimKiem.getText();
        List<PhieuMuon> listPM = daoPM.timKiemPM(timkiem);
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
    }

    PhieuMuon getFormPM() {
        PhieuMuon pm = new PhieuMuon();
        pm.setTrangThai("Đã Trả");
        pm.setMaPm(Integer.parseInt(txtMaPM.getText()));
        return pm;
    }

    void updateTrangThai() {
        PhieuMuon pm = getFormPM();
        try {
            daoPM.updateTrangThai(pm);
            fillToTablePM();
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangPM = new javax.swing.JTable();
        btnThemPM = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtNgayThucTra = new javax.swing.JTextField();
        cboTinhTrang = new javax.swing.JComboBox<>();
        txtTienPhat = new javax.swing.JTextField();
        btnThemCTPM = new javax.swing.JButton();
        btnSuaCTPM = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangCTPM = new javax.swing.JTable();
        btnTraSach = new javax.swing.JButton();
        txtMaPM = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 27)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_36px_2 1.png"))); // NOI18N
        jLabel1.setText("Quản lý mượn, trả sách");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel1.setBackground(new java.awt.Color(238, 232, 171));

        jPanel3.setBackground(new java.awt.Color(230, 202, 133));

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tìm kiếm");

        btnTimKiem.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_28px 1.png"))); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tblBangPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBangPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangPMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBangPM);

        btnThemPM.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnThemPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnThemPM.setText("Thêm Phiếu Mượn");
        btnThemPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemPM, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnThemPM)
                .addGap(15, 15, 15))
        );

        tabs.addTab("Phiếu mượn", jPanel1);

        panel.setBackground(new java.awt.Color(238, 232, 171));

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setText("Mã PM");

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel11.setText("Mã sách");

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel12.setText("Ngày thực trả");

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel13.setText("Tình trạng sách");

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel14.setText("Tiền phạt");

        cboTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Binh Thường", "Rách Sách", "Mất Sách" }));
        cboTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhTrangActionPerformed(evt);
            }
        });

        txtTienPhat.setText("0.0");
        txtTienPhat.setEnabled(false);

        btnThemCTPM.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnThemCTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnThemCTPM.setText("Thêm");
        btnThemCTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTPMActionPerformed(evt);
            }
        });

        btnSuaCTPM.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnSuaCTPM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit.png"))); // NOI18N
        btnSuaCTPM.setText("Sửa");
        btnSuaCTPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCTPMActionPerformed(evt);
            }
        });

        tblBangCTPM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBangCTPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangCTPMMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBangCTPM);

        btnTraSach.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnTraSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Tick.png"))); // NOI18N
        btnTraSach.setText("Trả Sách");
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });

        txtMaPM.setEnabled(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(56, 56, 56)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaSach)
                                    .addComponent(txtNgayThucTra, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addComponent(txtMaPM))
                                .addGap(74, 74, 74)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(64, 64, 64)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboTinhTrang, 0, 229, Short.MAX_VALUE)
                                    .addComponent(txtTienPhat)))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(btnThemCTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnSuaCTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnTraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtNgayThucTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTPM)
                    .addComponent(btnSuaCTPM)
                    .addComponent(btnTraSach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Chi tiết phiếu mượn", panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangPMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangPMMouseClicked
        // TODO add your handling code here:  
        this.index = tblBangPM.rowAtPoint(evt.getPoint());//lấy vị trí dòng được chọn
        String click = tblBangPM.getValueAt(index, 6) + "";
        if (click.equalsIgnoreCase("Đã Trả")) {
            XMgsbox.alert(this, "Khách Hàng này đã trả sách mời tạo Phiếu Mượn khác để tiếp tục mượn sách");
            return;
        } else  if (click.equalsIgnoreCase("Chưa Duyệt")){
            XMgsbox.alert(this, "Phiếu Mượn phải được nhân viên phê duyệt mới được mượn sách");
            return;
        } else {           
            if (evt.getClickCount() == 1) {
                if (this.index >= 0) {
                    this.editPM();
                    tabs.setSelectedIndex(1);
                    fillToTableCTPM();
                }
            }
        }
    }//GEN-LAST:event_tblBangPMMouseClicked

    private void btnThemCTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTPMActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaSach)) {
            if (XCheck.checkMaNV(txtMaSach)) {
                if (checkSoLuongMuonWithCTPM(txtMaPM.getText()) == true) {
                    daoPM.updateSLMuon(txtMaPM.getText());
                    insertCTPM();
                } else {
                    XMgsbox.alert(this, "Mỗi Khách Hàng Chỉ Được Mượn Tối Đa 3 Quyển Sách");
                }
            }
        }
    }//GEN-LAST:event_btnThemCTPMActionPerformed

    private void btnSuaCTPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCTPMActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaSach)
                && XCheck.checkNullText(txtNgayThucTra)) {
            if (XCheck.checkMaNV(txtMaSach)
                    && XCheck.checkDate(txtNgayThucTra)) {
                this.updateCTPM();
            }
        }
    }//GEN-LAST:event_btnSuaCTPMActionPerformed

    private void btnThemPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPMActionPerformed
        // TODO add your handling code here:
        ThemPhieuMuon_form themPM = new ThemPhieuMuon_form();
        themPM.setVisible(true);
    }//GEN-LAST:event_btnThemPMActionPerformed

    private void tblBangCTPMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangCTPMMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblBangCTPM.getSelectedRow();
        txtMaPM.setText(tblBangCTPM.getValueAt(selectedRow, 1).toString());
        txtMaSach.setText(tblBangCTPM.getValueAt(selectedRow, 2).toString());
        txtNgayThucTra.setText(tblBangCTPM.getValueAt(selectedRow, 3).toString());
        cboTinhTrang.setSelectedItem(tblBangCTPM.getValueAt(selectedRow, 4).toString());
        txtTienPhat.setText(tblBangCTPM.getValueAt(selectedRow, 5).toString());

    }//GEN-LAST:event_tblBangCTPMMouseClicked

    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
        // TODO add your handling code here:
        int index = tblBangCTPM.getSelectedRow();
        if (index >= 0) {
            TraSach();
            daoPM.updateTruSLMuon(txtMaPM.getText());
            if (checkSoLuongMuonTraSach(txtMaPM.getText()) == true) {
                updateTrangThai();
            }
        } else {
            XMgsbox.alert(this, "Bạn phải chọn sách trươc khí trả");
        }
    }//GEN-LAST:event_btnTraSachActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String timkiem = txtTimKiem.getText();
        List<PhieuMuon> listPM = daoPM.timKiemPM(timkiem);
        if (timkiem.equals("")) {
            XMgsbox.alert(this, "Bạn chưa nhập mã Phiếu Mượn cần tìm kiếm");
        } else {
            if (!listPM.isEmpty()) {
                loadTableTk();
                txtTimKiem.setText("");
            } else {
                XMgsbox.alert(this, "Mã Phiếu Mượn cần tìm không có ");
                txtTimKiem.setText("");
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhTrangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTinhTrangActionPerformed

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
            java.util.logging.Logger.getLogger(ql_muonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ql_muonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ql_muonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ql_muonTraSach_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ql_muonTraSach_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaCTPM;
    private javax.swing.JButton btnThemCTPM;
    private javax.swing.JButton btnThemPM;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTraSach;
    private javax.swing.JComboBox<String> cboTinhTrang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBangCTPM;
    private javax.swing.JTable tblBangPM;
    private javax.swing.JTextField txtMaPM;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayThucTra;
    private javax.swing.JTextField txtTienPhat;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
