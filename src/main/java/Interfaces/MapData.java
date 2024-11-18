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
public interface MapData<Type1, Type2> {
    public Map<Type1, Type2> getMapData();
    public Type2 getValueByID(Type1 id);
    public Type1 getIDByValue(Type2 value);
}
