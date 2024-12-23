/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.Component;
import DAO.KhachHangDAO;
import Interfaces.Initialize;
import Interfaces.CheckForm;
import Interfaces.CrudController;
import Entity.KhachHang;
import Utils.DialogBox;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author anhth
 */
public class KhachHangJDialog extends javax.swing.JFrame implements Initialize<KhachHang>,CheckForm<KhachHang, String>, CrudController{
    private KhachHangDAO dao = new KhachHangDAO();
    
    
    public KhachHangJDialog() {
        initComponents();
        init();
    }
    
    @Override
    public void init() {
        fillToTable();
        //filterTable();
        setLocationRelativeTo(null);
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model = new DefaultTableModel();
        List<KhachHang> list = dao.getAllData();
        
        String[] col = {
           "Mã khách hàng",
           "Tên khách hàng",
           "Giới tính",
           "Số điện thoại",
           "Địa chỉ"
        };
        
        model.setColumnIdentifiers(col);
        
        for(KhachHang o : list){
            model.addRow(new Object[]{
                o.getMaKH(),
                o.getTenKH(),
                o.isGioiTinh()?"Nam":"Nữ",
                o.getSoDT(),
                o.getDiaChi()
            });
        }
        tblKhachHang.setModel(model);
    }

    @Override
    public void filterTable() {
        DefaultTableModel model = new DefaultTableModel();
        List<KhachHang> list = dao.getDataByValue(txtSearch.getText());
        
        String[] col = {
            "Mã khách hàng",
            "Tên khách hàng",
            "Giới tính",
            "Số điện thoại",
            "Địa chỉ"
        };
        
        model.setColumnIdentifiers(col);
        
        for(KhachHang o : list){
            model.addRow(new Object[]{
                o.getMaKH(),
                o.getTenKH(),
                o.isGioiTinh()?"Nam":"Nữ",
                o.getSoDT(),
                o.getDiaChi()
            });
        }
        tblKhachHang.setModel(model);
    }

    @Override
    public void generateCbx() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void getForm(int index) {
       String maKH = (String) tblKhachHang.getValueAt(index, 0);
       String tenKH = (String) tblKhachHang.getValueAt(index, 1);
       String gioiTinhStr = (String) tblKhachHang.getValueAt(index, 2);
       String sdt = (String) tblKhachHang.getValueAt(index, 3);
       String diachi = (String) tblKhachHang.getValueAt(index, 4);
       
       boolean gioiTinh = gioiTinhStr.equals("Nam");
       rdnNam.setSelected(gioiTinh);
       rdnNu.setSelected(gioiTinh);
       
       KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, sdt, diachi);
       setForm(kh);
    }

    @Override
    public void showDetail() {
        int index = tblKhachHang.getSelectedRow();
        getForm(index);
    }

    @Override
    public void setForm(KhachHang o) {
        txtMaKH.setText(o.getMaKH());
        txtTenKH.setText(o.getTenKH());
        rdnNam.setSelected(o.isGioiTinh());
        rdnNu.setSelected(o.isGioiTinh());
        txtSDT.setText(o.getSoDT());
        txaDiaChi.setText(o.getDiaChi());
    }

    @Override
    public boolean isCheckValid() {
        StringBuilder sb = new StringBuilder();
        String maKH = txtMaKH.getText();
        String tenKH = txtTenKH.getText();
        String sdt = txtSDT.getText();
        String diachi = txaDiaChi.getText();
        String patternText = "\\s+";
        int count = 0;
        
        if (tenKH.equals("") || tenKH.matches(patternText)) {
            sb.append("Bạn chưa nhập tên\n");
            count++;
        }
        
        if (maKH.equals("") || maKH.matches(patternText)) {
            sb.append("Bạn chưa nhập mã\n");
            count++;
        }
        
        
        
        if(sdt.equals("") || sdt.matches(patternText)) {
            sb.append("Bạn chưa nhập số điện thoại");
            count++;
        }
        
        if(diachi.equals("") || diachi.matches(patternText)) {
            sb.append("Bạn chưa nhập địa chỉ");
            count++;
        }
        
        if (sb.length() > 0) {
            DialogBox.notice(this, sb.toString());
        }
        
        return count == 0;
    }

    @Override
    public boolean isCheckContain(List<KhachHang> list, String ma) {
        int count = 0;
        for (KhachHang o : list) {
            if (ma.equals(o.getMaKH())) count++;
        }
        
        return count != 0;
    }

    @Override
    public boolean isCheckDuplicate() {
        List<KhachHang> list = dao.getAllData();
        String ma = txtMaKH.getText();
        
        if (isCheckContain(list, ma)) {
            DialogBox.notice(this, "Mã loại này có rồi. Vui lòng nhập mã loại khác");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCheckUpdate() {
        List<KhachHang> list = dao.getAllData();
        String ma = txtMaKH.getText();
        
        if (!isCheckContain(list, ma)) {
            DialogBox.notice(this, "Không tìm thấy khách hàng cần sửa");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCheckLength() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isCheckDelete() {
        List<KhachHang> list = dao.getAllData();
        String ma = txtMaKH.getText();
        
        if (!isCheckContain(list, ma)) {
            DialogBox.notice(this, "Không tìm thấy khách hàng cần xóa");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void create() {
        if (isCheckValid()) {
            if (isCheckDuplicate()) {
                String maKH = txtMaKH.getText();
                String tenKH = txtTenKH.getText();
                String sdt = txtSDT.getText();
                String diachi = txaDiaChi.getText();
                Boolean gioiTinh = rdnNam.isSelected();
              
                dao.insertData(new KhachHang(maKH, tenKH, gioiTinh, sdt, diachi)); 
                DialogBox.notice(this, "Thêm thành công");
                reset();
                fillToTable();
            }
        }


    }

    @Override
    public void reset() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        rdnNam.setSelected(true);
        txtSDT.setText("");
        txaDiaChi.setText("");
    }

    @Override
    public void update() {
        if(isCheckValid()){
//            if(isCheckDuplicate()) {
                String maKH = txtMaKH.getText();
                String tenKH = txtTenKH.getText();
                String sdt = txtSDT.getText();
                String diachi = txaDiaChi.getText();
                Boolean gioiTinh = rdnNam.isSelected();
              
                dao.insertData(new KhachHang(maKH, tenKH, gioiTinh, sdt, diachi)); 
                DialogBox.notice(this, "Sửa thành công");
                reset();
                fillToTable(); 
//            };
        }
        
      }

    @Override
    public void delete() {
        isCheckDelete();
        dao.deleteById(txtMaKH.getText());
        fillToTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgrGioiTinh = new javax.swing.ButtonGroup();
        pnThietLap = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnChucNang = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnRenew = new javax.swing.JButton();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        rdnNam = new javax.swing.JRadioButton();
        rdnNu = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDiaChi = new javax.swing.JTextArea();
        pnThongTIn = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnThietLap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnThietLap.setName(""); // NOI18N

        jLabel1.setText("Mã khách hàng:");

        jLabel2.setText("Tên khách hàng:");

        jLabel3.setText("Giới tính:");

        jLabel4.setText("Số điện thoại:");

        jLabel6.setText("Địa chỉ");

        pnChucNang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnRenew.setText("Làm mới");
        btnRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnChucNangLayout = new javax.swing.GroupLayout(pnChucNang);
        pnChucNang.setLayout(pnChucNangLayout);
        pnChucNangLayout.setHorizontalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRenew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnChucNangLayout.setVerticalGroup(
            pnChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(btnDelete)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnRenew)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        btgrGioiTinh.add(rdnNam);
        rdnNam.setText("Nam");

        btgrGioiTinh.add(rdnNu);
        rdnNu.setText("Nữ");

        txaDiaChi.setColumns(20);
        txaDiaChi.setRows(5);
        jScrollPane3.setViewportView(txaDiaChi);

        javax.swing.GroupLayout pnThietLapLayout = new javax.swing.GroupLayout(pnThietLap);
        pnThietLap.setLayout(pnThietLapLayout);
        pnThietLapLayout.setHorizontalGroup(
            pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThietLapLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnThietLapLayout.createSequentialGroup()
                        .addComponent(rdnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdnNu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnThietLapLayout.createSequentialGroup()
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(pnChucNang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        pnThietLapLayout.setVerticalGroup(
            pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThietLapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnThietLapLayout.createSequentialGroup()
                        .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(rdnNam)
                            .addComponent(rdnNu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnChucNang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnThongTIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Tìm kiếm:");

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Tên KH", "Giới tính", "SĐT", "Địa chỉ"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhachHang);

        javax.swing.GroupLayout pnThongTInLayout = new javax.swing.GroupLayout(pnThongTIn);
        pnThongTIn.setLayout(pnThongTInLayout);
        pnThongTInLayout.setHorizontalGroup(
            pnThongTInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTInLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnThongTInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnThongTInLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(38, 38, 38)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnThongTInLayout.setVerticalGroup(
            pnThongTInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTInLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnThongTInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel8.setText("Thiết lập thông tin khách hàng");

        jLabel9.setText("Thông tin khách hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnThongTIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnThietLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnThietLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(pnThongTIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        create();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenewActionPerformed
        reset();
    }//GEN-LAST:event_btnRenewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
        filterTable();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

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
            java.util.logging.Logger.getLogger(KhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhachHangJDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgrGioiTinh;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRenew;
    private javax.swing.JButton btnSua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnChucNang;
    private javax.swing.JPanel pnThietLap;
    private javax.swing.JPanel pnThongTIn;
    private javax.swing.JRadioButton rdnNam;
    private javax.swing.JRadioButton rdnNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextArea txaDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables

    
}
