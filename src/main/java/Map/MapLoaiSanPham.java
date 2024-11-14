/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;

import DAO.LoaiSanPhamDAO;
import Entity.LoaiSanPham;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author ADMIN
 */
public class MapLoaiSanPham {
    public static Map<String, String> getDataMap() {
        Map<String, String> map = new TreeMap<>();
        LoaiSanPhamDAO dao = new LoaiSanPhamDAO();
        List<LoaiSanPham> list = dao.getAllData();
        
        for (LoaiSanPham o : list) {
            map.put(
                    o.getMaLoai(), 
                    o.getTenLoai()
            );
        }
        
        return map;
    }
    
    public static String getTenByMa(String ma) {
        String tenLoai = null;
        Map<String, String> map = getDataMap();
        
        for (Map.Entry<String, String> o : map.entrySet()){
            if (o.getKey().equalsIgnoreCase(ma)){
                tenLoai = o.getValue();
            }
        }
        
        return tenLoai;
    }
}
