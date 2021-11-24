/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.form;

import com.library.dao.KhachHangDAO;
import com.library.dao.NhanVienDAO;
import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import com.library.helper.XDate;
import com.library.helper.XMgsbox;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class form_Qlkh extends javax.swing.JFrame {
    DefaultTableModel defaultTableModel;
    List<KhachHang> listKhachHangs;
    KhachHangDAO khachHangDAO;
    DefaultComboBoxModel defaultComboBoxModel;
    NhanVien nhanVien;
    NhanVienDAO nhanVienDAO;
    
      int row = 0;
    int index;
    boolean check = true;
   
    public form_Qlkh() {
        initComponents();
        init();
    }
    void grouButton() {
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdoNam);
        bg.add(rdoNu);
        rdoNam.setSelected(true);
    }

    void loadTieuDeBang() {
        defaultTableModel.addColumn("MAKH");
        defaultTableModel.addColumn("Tên KH");
        defaultTableModel.addColumn("Mật Khẩu");
        defaultTableModel.addColumn("Giới Tính");
        defaultTableModel.addColumn("Ngày Sinh");
        defaultTableModel.addColumn("SĐT");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Địa Chỉ");
        defaultTableModel.addColumn("MÃ NV");
        defaultTableModel.addColumn("Trạng Thái");

        this.tblQlkh.setModel(defaultTableModel);
    }


    int loadTable(String code) {
        int i = 0;
        int row = 0;

        this.defaultTableModel.setRowCount(0);
        this.listKhachHangs = khachHangDAO.selectAll();

        for (KhachHang kh : listKhachHangs) {
            if (code != null && kh.getMaKH().equalsIgnoreCase(code)) {
                row = i;
            }
            Object r[] = {
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getMatKhau(),
                kh.getGioiTinh() ? "Nam" : "Nữ",
                XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getMaNV(),
                kh.getTrangThai() ? "Hoạt Động" : "Không Hoạt Động"
            };

            this.defaultTableModel.addRow(r);
//            tblQlkh.setRowSelectionInterval(index, index);
            i++;
        }
        return row;
    }

    void setForm(KhachHang kh) {
        txtMakh.setText(kh.getMaKH());
        txtTenKh.setText(kh.getTenKH());
        txtMatKhau.setText(kh.getMatKhau());

        if (kh.getGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
//        rdoNam.setSelected(kh.getGioiTinh());
//        rdoNu.setSelected(!kh.getGioiTinh());

        txtNgaySinh.setText(XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"));
        txtSdt.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());
        txtDiaChi.setText(kh.getDiaChi());
        txtManv.setText(kh.getMaNV());
        cbxTrangThai.setSelectedItem(kh.getTrangThai() ? true : false);

    }

    KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(txtMakh.getText());
        kh.setTenKH(txtTenKh.getText());
        kh.setMatKhau(new String(txtMatKhau.getPassword()));
        kh.setGioiTinh(rdoNam.isSelected());

        try {
            String ngay = txtNgaySinh.getText();
            Date date = XDate.toDate(txtNgaySinh.getText(), "dd-MM-yyyy");
            kh.setNgaySinh(date);
        } catch (Exception e) {
        }

        kh.setSdt(txtSdt.getText());
        kh.setEmail(txtEmail.getText());
        kh.setDiaChi(txtDiaChi.getText());
        kh.setMaNV(txtManv.getText());
        kh.setTrangThai(cbxTrangThai.getSelectedIndex() == 0);
        kh.setSoLuongMuon(Integer.parseInt("0"));

        return kh;
    }

    void showDetail(int index) {
        txtMakh.setText((String) tblQlkh.getValueAt(index, 0));
        txtTenKh.setText((String) tblQlkh.getValueAt(index, 1));
        txtMatKhau.setText((String) tblQlkh.getValueAt(index, 2));

        String gt = (String) tblQlkh.getValueAt(index, 3);
        if (gt.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        txtNgaySinh.setText((String) tblQlkh.getValueAt(index, 4));
        txtSdt.setText((String) tblQlkh.getValueAt(index, 5));
        txtEmail.setText((String) tblQlkh.getValueAt(index, 6));
        txtDiaChi.setText((String) tblQlkh.getValueAt(index, 7));
        txtManv.setText((String) tblQlkh.getValueAt(index, 8));
        cbxTrangThai.setSelectedItem(tblQlkh.getValueAt(index, 9));

    }

    void insert() {
        try {
            KhachHang kh = getForm();
            this.khachHangDAO.insert(kh);
            int index = loadTable(kh.getMaKH());
            tblQlkh.setRowSelectionInterval(index, index);
            XMgsbox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            XMgsbox.alert(this, "Thêm mới thất bại");
            return;
        }
    }

    void update() {
        try {
            KhachHang kh = getForm();
            this.khachHangDAO.update(kh);
            int index = loadTable(kh.getMaKH());
            tblQlkh.setRowSelectionInterval(index, index);
            XMgsbox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            XMgsbox.alert(this, "Cập nhật thành công");
            return;
        }
    }

//    void timKiem() {
//        String timkiem = txtTimKiem.getText();
//        KhachHang kh = khachHangDAO.selectByID(timkiem);
//        if(kh != null){
//            setForm(kh);
//        }else{
//            return;
//        }
//    }
    void loadTableTk() {
        this.defaultTableModel.setRowCount(0);
        String timkiem = txtTimKiem.getText();
        this.listKhachHangs = khachHangDAO.selectByMakh(timkiem);
        for (KhachHang kh : listKhachHangs) {
            Object r[] = {
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getMatKhau(),
                kh.getGioiTinh() ? "Nam" : "Nữ",
                XDate.toString(kh.getNgaySinh(), "dd-MM-yyyy"),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getMaNV(),
                kh.getTrangThai() ? "Hoạt Động" : "Không Hoạt Động"
            };
            this.defaultTableModel.addRow(r);
        }
        KhachHang kh = khachHangDAO.selectByID(timkiem);
        if (kh != null) {
            setForm(kh);
        } else {
            return;
        }
    }

    private void init() {

        this.setLocationRelativeTo(null);

        this.defaultTableModel = new DefaultTableModel();
        this.listKhachHangs = new ArrayList<KhachHang>();
        this.khachHangDAO = new KhachHangDAO();
        this.defaultComboBoxModel = new DefaultComboBoxModel();
        this.nhanVien = new NhanVien();
        this.nhanVienDAO = new NhanVienDAO();

        this.loadTieuDeBang();
        this.loadTable(null);
        this.grouButton();
        this.showDetail(0);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        rdoNam = new javax.swing.JRadioButton();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtTenKh = new javax.swing.JTextField();
        txtMakh = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtManv = new javax.swing.JTextField();
        rdoNu = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQlkh = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ KHACH HÀNG");

        jPanel1.setBackground(new java.awt.Color(238, 232, 171));

        btnTimKiem.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không Hoạt Động" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Users.png"))); // NOI18N
        jLabel1.setText("Quản Lý Khách Hàng");

        btnThem.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel10.setText("Mã KH");

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel11.setText("Ngày Sinh");

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel16.setText("Địa Chỉ");

        jLabel19.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel19.setText("Mã NV");

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel15.setText("SĐT");

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel14.setText("Giới Tính");

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel13.setText("Email");

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel17.setText("Mật Khẩu");

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel12.setText("Tên KH");

        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel18.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel18.setText("Trạng Thái");

        tblQlkh.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQlkh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQlkhMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQlkh);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(46, 46, 46)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMakh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(txtManv, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.checkRong();
        this.checkDl();
        if (checkMa()) {
            this.insert();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        this.checkRong();
        this.checkDl();
        if (check == true) {
            this.update();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        //       String timkiem = txtTimKiem.getText();
//       KhachHang kh = khachHangDAO.selectByID(timkiem);
//       if(timkiem.equalsIgnoreCase("")){
//           XMgsbox.alert(this, "Ô nhập tìm kiếm đang để trống !");
//       }
//       else if(kh == null){
//           XMgsbox.alert(this, "Không có mã bạn cần tìm !");
//       }else{
//           timKiem();
//       }
        String timkiem = txtTimKiem.getText();
        this.listKhachHangs = khachHangDAO.selectByMakh(timkiem);
        if (timkiem.equalsIgnoreCase("")) {
            loadTable(null);
        } else if (!listKhachHangs.isEmpty()) {
            loadTableTk();
            txtTimKiem.setText("");
        } else {
            XMgsbox.alert(this, "Mã khách cần tìm không có ");
             txtTimKiem.setText("");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblQlkhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQlkhMouseClicked
        // TODO add your handling code here:
        index = tblQlkh.getSelectedRow();
        this.showDetail(index);
    }//GEN-LAST:event_tblQlkhMouseClicked

    
    void checkRong() {
        if (txtMakh.getText().isEmpty() || txtTenKh.getText().isEmpty()
                || new String(txtMatKhau.getPassword()).isEmpty()
                || txtNgaySinh.getText().isEmpty() || txtSdt.getText().isEmpty()
                || txtEmail.getText().isEmpty() || txtDiaChi.getText().isEmpty()
                || txtManv.getText().isEmpty()) {
            XMgsbox.alert(this, "Không được để trống");
            return;
        }
    }

    boolean checkMa() {
        String maKh = txtMakh.getText();
        KhachHang kh = this.khachHangDAO.selectByID(maKh);
        if (kh != null) {
            XMgsbox.alert(this, "Mã Khách hàng đã tồn tại");
            return false;
        }

        String sdt = txtSdt.getText();
        KhachHang khach = khachHangDAO.selectBySdt(sdt);
        if (khach != null) {
            XMgsbox.alert(this, "Số điện thoại trùng");
            return false;
        }else{
            return true;
        }
    }

    void checkDl() {
        // check manv không tồn tại
        String manv = txtManv.getText();
        NhanVien nv = nhanVienDAO.selectByID(manv);
        if (nv == null) {
            XMgsbox.alert(this, "Mã Nhân viên không tồn tại");
            check = false;
            return;
        } else {
            check = true;
        }
        // check email
        String p_email = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
        String email = txtEmail.getText();
        if (!email.matches(p_email)) {
            XMgsbox.alert(this, "Email không đúng định dạng");
            check = false;
            return;
        } else {
            check = true;
        }

        // check sdt
        String makhau = new String(txtMatKhau.getPassword());
        if (makhau.length() < 6) {
            XMgsbox.alert(this, "Mật khẩu phải lớn hơn 6");
            check = false;
            return;
        } else {
            check = true;
        }

        //check ngày sinh
        String ngaySinh = txtNgaySinh.getText();
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            sdf.applyPattern("dd-MM-yyyy");
            Date ngay = sdf.parse(ngaySinh);
            check = true;
        } catch (Exception e) {
            XMgsbox.alert(this, "Ngày sai định dạng");
            check = false;
            return;
        }

        //check sdt
        String sdt = txtSdt.getText();
        String p = "0\\d{9,10}";
        try {
            if (!sdt.matches(p)) {
                XMgsbox.alert(this, "Số điện thoại không đúng đinh dạng");
                check = false;
                return;
            } else {
                check = true;
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi định dạng số điện thoại");
            check = false;
            return;
        }

    }
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
            java.util.logging.Logger.getLogger(form_Qlkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_Qlkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_Qlkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_Qlkh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_Qlkh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblQlkh;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMakh;
    private javax.swing.JTextField txtManv;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
