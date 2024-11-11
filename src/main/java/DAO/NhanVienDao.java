/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
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
 * @author PHONG
 */
public class NhanVienDao implements DAO<NhanVien, String> {

    @Override
    public List<NhanVien> getAllData() {
        String sql = "SELECT * FROM NhanVien";
        Object[] values = {};
        ResultSet rs = JDBC.executeQuery(sql, values);
        List<NhanVien> list = new ArrayList<>();
        try {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setEmail(rs.getString("Email"));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public NhanVien getDataById(String ma) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        Object[] values = {ma};
        ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setChucVu(rs.getBoolean("ChucVu"));
                nv.setLuong(rs.getDouble("Luong"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setEmail(rs.getString("Email"));
                return nv;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insertData(NhanVien nv) {
        String sql = "EXEC SP_InsertUpdateNhanVien ?, ?, ?,?,?,?,?,?";
        Object[] values = {
            nv.getMaNV(),
            nv.getTenNV(),
            nv.getMatKhau(),
            nv.isGioiTinh(),
            nv.isChucVu(),
            nv.getLuong(),
            nv.getSoDT(),
            nv.getEmail()
        };
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void updateData(NhanVien nv) {
         String sql="EXEC SP_InsertUpdateNhanVien ?, ?, ?,?,?,?,?,?";
      Object[] values = {
            nv.getMaNV(),
            nv.getTenNV(),
            nv.getMatKhau(),
            nv.isGioiTinh(),
            nv.isChucVu(),
            nv.getLuong(),
            nv.getSoDT(),
            nv.getEmail()
        };
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void deleteById(String ma) {
         String sql="Delete FROM NhanVien WHERE MaNV=?";
        Object[] values={ma};
        JDBC.executeUpdate(sql, values);
    }

}
