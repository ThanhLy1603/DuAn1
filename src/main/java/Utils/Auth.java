/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Enity.Employee;

/**
 *
 * @author ADMIN
 */
public class Auth {
    public static Employee employee = null;
    
    public static void clear() {
        Auth.employee = null;
    }

    public static boolean isLogin() {
        return Auth.employee != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && employee.isRole();
    }
}
