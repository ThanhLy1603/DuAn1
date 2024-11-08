/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.LoaiSanPham;
import java.util.List;
/**
 *
 * @author anhth
 */
public class Test {
    public static void main(String[] args) {
        LoaiSanPhamDAO DAO = new LoaiSanPhamDAO();
        LoaiSanPham loai = DAO.getDataById("DRE");
        List<LoaiSanPham> list = DAO.getAllData();
        
        System.out.println(loai.getMaLoai());
        System.out.println(loai.getTenLoai());
        
//        for(LoaiSanPham o : list) {
//            System.out.println(o.getMaLoai());
//            System.out.println(o.getTenLoai());
//        }
    }
}
