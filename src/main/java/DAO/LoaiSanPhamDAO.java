/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.LoaiSanPham;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author anhth
 */
public class LoaiSanPhamDAO implements DAO<LoaiSanPham, String>{

    @Override
    public List<LoaiSanPham> getAllData() {
       List<LoaiSanPham> list = new ArrayList<>();
       
       String sql = "Select * from LoaiSanPham";
       Object[] values = {};
       
       ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                list.add(new LoaiSanPham(
                        rs.getString("MaLoai"),
                        rs.getString("TenLoai")
                )); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public LoaiSanPham getDataById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertData(LoaiSanPham o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(LoaiSanPham o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
