/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Map;
import java.util.Map;
/**
 *
 * @author ADMIN
 */
public class Test {
    public static void main(String[] args) {
        MapLoaiSanPham mapLSP = new MapLoaiSanPham();
        Map<String, String> list = mapLSP.getMapData();
        
//        for (Map.Entry<String, String> o : list.entrySet()) {
//            System.out.println(o.getKey());
//            System.out.println(o.getValue());
//            System.out.println("------------------------------");
//        }

        System.out.println(mapLSP.getIDByValue("Đầm"));
        System.out.println(mapLSP.getValueByID("DRE"));
    }
}
