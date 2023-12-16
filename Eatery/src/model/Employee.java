/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ktkir
 */
public class Employee {
    private String name;
    private int employeeId;
    private int restaurantId;
    private String role;
    
    public Employee( String name,int employeeId,int restaurantId,String role)
    {
        this.name=name;
        this.employeeId=employeeId;
        this.restaurantId=restaurantId;
        this.role=role;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRole() {
        return role;
    }
   
    
    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
