package com.library.form.quanli;

import com.library.dao.CTPhieuMuonDAO;
import com.library.dao.KhachHangDAO;
import com.library.dao.PhieuMuonDAO;
import com.library.dao.PhieuTraDAO;
import com.library.entity.CTPhieuMuon;
import com.library.entity.PhieuMuon;
import com.library.entity.PhieuTra;
import com.library.entity.Sach;
import com.library.helper.XCheck;
import com.library.helper.XDate;
import com.library.helper.XImages;
import com.library.helper.XJdbc;
import com.library.helper.XMgsbox;
import java.awt.Color;
import static java.awt.Color.white;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JTextField;

public class form_quanli_QuanLiMuonTraSach extends javax.swing.JFrame {

    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel mol_2 = new DefaultTableModel();
    DefaultTableModel mol3 = new DefaultTableModel();
    PhieuMuonDAO daoPM = new PhieuMuonDAO();
    PhieuTraDAO daoPT = new PhieuTraDAO();
    CTPhieuMuonDAO daoCTPM = new CTPhieuMuonDAO();
    KhachHangDAO daoKH = new KhachHangDAO();
    int index;
    int check = 1;
    public Connection conn = XJdbc.getConnection();
    float tienPhat, tienPhat2;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();

    public form_quanli_QuanLiMuonTraSach() {
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

        mol3.setColumnCount(0);
        mol3 = (DefaultTableModel) tblBangPhieuTra.getModel();
        String col3[] = {"STT", "Mã PM", "Mã Sách", "Số Tiền Cọc", "Tiền phạt", "Số Tiền Hoàn Trả", "Số Tiền Thanh Toán"};
        for (String z : col3) {
            mol3.addColumn(z);
        }
        txtNgayThucTra.setText(dtf.format(now));
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

    void fillToTablePTra() {
        DefaultTableModel model = (DefaultTableModel) tblBangPhieuTra.getModel();
        model.setRowCount(0);   //đưa số dòng về 0 (xóa bảng)
        try {
            int index  = tblBangPM.getSelectedRow();
            Integer pm = Integer.parseInt(tblBangPM.getValueAt(index, 0).toString());
            if (pm != null) {
                List<PhieuTra> list = daoPT.selectByPM(pm);
                for (int i = 0; i < list.size(); i++) {
                    PhieuTra pt = list.get(i);
                    Object[] row = {
                        i + 1,
                        pt.getMaPM(),
                        pt.getMaSach(),
                        pt.getSoTienCoc(),
                        pt.getTienPhat(),
                        pt.getTienHoanTra(),
                        pt.getTienThanhToan()
                    };
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
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
            e.printStackTrace();
        }
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
        txtNgayTra.setText(XDate.toString(pm.getNgayTra()));
        txtSoTienCoc.setText(pm.getSoTienCoc() + "");
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
        ctpm.setNgayThucTra(XDate.toDate(txtNgayThucTra.getText()));
        ctpm.setTienPhat(Float.parseFloat(txtTienPhat.getText()));
        return ctpm;
    }

    PhieuTra getFormPTra() {
        PhieuTra pt = new PhieuTra();
        pt.setMaPM(Integer.parseInt(txtMaPM.getText()));
        pt.setMaSach(txtMaSach.getText());
        pt.setSoTienCoc(Float.parseFloat(txtSoTienCoc.getText()));
        pt.setTienPhat(Float.parseFloat(txtTienPhat.getText()));
        return pt;
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

    void insertPTra() {
        PhieuTra pt = getFormPTra();
        try {
            daoPT.insert(pt);
            this.fillToTablePTra();//cập nhật lại bảng pt
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updatePTra() {
        PhieuTra pt = getFormPTra();
        try {
            daoPT.update(pt);
            this.fillToTablePTra();//cập nhật lại bảng pt
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean checkTienPhat(JTextField txt, JTextField txt2) {
        txt.setBackground(white);
        java.util.Date date = XDate.toDate(txt.getText());
        java.util.Date date2 = XDate.toDate(txt2.getText());
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date);
        c2.setTime(date2);
        long a = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        if (c2.getTime().getTime() > c1.getTime().getTime()) {
            tienPhat = a * 2000;
            return true;
        } else {
            return false;
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
        txtMaSach = new javax.swing.JTextField();
        btnThemCTPM = new javax.swing.JButton();
        btnSuaCTPM = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBangCTPM = new javax.swing.JTable();
        btnTraSach = new javax.swing.JButton();
        txtMaPM = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNgayThucTra = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNgayTra = new javax.swing.JTextField();
        cboTinhTrang = new javax.swing.JComboBox<>();
        txtTienPhat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtSoTienCoc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblBangPhieuTra = new javax.swing.JTable();

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

        tabs.addTab("Phiếu Mượn", jPanel1);

        panel.setBackground(new java.awt.Color(238, 232, 171));

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setText("Mã PM");

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel11.setText("Mã sách");

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

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel15.setText("Mã Khách Hàng");

        txtMaKH.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel12.setText("Ngày thực trả");

        txtNgayThucTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayThucTraActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel16.setText("Ngày Trả");

        txtNgayTra.setEnabled(false);

        cboTinhTrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bình Thường", "Hư Hỏng", "Mất Sách" }));
        cboTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhTrangActionPerformed(evt);
            }
        });

        txtTienPhat.setText("0.0");
        txtTienPhat.setEnabled(false);
        txtTienPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienPhatActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel13.setText("Tình trạng sách");

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel14.setText("Tiền phạt");

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel17.setText("Số Tiền Cọc");

        txtSoTienCoc.setEnabled(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btnThemCTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuaCTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelLayout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                                            .addGap(32, 32, 32)))
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17))
                                .addGap(44, 44, 44)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSoTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMaSach, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                        .addComponent(txtMaPM)
                                        .addComponent(txtMaKH)))
                                .addGap(62, 62, 62)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel12)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTienPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtNgayThucTra, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNgayTra, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cboTinhTrang, javax.swing.GroupLayout.Alignment.LEADING, 0, 229, Short.MAX_VALUE)))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMaPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtNgayThucTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTinhTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTienPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtSoTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemCTPM)
                    .addComponent(btnSuaCTPM)
                    .addComponent(btnTraSach))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Chi Tiết Phiếu Mượn", panel);

        jPanel2.setBackground(new java.awt.Color(238, 232, 171));

        tblBangPhieuTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBangPhieuTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangPhieuTraMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblBangPhieuTra);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 904, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        tabs.addTab("Phiếu Trả", jPanel2);

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
            if (evt.getClickCount() == 1) {
                if (this.index >= 0) {
                    tabs.setSelectedIndex(2);
                    fillToTablePTra();
                }
            }
        } else if (click.equalsIgnoreCase("Chưa Duyệt")) {
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
                    daoKH.updateSLMuon(txtMaKH.getText());
                    insertCTPM();
                    insertPTra();
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
                updatePTra();
            }
        }
    }//GEN-LAST:event_btnSuaCTPMActionPerformed

    private void btnThemPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPMActionPerformed
        // TODO add your handling code here:
        form_quanli_ThemPhieuMuon themPM = new form_quanli_ThemPhieuMuon();
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
            daoKH.updateTruSLMuon(txtMaKH.getText());
            if (tblBangCTPM.getRowCount() == 0) {
                updateTrangThai();
                tabs.setSelectedIndex(0);
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
        if (checkTienPhat(txtNgayTra, txtNgayThucTra)) {
            if (cboTinhTrang.getSelectedItem().equals("Mất Sách")) {
                tienPhat2 = 100000 + tienPhat;
                txtTienPhat.setText(tienPhat2 + "");
            } else if (cboTinhTrang.getSelectedItem().equals("Hư Hỏng")) {
                tienPhat2 = 50000 + tienPhat;
                txtTienPhat.setText(tienPhat2 + "");
            } else {
                tienPhat2 = 0 + tienPhat;
                txtTienPhat.setText(tienPhat2 + "");
            }
        }
    }//GEN-LAST:event_cboTinhTrangActionPerformed

    private void tblBangPhieuTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangPhieuTraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangPhieuTraMouseClicked

    private void txtNgayThucTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayThucTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayThucTraActionPerformed

    private void txtTienPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienPhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienPhatActionPerformed

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
            java.util.logging.Logger.getLogger(form_quanli_QuanLiMuonTraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_quanli_QuanLiMuonTraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_quanli_QuanLiMuonTraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_quanli_QuanLiMuonTraSach.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_quanli_QuanLiMuonTraSach().setVisible(true);
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
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panel;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBangCTPM;
    private javax.swing.JTable tblBangPM;
    private javax.swing.JTable tblBangPhieuTra;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaPM;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtNgayThucTra;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtSoTienCoc;
    private javax.swing.JTextField txtTienPhat;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
