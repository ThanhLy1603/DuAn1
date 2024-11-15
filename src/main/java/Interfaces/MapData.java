/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import java.util.Map;
/**
 *
 * @author ADMIN 
 */
public interface MapData {
    public Map<String, String> getMapData();
    public String getValueByID(String id);
    public String getIDByValue(String value);
}
