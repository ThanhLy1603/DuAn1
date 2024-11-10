/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.SanPham;
import java.util.List;
/**
 *
 * @author anhth
 */
public class Test {
    public static void main(String[] args) {
       SanPhamDAO sp = new SanPhamDAO();
       List<SanPham> list = sp.getAllData();
        
       
       
        for(SanPham o : list) {
            System.out.println(o.getMaSP());
            System.out.println(o.getMaLoai());
            System.out.println(o.getTenSP());
            System.out.println(o.getSoLuong());
            System.out.println(o.getDonGia());
            System.out.println(o.isTrangThai());
            System.out.println("----------------------------");
        }
    }
}
