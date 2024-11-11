/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import java.util.List;

/**
 *
 * @author PHONG
 */
public class testHoaDon {
    public static void main(String[] args) {
        HoaDonDao hd= new HoaDonDao();
        List<HoaDon> list = hd.getAllData();
        
    }
  
}
