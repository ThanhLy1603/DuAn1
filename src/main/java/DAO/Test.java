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
       KhuyenMaiDAO dao = new KhuyenMaiDAO();
       
       List<KhuyenMai> list = dao.getAllData();
           
       dao.deleteById("KM6");
    } 
}

