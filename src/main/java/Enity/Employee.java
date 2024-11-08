/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enity;

/**
 *
 * @author ADMIN
 */
public class Employee {
    private String idEmployee;
    private String password;
    private String name;
    private boolean gender;
    private boolean role;
    private double salary;
    private String phone;
    private String email;

    public Employee() {
    }

    public Employee(String idEmployee, String password, String name, boolean gender, boolean role, double salary, String phone, String email) {
        this.idEmployee = idEmployee;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
