/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import Entity.NhapHang;

/**
 *
 * @author ADMIN
 */
public class TestNhapHang {
    public static void main(String[] args) {
        NhapHangDAO dao = new NhapHangDAO();
        List<NhapHang> list = dao.getAllData();
        
        for (NhapHang o : list) {
            System.out.println(o.toString());
        }
        System.out.println("--------------------");
        
        NhapHang nh = dao.getDataById(2);
        
        System.out.println(nh.toString());
    }
}
