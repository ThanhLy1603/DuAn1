/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import java.util.Date;
import java.util.List;

/**
 *
 * @author PHONG
 */
public class testHoaDon {
    public static void main(String[] args) {
        HoaDonDAO hd= new HoaDonDAO();
        List<HoaDon> list = hd.getAllData();
        
        hd.updateData(new HoaDon(
                "HD12", 
                "PS43138", 
                "KH5", 
                new Date(2024-1900, 11-1, 11), 
                "Chuyển khoản", 
                true
        ));
    }
  
}