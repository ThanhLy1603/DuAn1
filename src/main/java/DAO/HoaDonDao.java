/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import Interfaces.DAO;
import Utils.JDBC;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHONG
 */
public class HoaDonDao implements DAO<HoaDon,String>{

    @Override
    public List<HoaDon> getAllData() {
            String sql = "SELECT * FROM HoaDon";
        Object[] values = {};
        ResultSet rs = JDBC.executeQuery(sql, values);
        List<HoaDon> list = new ArrayList<>();
        try {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setHinhThuc(rs.getString("HinhThuc"));
                hd.setTrangThai(rs.getInt("TrangThai")==1);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HoaDon getDataById(String ma) {
          String sql = "SELECT * FROM HoaDon WHERE MaHD=?";
        Object[] values = {ma};
        ResultSet rs = JDBC.executeQuery(sql, values);
        try {
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaNV(rs.getString("MaNV"));
                hd.setMaKH(rs.getString("MaKH"));
                hd.setNgayLap(rs.getDate("NgayLap"));
                hd.setHinhThuc(rs.getString("HinhThuc"));
                hd.setTrangThai(rs.getInt("TrangThai")==1);
                return hd;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void insertData(HoaDon hd) {
        String sql = "EXEC SP_InsertUpdateHoaDon ?,?,?,?,?,?";
        Object[] values = {
            hd.getMaHD(),
            hd.getMaNV(),
            hd.getMaKH(),
            hd.getNgayLap(),
            hd.getHinhThuc(),
            hd.isTrangThai()?1:0
        };
        
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void updateData(HoaDon hd) {
        String sql="EXEC SP_InsertUpdateHoaDon ?,?,?,?,?,?";
        Object[] values = {
            hd.getMaHD(),
            hd.getMaNV(),
            hd.getMaKH(),
            hd.getNgayLap(),
            hd.getHinhThuc(),
            hd.isTrangThai()?1:0
        };
      
        JDBC.executeUpdate(sql, values);
    }

    @Override
    public void deleteById(String ma) {
        String sql="Delete FROM HoaDon WHERE MaHD=?";
        Object[] values={ma};
        
        JDBC.executeUpdate(sql, values);
    }
}

