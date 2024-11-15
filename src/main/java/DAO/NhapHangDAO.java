/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.NhapHang;
import Interfaces.DAO;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class NhapHangDAO implements DAO<NhapHang, Integer>{

    @Override
    public List<NhapHang> getAllData() {
        List<NhapHang> list = new ArrayList<>();
        String sql = "SELECT * FROM NhapHang";
        Object[] values = {};
        
        ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                list.add(new NhapHang(
                        rs.getInt("MaNhap"), 
                        rs.getString("MaSanPham"), 
                        rs.getDate("NgayNhap"), 
                        rs.getInt("SoLuong")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public NhapHang getDataById(Integer ma) {
        NhapHang nh = null;
        String sql = "SELECT * FROM NhapHang WHERE MaNhap = ?";
        Object[] values = {ma};
        
        ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                nh = new NhapHang(
                    rs.getInt("MaNhap"), 
                    rs.getString("MaSanPham"), 
                    rs.getDate("NgayNhap"), 
                    rs.getInt("SoLuong")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nh;
    }

    @Override
    public void insertData(NhapHang o) {
        String sql = "EXEC SP_InsertUpdateNhapHang ?,?,?,?";
        Object[] values = {
            o.getMaNhap(),
            o.getMaSP(),
            o.getNgayNhap(),
            o.getSoLuong()
        };
        
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void updateData(NhapHang o) {
        String sql = "EXEC SP_InsertUpdateNhapHang ?,?,?,?";
        Object[] values = {
            o.getMaNhap(),
            o.getMaSP(),
            o.getNgayNhap(),
            o.getSoLuong()
        };
        
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void deleteById(Integer ma) {
        throw new UnsupportedOperationException("Not Available");
    }

    @Override
    public List<NhapHang> getDataByValue(Integer value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
