/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Component;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDao;
import DAO.KhachHangDAO;
import DAO.KhuyenMaiDAO;
import DAO.SanPhamDAO;
import Entity.ChiTietHoaDon;
import Entity.HoaDon;
import Entity.SanPham;
import Entity.KhachHang;
import Map.MapChiTietSanPham;
import Map.MapKhuyenMai;
import Utils.Auth;
import Utils.DialogBox;
import Utils.ValidateInput;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.NumberFormat;
import Utils.MaxLength;
import javax.swing.text.AbstractDocument;
/**
 *
 * @author ADMIN
 */
enum colHD {
    TENSP(0),
    DONGIA(1),
    SOLUONG(2),
    GIAMGIA(3),
    THUE(4);
    int i;

    private colHD(int i) {
        this.i = i;
    }
}

public class HoaDonJDialog extends javax.swing.JFrame {
    private SanPhamDAO daoSP = new SanPhamDAO();
    private KhachHangDAO daoKH = new KhachHangDAO();
    private HoaDonDao daoHD = new HoaDonDao();
    private KhuyenMaiDAO daoKM = new KhuyenMaiDAO();
    private ChiTietHoaDonDAO daoCTHD = new ChiTietHoaDonDAO();
    private MapChiTietSanPham mapCTSP = new MapChiTietSanPham();
    private MapKhuyenMai mapKM = new MapKhuyenMai();
    private Date date = new Date();
    private DecimalFormat dfInt = new DecimalFormat("#");
    private DecimalFormat dfMoney = new DecimalFormat("#,###");
    private NumberFormat numFormat = new NumberFormat();
    private ValidateInput input = new ValidateInput();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private final int THUE_VAT = 10;
    private DefaultTableModel model = new DefaultTableModel();
    
    /**
     * Creates new form HoaDonFormJDialog
     */
    public HoaDonJDialog() throws SQLException {
        initComponents();
        
        init();
    }
    
    // Khởi tạo cho form hóa đơn
    public void init() throws SQLException {
        fillToSanPhamTable();
        fillToHoaDonTable();
        generateForm();
        setGiamGiaThue();
        
        setLocationRelativeTo(null);
    }
    
    // Điền hết tất cả thông tin của hóa đơn vào bảng
    public void fillToSanPhamTable() {
        DefaultTableModel model = new DefaultTableModel();
        List<SanPham> list = daoSP.getAllData();
        
        String[] col = {"Danh sách sản phẩm"};
        
        model.setColumnIdentifiers(col);
        
        for (SanPham o : list) {
            model.addRow(new Object[]{
                o.getTenSP() + ", " + o.getMauSac() + ", " + o.getSize()
            });
        }
        
        tblDanhSachSanPham.setModel(model);
    }
    
    // Điền thông tin của hóa đơn sao khi tìm kiếm
    public void filterToSanPhamTable() {
        DefaultTableModel modelSP = new DefaultTableModel();
        List<SanPham> list = daoSP.getDataByValue(txtTenSanPham.getText());
        
        String[] col = {"Danh sách sản phẩm"};
        
        modelSP.setColumnIdentifiers(col);
        modelSP.setRowCount(0);
        
        System.out.println(tblDanhSachSanPham.getRowCount());
        
        for (SanPham o : list) {
            modelSP.addRow(new Object[]{
                o.getTenSP() + ", " + o.getMauSac() + ", " + o.getSize()
            });
        }
        
        tblDanhSachSanPham.setModel(modelSP);
    }
    
    // Tạo danh sách mã hóa đơn đã tạo
    public List<String> listMaHD() {
        List<HoaDon> list = daoHD.getAllData();
        List<String> maHD = new ArrayList<>();
        
        for (HoaDon o : list) {
            maHD.add(o.getMaHD());
        }
        
        return maHD;
    }
    
    // Tạo mã hóa đơn
    public String generateMaHD() {
        String maHD = null;      
        int count = 1;
        
        // Kiếm tra xem mã hóa đơn vào tạo có nằm trong danh sách đã có hay không
        do {
            maHD = String.format("HD%d", count++);
        } while (listMaHD().contains(maHD));
        
        return maHD;
    } 
    
    public List<String> listMaKH() {
        List<KhachHang> list = daoKH.getAllData();
        List<String> maKH = new ArrayList<>();
        
        for (KhachHang o : list) {
            maKH.add(o.getMaKH());
        }
        
        return maKH;
    }
    
    public String generateMaKH() {
        String maKH = null;
        int count = 1;
        
        do {
            maKH = String.format("KH%d", count++);    
        } while (listMaKH().contains(maKH));
                
        return maKH;
    }
    
    public List<String> listMaCTHD() {
        List<ChiTietHoaDon> list = daoCTHD.getAllData();
        List<String> maCTHD = new ArrayList<>();
        
        for (ChiTietHoaDon o : list) {
            maCTHD.add(o.getMaCTHD());
        }
        
        return maCTHD;
    }
    
    public String generateMaCTHD() {
        String maCTHD = null;
        int count = 1;
        
        do {
            maCTHD = String.format("CTHD%d", count++);    
        } while (listMaCTHD().contains(maCTHD));
                
        return maCTHD;
    }
    
    // Tạo mã nhân viên và tên nhân viên
    public void generateForm() {
        String maNhanVien = "PS43010";
        String tenNhanVien = "Trần Lê Duy Thiện";
        
        // Lấy mã và tên đăng nhập sau khi đăng nhập tài khoảng
        if (Auth.isLogin()) {
            tenNhanVien = Auth.user.getTenNV();
            maNhanVien = Auth.user.getMaNV();
        }
        
        txtMaNV.setText(maNhanVien);
        txtTenNhanVien.setText(tenNhanVien);
        txtNgayBan.setText(formatter.format(date));
        txtMaHD.setText(generateMaHD());
        txtMaKH.setText(generateMaKH());
    }
     
    // Điền thông tin của sản phẩm vào form
    public void showTenSanPham() {
        int index = tblDanhSachSanPham.getSelectedRow();
        String tenSP = (String) tblDanhSachSanPham.getValueAt(index, 0);
        txtTenSanPham.setText(tenSP);
        
        System.out.println("index = " + index);
        System.out.println(tenSP);
    }
    
    // Tạo bảng hóa đơn và thêm thông tin hóa đơn vào bảng
    public void fillToHoaDonTable() {
        String[] col = {
            "Tên mặt hàng",
            "Đơn giá",
            "Số lượng",
            "Giảm giá (%)",
            "Thuế (%)",
            "Thành tiền"
        };
        
        model.setColumnIdentifiers(col);
        
        tblHoaDon.setModel(model);
    }
    
    // Khởi tạo giảm giá và thuế
    public void setGiamGiaThue() throws SQLException {
        float mucKM = daoKM.getDataByDate(date);
        txtGiamGia.setText(dfInt.format(mucKM));
        txtThue.setText(String.valueOf(THUE_VAT));
    }
    
    // Điền tổng tiền vào form
    public void setTongTien() {
        double tongTien = 0;
        final int COL_THANHTIEN = 5;
        
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            String thanhTienInTable = numFormat.removeCommas((String) tblHoaDon.getValueAt(i,COL_THANHTIEN));
            Double thanhTien = Double.valueOf(thanhTienInTable);
            tongTien += thanhTien;
        }
        
        txtTongTien.setText(dfMoney.format(tongTien));
    }
    
    public boolean isCheckValid() {
        StringBuilder sb = new StringBuilder();
        String maHD = txtMaHD.getText();
        String maKH = txtMaKH.getText();
        String tenSP = txtTenSanPham.getText();
        String patternText = "\\s+";
        int count = 0;
        
        if (maHD.equals("") || maHD.matches(patternText)) {
            sb.append("Bạn chưa nhập mã hóa đơn\n");
            count++;
        }
        
        if (maKH.equals("") || maKH.matches(patternText)) {
            sb.append("Bạn chưa nhập mã khách hàng\n");
            count++;
        }
        
        if (tenSP.equals("") || tenSP.matches(patternText)) {
            sb.append("Bạn chưa nhập mã khách hàng\n");
            count++;
        }
        
        if (sb.length() > 0) {
            DialogBox.notice(this, sb.toString());
        }
        
        return count == 0;
    }
    
    public void createKhachHang() {
        if (isCheckValid()) {
            daoKH.insertData(new KhachHang(
                    txtMaKH.getText(), 
                    txtTenKH.getText(), 
                    true, 
                    txtDiaChi.getText(), 
                    txtSoDT.getText()
          ));
        }
    }
    
    public void createHoaDon() {
        if (isCheckValid()) {
            daoHD.insertData(new HoaDon(
                    txtMaHD.getText(), 
                    txtMaNV.getText(), 
                    txtMaKH.getText(),
                    date,
                    "Tiền mặt", 
                    true
            ));
        }
    }
    
    public void createChiTietHoaDon() {
        if (isCheckValid()) {
            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
                String maCTHD = generateMaCTHD();
                String maHD = txtMaHD.getText();
                String maSP = mapCTSP.getIDByValue((String) tblHoaDon.getValueAt(i, colHD.TENSP.i));
                String soLuong = String.valueOf(tblHoaDon.getValueAt(i, colHD.SOLUONG.i));
                String donGia = numFormat.removeCommas((String.valueOf(tblHoaDon.getValueAt(i, colHD.DONGIA.i))));
                String maKM = mapKM.getIDByValue(Integer.valueOf((String) tblHoaDon.getValueAt(i, colHD.GIAMGIA.i)));
                String thue = String.valueOf(tblHoaDon.getValueAt(i, colHD.THUE.i));
                
                daoCTHD.insertData(new ChiTietHoaDon(
                        maCTHD, 
                        maHD, 
                        maSP, 
                        maKM, 
                        Integer.parseInt(soLuong), 
                        Double.parseDouble(donGia), 
                        Integer.parseInt(thue)
                ));
            }
        }
    }

    // Thêm sản phẩm vào bảng hóa đơn
    public void addSanPhamVaoHD() {       
        String tenSP = txtTenSanPham.getText();
        double donGia = Double.parseDouble(txtDonGia.getText());
        int soLuong = Integer.parseInt(txtSoLuong.getText());
        float giamGia = Float.parseFloat(txtGiamGia.getText());
        int thue = Integer.parseInt(txtThue.getText());
        double thanhTien = donGia * soLuong * ((1 - giamGia/100))*(100 + thue)/100;
        
        Object[] row = {
            tenSP,
            dfMoney.format(donGia),
            soLuong,
            dfInt.format(giamGia),
            dfInt.format(thue), 
            dfMoney.format(thanhTien)
        };
        
        model.addRow(row);
        
        tblHoaDon.setModel(model);  
        setTongTien();
    }
    
    public void saveHoaDon() {
        createKhachHang();
        createHoaDon();
        createChiTietHoaDon();
        
        DialogBox.notice(this, "Lưu hóa đơn thành công");
    }
    
    public void removeSanPham() {
        int index = tblHoaDon.getSelectedRow();
        model.removeRow(index);
        
        tblHoaDon.setModel(model);
        
        setTongTien();
    }
    
    public void clearHoaDon() {
        model.setRowCount(0);
        tblHoaDon.setModel(model);
        
        setTongTien();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayBan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachSanPham = new javax.swing.JTable();
        txtThue = new javax.swing.JTextField();
        btnThemHoaDon = new javax.swing.JButton();
        btnLuuHoaDon = new javax.swing.JButton();
        btnHuyHoaDon = new javax.swing.JButton();
        btnXoaKhoiHD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chung", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Mã hóa đơn");

        txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaHDKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Ngày bán");

        txtNgayBan.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Tên nhân viên");

        txtTenNhanVien.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Mã khách hàng");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Tên khách hàng");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Địa chỉ");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Số điện thoại");

        txtTenKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKHKeyPressed(evt);
            }
        });

        txtSoDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoDTKeyPressed(evt);
            }
        });

        txtMaNV.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenNhanVien))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addComponent(txtNgayBan)
                            .addComponent(txtMaNV))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaKH)
                    .addComponent(txtTenKH)
                    .addComponent(txtDiaChi)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("HÓA ĐƠN BÁN HÀNG");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Tên sản phẩm");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Đơn giá");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("Số lượng");

        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyPressed(evt);
            }
        });

        txtDonGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDonGiaKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Giảm giá %");

        txtGiamGia.setEnabled(false);
        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Thuế");

        txtTenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenSanPhamKeyPressed(evt);
            }
        });

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên mặt hàng", "Số lượng", "Đơn giá", "Giảm giá (%)", "Thuế (%)", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(tblHoaDon);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Tổng số tiền cần thanh toán");

        txtTongTien.setEnabled(false);

        tblDanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Danh sách sản phẩm"
            }
        ));
        tblDanhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachSanPham);

        txtThue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtThueKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThue, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTongTien)
                .addGap(27, 27, 27))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtThue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(216, 216, 216))
        );

        btnThemHoaDon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnThemHoaDon.setText("Thêm vào hóa đơn");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });

        btnLuuHoaDon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLuuHoaDon.setText("Lưu hóa đơn");
        btnLuuHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuHoaDonActionPerformed(evt);
            }
        });

        btnHuyHoaDon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnXoaKhoiHD.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnXoaKhoiHD.setText("Xóa khỏi hóa đơn");
        btnXoaKhoiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoiHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnThemHoaDon)
                        .addGap(90, 90, 90)
                        .addComponent(btnXoaKhoiHD)
                        .addGap(85, 85, 85)
                        .addComponent(btnLuuHoaDon)
                        .addGap(96, 96, 96)
                        .addComponent(btnHuyHoaDon)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaKhoiHD)
                    .addComponent(btnLuuHoaDon)
                    .addComponent(btnHuyHoaDon)
                    .addComponent(btnThemHoaDon))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        addSanPhamVaoHD();
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void tblDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSanPhamMouseClicked
        showTenSanPham();
    }//GEN-LAST:event_tblDanhSachSanPhamMouseClicked

    private void txtTenSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenSanPhamKeyPressed
        filterToSanPhamTable();
    }//GEN-LAST:event_txtTenSanPhamKeyPressed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        clearHoaDon();
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void txtDonGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDonGiaKeyPressed
        input.inputNumber(txtDonGia);
    }//GEN-LAST:event_txtDonGiaKeyPressed

    private void txtSoLuongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyPressed
        input.inputNumber(txtSoLuong);
    }//GEN-LAST:event_txtSoLuongKeyPressed

    private void btnLuuHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuHoaDonActionPerformed
        saveHoaDon();
    }//GEN-LAST:event_btnLuuHoaDonActionPerformed

    private void txtGiamGiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyPressed
        input.inputNumber(txtGiamGia);
        ((AbstractDocument)txtGiamGia.getDocument()).setDocumentFilter(new MaxLength(2));
    }//GEN-LAST:event_txtGiamGiaKeyPressed

    private void txtThueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtThueKeyPressed
        input.inputNumber(txtThue);
        ((AbstractDocument) txtThue.getDocument()).setDocumentFilter(new MaxLength(2));
    }//GEN-LAST:event_txtThueKeyPressed

    private void txtSoDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoDTKeyPressed
        input.inputPhone(txtSoDT);
    }//GEN-LAST:event_txtSoDTKeyPressed

    private void btnXoaKhoiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoiHDActionPerformed
        removeSanPham();
    }//GEN-LAST:event_btnXoaKhoiHDActionPerformed

    private void txtTenKHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKHKeyPressed
        input.inputText(txtTenKH);
        ((AbstractDocument)txtTenKH.getDocument()).setDocumentFilter(new MaxLength(50));
    }//GEN-LAST:event_txtTenKHKeyPressed

    private void txtMaHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyPressed
        ((AbstractDocument)txtMaHD.getDocument()).setDocumentFilter(new MaxLength(10));
    }//GEN-LAST:event_txtMaHDKeyPressed

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
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HoaDonJDialog().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(HoaDonJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLuuHoaDon;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JButton btnXoaKhoiHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDanhSachSanPham;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgayBan;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtThue;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
