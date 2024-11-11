/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.KhuyenMai;
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
 * @author hp
 */
public class KhuyenMaiDAO implements DAO<KhuyenMai, String> {

    @Override
    public List<KhuyenMai> getAllData() {
        List<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuyenMai";
        Object[] values = {};
        ResultSet rs = JDBC.executeQuery(sql, values);
        
        try {
            while (rs.next()) {
                list.add(new KhuyenMai(
                        rs.getString("MaKM"),
                        rs.getString("TenKM"),
                        rs.getFloat("MucKM"),
                        rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public KhuyenMai getDataById(String ma) {
        KhuyenMai km = null;
        String sql = "SELECT * FROM KhuyenMai WHERE MaKM = ?";
        Object[] values = {ma};
        
        ResultSet rs = JDBC.executeQuery(sql, values);
        
        try {
            while (rs.next()) {
                km = new KhuyenMai(
                        rs.getString("MaKM"),
                        rs.getString("TenKM"),
                        rs.getFloat("MucKM"),
                        rs.getDate("NgayBatDau"),
                        rs.getDate("NgayKetThuc")
                );
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenMaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return km;
    }

    @Override
    public void insertData(KhuyenMai o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(KhuyenMai o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
