/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import UI.Component.DoanhThuJDialog;
import Interfaces.Initialize;
import UI.Component.KhachHangJDialog;
import UI.Component.KhuyenMaiJDialog;
import UI.Component.NhanVienJDialog;
import UI.Component.NhapHangJDialog;
import UI.Component.SanPhamJDialog;
import javax.swing.JFrame;

/**
 *
 * @author hp
 */
public class MenuJDialog extends javax.swing.JFrame implements Initialize<Object> { 
    /**
     * Creates new form MenuJDialog
     */
    public MenuJDialog() {
        initComponents();
        init();
    }

    private void showPanel(JPanel visiblePanel) {
        pnDoanhThu.setVisible(false);
        pnKhachHang.setVisible(false);
        pnNhanVien.setVisible(false);
        pnSanPham.setVisible(false);
        pnKhuyenMai.setVisible(false);
        pnNhapHang.setVisible(false);
        visiblePanel.setVisible(true);
    }

    public void showDialogInPanel(JPanel targetPanel, JFrame dialog) {
        // Tạo một JPanel để chứa nội dung của dialog
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout()); // Thiết lập layout BorderLayout cho contentPanel

        // Thêm nội dung của dialog vào giữa contentPanel
        contentPanel.add(dialog.getContentPane(), BorderLayout.CENTER);

        // Đặt kích thước ưu tiên của contentPanel bằng kích thước của panel đích
        contentPanel.setPreferredSize(new Dimension(targetPanel.getWidth(), targetPanel.getHeight()));

        // Xóa tất cả các thành phần hiện có trong targetPanel
        targetPanel.removeAll();

        // Thiết lập layout cho targetPanel
        targetPanel.setLayout(new BorderLayout());

        // Thêm contentPanel vào targetPanel
        targetPanel.add(contentPanel, BorderLayout.CENTER);

        // Cập nhật lại giao diện của targetPanel để đảm bảo các thay đổi được áp dụng
        targetPanel.revalidate();
        targetPanel.repaint();

        // Điều chỉnh kích thước của dialog để vừa với nội dung
        dialog.pack();
    }

    @Override
    public void init() {
        setLocationRelativeTo(null);
    }

    @Override
    public void fillToTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setForm(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void getForm(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void generateCbx() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        pnSideMenu = new javax.swing.JPanel();
        lblBanHang = new javax.swing.JLabel();
        lblSanPham = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblKhuyenMai = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        lblThoat = new javax.swing.JLabel();
        lblNhapHang = new javax.swing.JLabel();
        pnContainers = new javax.swing.JPanel();
        pnDoanhThu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnKhachHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnNhanVien = new javax.swing.JPanel();
        pnSanPham = new javax.swing.JPanel();
        pnKhuyenMai = new javax.swing.JPanel();
        pnNhapHang = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        pnSideMenu.setBackground(new java.awt.Color(153, 153, 153));

        lblBanHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHang.setText("Bán Hàng");
        lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBanHangMouseClicked(evt);
            }
        });

        lblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lblSanPham.setText("Sản Phẩm");
        lblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSanPhamMouseClicked(evt);
            }
        });

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lblHoaDon.setText("Hóa Đơn");

        lblKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        lblKhuyenMai.setText("Khuyến Mại");
        lblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhuyenMaiMouseClicked(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setText("Nhân Viên");
        lblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhanVienMouseClicked(evt);
            }
        });

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lblKhachHang.setText("Khách Hàng");
        lblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKhachHangMouseClicked(evt);
            }
        });

        lblDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThu.setText("Thống Kê");
        lblDoanhThu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDoanhThuMouseClicked(evt);
            }
        });

        lblThoat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblThoat.setForeground(new java.awt.Color(255, 255, 255));
        lblThoat.setText("Thoát");

        lblNhapHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        lblNhapHang.setText("Nhập  Hàng");
        lblNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNhapHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnSideMenuLayout = new javax.swing.GroupLayout(pnSideMenu);
        pnSideMenu.setLayout(pnSideMenuLayout);
        pnSideMenuLayout.setHorizontalGroup(
            pnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSideMenuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDoanhThu)
                    .addComponent(lblBanHang)
                    .addComponent(lblSanPham)
                    .addComponent(lblHoaDon)
                    .addComponent(lblKhuyenMai)
                    .addComponent(lblNhanVien)
                    .addComponent(lblKhachHang)
                    .addComponent(lblThoat)
                    .addComponent(lblNhapHang))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnSideMenuLayout.setVerticalGroup(
            pnSideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSideMenuLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(lblBanHang)
                .addGap(26, 26, 26)
                .addComponent(lblSanPham)
                .addGap(32, 32, 32)
                .addComponent(lblHoaDon)
                .addGap(26, 26, 26)
                .addComponent(lblKhuyenMai)
                .addGap(34, 34, 34)
                .addComponent(lblNhanVien)
                .addGap(32, 32, 32)
                .addComponent(lblKhachHang)
                .addGap(26, 26, 26)
                .addComponent(lblDoanhThu)
                .addGap(27, 27, 27)
                .addComponent(lblNhapHang)
                .addGap(33, 33, 33)
                .addComponent(lblThoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnContainers.setBackground(new java.awt.Color(255, 255, 255));
        pnContainers.setLayout(new java.awt.CardLayout());

        pnDoanhThu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("DoanhThu");

        javax.swing.GroupLayout pnDoanhThuLayout = new javax.swing.GroupLayout(pnDoanhThu);
        pnDoanhThu.setLayout(pnDoanhThuLayout);
        pnDoanhThuLayout.setHorizontalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDoanhThuLayout.createSequentialGroup()
                .addGap(533, 533, 533)
                .addComponent(jLabel1)
                .addContainerGap(536, Short.MAX_VALUE))
        );
        pnDoanhThuLayout.setVerticalGroup(
            pnDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDoanhThuLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jLabel1)
                .addContainerGap(622, Short.MAX_VALUE))
        );

        pnContainers.add(pnDoanhThu, "card2");

        jLabel2.setText("Khách Hàng");

        javax.swing.GroupLayout pnKhachHangLayout = new javax.swing.GroupLayout(pnKhachHang);
        pnKhachHang.setLayout(pnKhachHangLayout);
        pnKhachHangLayout.setHorizontalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangLayout.createSequentialGroup()
                .addGap(511, 511, 511)
                .addComponent(jLabel2)
                .addContainerGap(548, Short.MAX_VALUE))
        );
        pnKhachHangLayout.setVerticalGroup(
            pnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnKhachHangLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel2)
                .addContainerGap(689, Short.MAX_VALUE))
        );

        pnContainers.add(pnKhachHang, "card3");

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
        );

        pnContainers.add(pnNhanVien, "card4");

        javax.swing.GroupLayout pnSanPhamLayout = new javax.swing.GroupLayout(pnSanPham);
        pnSanPham.setLayout(pnSanPhamLayout);
        pnSanPhamLayout.setHorizontalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
        );
        pnSanPhamLayout.setVerticalGroup(
            pnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
        );

        pnContainers.add(pnSanPham, "card5");

        javax.swing.GroupLayout pnKhuyenMaiLayout = new javax.swing.GroupLayout(pnKhuyenMai);
        pnKhuyenMai.setLayout(pnKhuyenMaiLayout);
        pnKhuyenMaiLayout.setHorizontalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
        );
        pnKhuyenMaiLayout.setVerticalGroup(
            pnKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
        );

        pnContainers.add(pnKhuyenMai, "card6");

        javax.swing.GroupLayout pnNhapHangLayout = new javax.swing.GroupLayout(pnNhapHang);
        pnNhapHang.setLayout(pnNhapHangLayout);
        pnNhapHangLayout.setHorizontalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1124, Short.MAX_VALUE)
        );
        pnNhapHangLayout.setVerticalGroup(
            pnNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 797, Short.MAX_VALUE)
        );

        pnContainers.add(pnNhapHang, "card7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnSideMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnContainers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnSideMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnContainers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void lblBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseClicked
        // TODO add your handling code here:
        //Hiên thị panel và ẩn panel
    }//GEN-LAST:event_lblBanHangMouseClicked

    private void lblDoanhThuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDoanhThuMouseClicked
        // TODO add your handling code here:
        //Hiên thị panel và ẩn panel
        showPanel(pnDoanhThu);
        //Khởi tạo đố tượng
        DoanhThuJDialog dialog = new DoanhThuJDialog();
        showDialogInPanel(pnDoanhThu, dialog);
    }//GEN-LAST:event_lblDoanhThuMouseClicked

    private void lblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhachHangMouseClicked
        // TODO add your handling code here:
        showPanel(pnKhachHang);
        KhachHangJDialog dialog= new KhachHangJDialog();
        showDialogInPanel(pnKhachHang, dialog);
    }//GEN-LAST:event_lblKhachHangMouseClicked

    private void lblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhanVienMouseClicked
        // TODO add your handling code here:
        showPanel(pnNhanVien);
        NhanVienJDialog dialog=new NhanVienJDialog();
        showDialogInPanel(pnNhanVien, dialog);
    }//GEN-LAST:event_lblNhanVienMouseClicked

    private void lblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSanPhamMouseClicked
        // TODO add your handling code here:
        showPanel(pnSanPham);
        SanPhamJDialog dialog=new SanPhamJDialog();
        showDialogInPanel(pnSanPham, dialog);

    }//GEN-LAST:event_lblSanPhamMouseClicked

    private void lblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKhuyenMaiMouseClicked
        // TODO add your handling code here:
        showPanel(pnKhuyenMai);
        KhuyenMaiJDialog dialog=new KhuyenMaiJDialog();
        showDialogInPanel(pnKhuyenMai, dialog);
    }//GEN-LAST:event_lblKhuyenMaiMouseClicked

    private void lblNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNhapHangMouseClicked
        // TODO add your handling code here:
        showPanel(pnNhapHang);
        NhapHangJDialog dialog= new NhapHangJDialog();
        showDialogInPanel(pnNhapHang, dialog);
    }//GEN-LAST:event_lblNhapHangMouseClicked

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
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuJDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblKhuyenMai;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblNhapHang;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblThoat;
    private javax.swing.JPanel pnContainers;
    private javax.swing.JPanel pnDoanhThu;
    private javax.swing.JPanel pnKhachHang;
    private javax.swing.JPanel pnKhuyenMai;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JPanel pnNhapHang;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnSideMenu;
    // End of variables declaration//GEN-END:variables

}
