/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Entity.SanPham;
import java.util.List;
import Entity.KhuyenMai;
import java.util.Date;
import Entity.LoaiSanPham;
import DAO.SanPhamDAO;
/**
 *
 * @author anhth
 */
public class Test {
    public static void main(String[] args) {
        LoaiSanPhamDAO dao = new LoaiSanPhamDAO();
        List<LoaiSanPham> list = dao.getAllData();
       
        SanPhamDAO daoSP = new SanPhamDAO();
        List<SanPham> listSP = daoSP.getAllData();
        
        for (SanPham o : listSP) {
            System.out.println(o.toString());
        }
    } 
}

