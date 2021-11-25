package com.library.form;

import com.library.dao.CTPhieuMuonDAO;
import com.library.dao.KhachHangDAO;
import com.library.dao.PhieuMuonDao;
import com.library.dao.SachDAO;
import com.library.dao.theLoaiDAO;
import com.library.entity.CTPhieuMuon;
import com.library.entity.KhachHang;
import com.library.entity.PhieuMuon;
import com.library.entity.Sach;
import com.library.entity.TheLoai;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class form_MuonSach extends javax.swing.JFrame {

    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel mol2 = new DefaultTableModel();
    SachDAO daoSA = new SachDAO();
    PhieuMuonDao daoPM = new PhieuMuonDao();
    CTPhieuMuonDAO daoCTPM = new CTPhieuMuonDAO();
    theLoaiDAO daoTL = new theLoaiDAO();
    KhachHangDAO daoKH = new KhachHangDAO();
    int tongMuon = 0;
    int gioiHan;
    Sach sachChon;
    int index;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDateTime now = LocalDateTime.now();
    public Connection conn = XJdbc.getConnection();

    public form_MuonSach() {
        initComponents();
        gioiHan = 3 - XAuther.UserKH.getSoLuongMuon();

        // bảng 1
        mol.setColumnCount(0);
        mol = (DefaultTableModel) tblBangSach.getModel();
        String col[] = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Năm XB", "NXB", "Giá tiền", "Nơi đặt"};
        for (String x : col) {
            mol.addColumn(x);
        }

        //Bảng 2
        mol2.setColumnCount(0);
        mol2 = (DefaultTableModel) tblBangSachMuon.getModel();
        String col2[] = {"Mã sách", "Tên sách", "Tác giả", "Thể loại", "Năm XB", "NXB", "Giá tiền", "Nơi đặt"};
        for (String y : col2) {
            mol2.addColumn(y);
        }
        init();
        fillComboBoxTheLoai();
        fillTableSachMuon();
        lblGioiHan.setText(String.valueOf(gioiHan));
        setTongMuon();
        txtNgayMuon.setText(dtf.format(now));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void init() {
        setIconImage(XImages.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Thư Viện Đại Học Hà Nội - Hanoi University Library");
    }

    void fillComboBoxTheLoai() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheLoai.getModel();
        model.removeAllElements();
        cboTheLoai.addItem("Thể Loại");
        List<TheLoai> list = daoTL.selectAll();
        for (TheLoai tl : list) {
            model.addElement(tl);
        }
    }

    void fillTableCboTL() {
        String selectTL = cboTheLoai.getSelectedItem().toString();
        if (selectTL.equalsIgnoreCase("Thể Loại")) {
            fillTableALL();
        } else {
            DefaultTableModel model = (DefaultTableModel) tblBangSach.getModel();
            model.setRowCount(0); // xoa tat ca cac hang
            try {
                TheLoai tl = (TheLoai) cboTheLoai.getSelectedItem();
                List<Sach> list = daoSA.selectByMaTL(tl.getMaTl());
                for (Sach sa : list) {
                    Object[] row = {
                        sa.getMaSach(),
                        sa.getTenSach(),
                        sa.getTacGia(),
                        sa.getTl(),
                        sa.getNamXB(),
                        sa.getnXB(),
                        sa.getGiaTien(),
                        sa.getNoiDat()
                    };
                    model.addRow(row); //them 1 hang vao table
                }
            } catch (Exception e) {
                XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
            }
        }
    }

    void fillTableALL() {
        DefaultTableModel model = (DefaultTableModel) tblBangSach.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
        try {
            List<Sach> list = daoSA.selectAll();
            for (Sach sa : list) {
                Object[] row = {
                    sa.getMaSach(),
                    sa.getTenSach(),
                    sa.getTacGia(),
                    sa.getTl(),
                    XDate.toString(sa.getNamXB()),
                    sa.getnXB(),
                    sa.getGiaTien(),
                    sa.getNoiDat()
                };
                model.addRow(row); //them 1 hang vao table
            }
        } catch (Exception e) {
            XMgsbox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillTableSachMuon() {
        DefaultTableModel model = (DefaultTableModel) tblBangSachMuon.getModel();
        model.setRowCount(0); // xoa tat ca cac hang
    }

    public void setTongMuon() {
        tongMuon = tblBangSachMuon.getRowCount();
        lblTongMuon.setText(String.valueOf(tongMuon));
        if (tongMuon > gioiHan) {
            lblTongMuon.setForeground(Color.red);
        } else {
            lblTongMuon.setForeground(Color.black);
        }
    }

    public boolean ktDSMuon(String masach) {
        TableRowSorter<DefaultTableModel> tbl = new TableRowSorter<DefaultTableModel>(mol2);
        tbl.setRowFilter(RowFilter.regexFilter(masach));
        if (tbl.getViewRowCount() != 0) {
            return true;
        }
        return false;
    }

    public void addDSMuon(Sach sa) {
        if (ktDSMuon(sa.getMaSach())) {
            XMgsbox.alert(this, "Sách này đã có trong danh mục sách mượn");
        } else {
            mol2.addRow(new Object[]{
                sa.getMaSach(),
                sa.getTenSach(),
                sa.getTacGia(),
                sa.getTl(),
                XDate.toString(sa.getNamXB()),
                sa.getnXB(),
                sa.getGiaTien(),
                sa.getNoiDat()
            });
            setTongMuon();
            XMgsbox.alert(this, "Mượn Sách thành công");
        }
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

    public boolean checkNgayTra(JTextField txt, JTextField txt2) {
        txt.setBackground(white);
        LocalDate date = LocalDate.parse(txt.getText());
        LocalDate date2 = LocalDate.parse(txt2.getText());
        if (date2.isBefore(date)) {
            txt2.setBackground(pink);
            XMgsbox.alert(this, "Ngày Trả không được nhỏ hơn Ngày Mượn");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkSoLuongMuon(String maKH) {
        String sql = "select SOLUONGMUON from PHIEUMUON, KHACHHANG where MAKH like '%" + maKH + "%'";
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

    KhachHang getForm() {
        KhachHang kh = new KhachHang();
        kh.setMaKH(XAuther.UserKH.getMaKH());
        kh.setSoLuongMuon(XAuther.UserKH.getSoLuongMuon() + Integer.parseInt(lblTongMuon.getText()));
        return kh;
    }

    void updateSLMuon() {
        KhachHang kh = getForm();
        try {
            daoKH.updateSLMuon2(kh);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboTheLoai = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangSach = new javax.swing.JTable();
        btnMuonSach = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangSachMuon = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        sprt1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        txtNgayMuon = new javax.swing.JTextField();
        txtNgayTra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblTongMuon = new javax.swing.JLabel();
        lblGioiHan = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(179, 115, 90));

        jPanel2.setBackground(new java.awt.Color(235, 231, 216));

        jPanel4.setBackground(new java.awt.Color(207, 206, 193));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        btnTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setFont(new java.awt.Font("Bahnschrift", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_28px 1.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Thể loại:");

        cboTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblBangSach.setBackground(new java.awt.Color(207, 206, 193));
        tblBangSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblBangSach);

        btnMuonSach.setBackground(new java.awt.Color(153, 153, 153));
        btnMuonSach.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        btnMuonSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_reading_28px 2.png"))); // NOI18N
        btnMuonSach.setText("Mượn sách");
        btnMuonSach.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnMuonSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuonSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnMuonSach, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin sách", jPanel2);

        jPanel3.setBackground(new java.awt.Color(235, 231, 216));

        tblBangSachMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblBangSachMuon);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Tổng số sách được mượn:");

        btnXoa.setBackground(new java.awt.Color(153, 153, 153));
        btnXoa.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_delete_trash_31px 1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        sprt1.setBackground(new java.awt.Color(0, 0, 0));
        sprt1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ngày mượn");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ngày trả");

        btnXacNhan.setBackground(new java.awt.Color(153, 153, 153));
        btnXacNhan.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnXacNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_book_reading_28px 2.png"))); // NOI18N
        btnXacNhan.setText("Xác nhận mượn sách");
        btnXacNhan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        txtNgayMuon.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("/");

        lblTongMuon.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lblGioiHan.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("* Mỗi Khách Hàng Chỉ Được Mượn Tối Đa 3 Cuốn Sách");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sprt1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXacNhan)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGioiHan, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGioiHan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(btnXoa)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addComponent(sprt1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnXacNhan)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sách đang mượn", jPanel3);

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Thông tin sách tài liệu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiActionPerformed
        // TODO add your handling code here:
        fillTableCboTL();
    }//GEN-LAST:event_cboTheLoaiActionPerformed

    private void btnMuonSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuonSachActionPerformed
        // TODO add your handling code here:
        this.index = tblBangSach.getSelectedRow();//lấy vị trí dòng được chọn     
        Sach sa = new Sach();
        sa.setMaSach(tblBangSach.getValueAt(index, 0) + "");
        sa.setTenSach(tblBangSach.getValueAt(index, 1) + "");
        sa.setTacGia(tblBangSach.getValueAt(index, 2) + "");
        sa.setTl((TheLoai) tblBangSach.getValueAt(index, 3));
        sa.setNamXB(XDate.toDate(tblBangSach.getValueAt(index, 4) + ""));
        sa.setnXB(tblBangSach.getValueAt(index, 5) + "");
        sa.setGiaTien(Float.parseFloat(tblBangSach.getValueAt(index, 6) + ""));
        sa.setNoiDat(tblBangSach.getValueAt(index, 7) + "");
        addDSMuon(sa);
    }//GEN-LAST:event_btnMuonSachActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int row = tblBangSachMuon.getSelectedRow();
        if (row == -1) {
            XMgsbox.alert(this, "Bạn Phải Chọn Một Cuốn Sách Để Xóa");
        } else {
            mol2.removeRow(row);
        }
        setTongMuon();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        if (XCheck.checkNullText(txtNgayTra)) {
            if (XCheck.checkDate(txtNgayTra)) {
                if (check14Ngay(txtNgayMuon, txtNgayTra) == true) {
                    if (checkNgayTra(txtNgayMuon, txtNgayTra) == true) {
                        if (tongMuon == 0) {
                            XMgsbox.alert(this, "Bạn Đã Hết Số Lượt Mượt Sách! Vui Lòng Trả Sách Để Tiếp Tục Mượn");
                        } else if (tongMuon > gioiHan) {
                            if (gioiHan == 0) {
                                XMgsbox.alert(this, "Bạn Đã Hết Số Lượt Mượt Sách! Vui Lòng Trả Sách Để Tiếp Tục Mượn");
                            } else {
                                XMgsbox.alert(this, "Số Sách Mượn Vượt Quá Số Lượt Cho Phép! Vui Lòng Xóa Bớt Sách Để Mượn");
                            }
                        } else {
                            String maPM = daoPM.selectTopMaPM();
                            if (checkSoLuongMuon(XAuther.UserKH.getMaKH()) == true) {
//              Thêm mới một phiếu mượn
                                PhieuMuon pm = new PhieuMuon();
                                pm.setMaKH(XAuther.UserKH.getMaKH());
                                pm.setMaNV(null);
                                pm.setNgayMuon(XDate.toDate(txtNgayMuon.getText()));
                                pm.setNgayTra(XDate.toDate(txtNgayTra.getText()));
                                pm.setSoTienCoc(Float.parseFloat("0.0"));
                                pm.setTrangThai("Chưa Duyệt");
                                daoPM.insert(pm);

//              Thêm các sách vào chi tiết phiếu mượn
                                int rowCount = tblBangSachMuon.getRowCount();
                                String maPM2 = daoPM.selectTopMaPM();
                                for (int i = 0; i < rowCount; i++) {
                                    CTPhieuMuon ctpm = new CTPhieuMuon();
                                    ctpm.setMaPM(Integer.parseInt(maPM2));
                                    ctpm.setMaSach(tblBangSachMuon.getValueAt(i, 0) + "");
                                    ctpm.setTinhTrangSach("Bình Thường");
                                    daoCTPM.insert(ctpm);
                                }
                                updateSLMuon();
                                gioiHan -= rowCount;
                                lblGioiHan.setText(String.valueOf(gioiHan));
                                XMgsbox.alert(this, "Mượn Sách Thành Công! Mời Bạn Đến Thư Viện Trong Thời Gian Sớm Nhất Để Được Mượn Sách");
                                mol2.setRowCount(0);
                                txtNgayTra.setText("");
                                setTongMuon();
                            } else {
                                XMgsbox.alert(this, "Bạn Đã Mượn Tối Đa 3 Quyển Sách");
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanActionPerformed

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
            java.util.logging.Logger.getLogger(form_MuonSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_MuonSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_MuonSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_MuonSach.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_MuonSach().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMuonSach;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblGioiHan;
    private javax.swing.JLabel lblTongMuon;
    private javax.swing.JSeparator sprt1;
    private javax.swing.JTable tblBangSach;
    private javax.swing.JTable tblBangSachMuon;
    private javax.swing.JTextField txtNgayMuon;
    private javax.swing.JTextField txtNgayTra;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
