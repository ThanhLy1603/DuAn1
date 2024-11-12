/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.SanPham;
import java.util.List;
import Entity.KhuyenMai;
import java.util.Date;
/**
 *
 * @author anhth
 */
public class Test {
    public static void main(String[] args) {
       SanPhamDAO dao = new SanPhamDAO();
       List<SanPham> list = dao.getAllData();
       
//       for (SanPham o : list) {
//           System.out.println(o.toString());
//       }

//        SanPham o = dao.getDataById("SP12");
//        
//        System.out.println(o.getMaSP());
//           System.out.println(o.getTenSP());
//           System.out.println(o.getMaLoai());
//           System.out.println(o.getSoLuong());
//           System.out.println(o.getDonGia());
//           System.out.println(o.getMauSac());
//           System.out.println(o.getChatLieu());
//           System.out.println(o.getSize());
//           System.out.println(o.getHinhAnh());
//           System.out.println(o.isTrangThai());

        list.forEach((SanPham) -> {
            System.out.println(SanPham.toString());
        });
        
        dao.insertData(new SanPham(
                "SP11", 
                "JEAN", 
                "Quần Jeans đi du lịch",
                350000, 
                150, 
                "Xanh",
                "Jean", 
                "XXL", 
                "", 
                false
        ));
        
    } 
}

