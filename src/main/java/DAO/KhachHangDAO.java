/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Interfaces.DAO;
import Entity.KhachHang;
import Utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class KhachHangDAO implements DAO<KhachHang, String> {

    @Override
    public List<KhachHang> getAllData() {
        String sql = "SELECT * FROM KHACHHANG";
        Object[] values = {};
        ResultSet rs = JDBC.executeQuery(sql, values);
        List<KhachHang> list = new ArrayList<>();
        try {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MAKH"));
                kh.setTenKH(rs.getString("TENKH"));
                kh.setGioiTinh(rs.getBoolean("GIOITINH"));
                kh.setDiaChi(rs.getString("DIACHI"));
                kh.setSoDT(rs.getString("SDT"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public KhachHang getDataById(String ma) {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH=?";
        Object[] values = {ma};
        ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString("MAKH"));
                kh.setTenKH(rs.getString("TENKH"));
                kh.setGioiTinh(rs.getBoolean("GIOITINH"));
                kh.setDiaChi(rs.getString("DIACHI"));
                kh.setSoDT(rs.getString("SDT"));
                return kh;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insertData(KhachHang kh) {
        String sql="EXEC SP_InsertUpdateKhachHang ?, ?, ?,?,?";
        Object[] values={kh.getMaKH(),kh.getTenKH(),kh.isGioiTinh(),kh.getDiaChi(),kh.getSoDT()};
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void updateData(KhachHang kh) {
        String sql="EXEC SP_InsertUpdateKhachHang ?, ?, ?,?,?";
        Object[] values={kh.getTenKH(),kh.isGioiTinh(),kh.getDiaChi(),kh.getSoDT(),kh.getMaKH()};
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void deleteById(String ma) {
        String sql="Delete FROM KHACHHANG WHERE MAKH=?";
        Object[] values={ma};
        JDBC.executeUpdate(sql, values);
    }

}
