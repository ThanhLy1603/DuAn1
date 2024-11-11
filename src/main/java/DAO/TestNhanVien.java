/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.NhanVien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class TestNhanVien {
    public static void main(String[] args) {
        NhanVienDao nv= new NhanVienDao();
        List<NhanVien> list= nv.getAllData();
        
        list.forEach(nv1->{
            System.out.println(nv1.getMaNV());
            }
        );
        
        NhanVien nv2 = nv.getDataById("PS43194");
        
        System.out.println(nv2.getTenNV());
        
    }
    
    
}
