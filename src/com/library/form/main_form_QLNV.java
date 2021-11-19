/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.form;

import com.library.helper.XCheck;
import com.library.helper.XDate;
import com.library.helper.XMgsbox;
import com.library.helper.XImages;
import com.library.dao.NhanVienDAO;
import com.library.entity.KhachHang;
import com.library.entity.NhanVien;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class main_form_QLNV extends javax.swing.JFrame {

    DefaultTableModel mol = new DefaultTableModel();
    NhanVienDAO dao;
    int index;
    NhanVien nv;

    public main_form_QLNV() {
        initComponents();
        init();
        mol = (DefaultTableModel) tblNhanVien.getModel();
        String[] col = {"Mã NV", "Tên NV", "Mật khẩu", "Giới tính", "Ngày sinh", "SDT", "Email", "Địa chỉ", "Vai trò", "Trạng thái"};
        for (String x : col) {
            mol.addColumn(x);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Thư Viện Đại Học Hà Nội - Hanoi University Library");
        dao = new NhanVienDAO();
        nv = new NhanVien();
        fillToTable();
    }

    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);   //đưa số dòng về 0 (xóa bảng)
        try {
            List<NhanVien> listNV = dao.selectAll();
            for (NhanVien nv : listNV) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getMatKhau(),
                    nv.getGioiTinh() ? "Nam" : "Nữ",
                    XDate.toString(nv.getNgaySinh()),
                    nv.getSdt(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getVaiTro(),
                    nv.getTrangThai() ? "Hoạt Động" : "Không Hoạt Động"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtTenNV.setText(nv.getTenNV());
        txtMatKhau.setText(nv.getMatKhau());
        rdoNam.setSelected(nv.getGioiTinh());
        rdoNu.setSelected(!nv.getGioiTinh());
        txtNgaySinh.setText(XDate.toString(nv.getNgaySinh()));
        txtSDT.setText(nv.getSdt());
        txtEmail.setText(nv.getEmail());
        txtDiaChi.setText(nv.getDiaChi());
        cboVaiTro.setSelectedItem(nv.getVaiTro());
        cboTrangThai.setSelectedItem(nv.getTrangThai());
    }

    NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setTenNV(txtTenNV.getText());
        nv.setMatKhau(new String(txtMatKhau.getPassword()));  //chuyển char[] thành String
        nv.setGioiTinh(rdoNam.isSelected());
        nv.setNgaySinh(XDate.toDate(txtNgaySinh.getText()));
        nv.setSdt(txtSDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setVaiTro(cboVaiTro.getSelectedItem() + "");
        nv.setTrangThai(cboTrangThai.getSelectedIndex() == 0);
        return nv;
    }

    public boolean checkTrungMa(JTextField txt) {
        txt.setBackground(white);
        if (dao.selectByID(txt.getText()) == null) {
            return true;
        } else {
            txt.setBackground(pink);
            XMgsbox.alert(this, txt.getName() + " đã tồn tại.");
            return false;
        }
    }

    public void setTrang() {
        txtMaNV.setBackground(white);
        txtTenNV.setBackground(white);
        txtMatKhau.setBackground(white);
        rdoNam.setSelected(true);
        txtNgaySinh.setBackground(white);
        txtSDT.setBackground(white);
        txtEmail.setBackground(white);
        txtDiaChi.setBackground(white);
        cboVaiTro.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
    }

    void clear() {
        setTrang();
        this.setForm(new NhanVien());  //xóa trắng form
    }

    void edit() {
        setTrang();
        try {
            String manv = (String) tblNhanVien.getValueAt(this.index, 0);  //lấy maNV từ bảng theo index
            NhanVien nv = dao.selectByID(manv); //dùng maNV tìm ra đối tượng nhanVien
            if (nv != null) {
                this.setForm(nv);   //điền thông tin dt nhanVien lên form
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    //lấy thông tin trên form để
    //thêm (đăng kí) nhân viên - trưởng phòng vào CSDL
    void insert() {
        //getForm() viết ở dưới, lấy thông tin trên form điền vào dt nv
        NhanVien nv = getForm();
        try {
            dao.insert(nv);
            this.fillToTable();    //cập nhật lại bảng nv
            this.clear();   // xóa trắng form
            XMgsbox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Thêm mới thất bại!");
        }
    }

    //lấy thông tin trên form để
    //cập nhật nhanVien theo maNV
    void update() {
        NhanVien nv = getForm();
        try {
            dao.update(nv);     //cập nhật nhân viên theo maNV
            this.fillToTable(); //điền tt mới vào bảng
            this.clear();
            XMgsbox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            XMgsbox.alert(this, "Cập nhật thất bại!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        cboVaiTro = new javax.swing.JComboBox<>();
        txtMatKhau = new javax.swing.JPasswordField();

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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(201, 193, 141));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_people_36px 1.png"))); // NOI18N
        jLabel1.setText("Quản lý Nhân Viên");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel2.setBackground(new java.awt.Color(212, 181, 142));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnTimKiem.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_28px 1.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

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
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã NV");

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên NV");

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mật khẩu");

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Giới tính");

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SDT");

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email");

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Địa chỉ");

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vai trò");

        btnThem.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Create.png"))); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ngày sinh");

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Trạng thái");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không Hoạt Động" }));

        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV)
                            .addComponent(txtNgaySinh)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(28, 28, 28)
                                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSDT)
                            .addComponent(txtEmail)
                            .addComponent(txtDiaChi)
                            .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
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
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(rdoNam)
                        .addComponent(rdoNu)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaNV)
                && XCheck.checkNullText(txtTenNV)
                && XCheck.checkNullPass(txtMatKhau)
                && XCheck.checkNullText(txtNgaySinh)
                && XCheck.checkNullText(txtSDT)
                && XCheck.checkNullText(txtEmail)
                && XCheck.checkNullText(txtDiaChi)) {
            if (XCheck.checkMaNV(txtMaNV)
                    && XCheck.checkName(txtTenNV)
                    && XCheck.checkPass(txtMatKhau)
                    && XCheck.checkDate(txtNgaySinh)
                    && XCheck.checkSDT(txtSDT)
                    && XCheck.checkEmail(txtEmail)) {
                if (checkTrungMa(txtMaNV)) {
                    this.insert();
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtMaNV)
                && XCheck.checkNullText(txtTenNV)
                && XCheck.checkNullPass(txtMatKhau)
                && XCheck.checkNullText(txtNgaySinh)
                && XCheck.checkNullText(txtSDT)
                && XCheck.checkNullText(txtEmail)
                && XCheck.checkNullText(txtDiaChi)) {
            if (XCheck.checkMaNV(txtMaNV)
                    && XCheck.checkName(txtTenNV)
                    && XCheck.checkPass(txtMatKhau)
                    && XCheck.checkDate(txtNgaySinh)
                    && XCheck.checkSDT(txtSDT)
                    && XCheck.checkEmail(txtEmail)) {
                update();
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        this.index = tblNhanVien.rowAtPoint(evt.getPoint());//lấy vị trí dòng được chọn
        if (this.index >= 0) {
            this.edit();
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        fillToTable();
    }//GEN-LAST:event_formWindowOpened

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String timkiem = txtTimKiem.getText();
        NhanVien nv = dao.selectByID(timkiem);
        if (nv != null) {
            setForm(nv);
            XMgsbox.alert(this, "Đã tìm thấy");
        } else if (nv == null) {
            XMgsbox.alert(this, "Không có nhân viên bạn cần tìm");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(main_form_QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_form_QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_form_QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_form_QLNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new main_form_QLNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JComboBox<String> cboVaiTro;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
