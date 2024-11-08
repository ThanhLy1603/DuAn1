/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UI.Component;

/**
 *
 * @author ADMIN
 */
public interface Initialize<Entity> {
    public void init();
    public void fillToTable();
    public void setForm(Entity o);
    public void getForm(int index);
    public void generateCbx();
}
