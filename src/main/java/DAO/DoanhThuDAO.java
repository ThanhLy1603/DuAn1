/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.DoanhThu;
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
public class DoanhThuDAO implements DAO<DoanhThu, String>{

    @Override
    public List<DoanhThu> getAllData() {
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT * FROM V_DoanhThu";
        Object[] values = {};
        
        ResultSet rs = JDBC.executeQuery(sql, values);
        
        try {
            while (rs.next()) {
                list.add(new DoanhThu(
                        rs.getString("MaSanPham"), 
                        rs.getString("TenSanPham"), 
                        rs.getShort("SoLuong"),
                        rs.getShort("Gia"),
                        rs.getShort("TongTien"), 
                        rs.getDate("NgayLap")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public DoanhThu getDataById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertData(DoanhThu o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(DoanhThu o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
