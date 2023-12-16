/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.sql.*;
import java.util.logging.*;
import model.SQLConnection.SQLConnection;
/**
 *
 * @author ktkir
 */
public class RestaurantEmployeeManagement {
    private ArrayList<Employee> employeeList;

public RestaurantEmployeeManagement(){
 this.employeeList=new ArrayList<Employee>();
 populateEmployeeListDb();
}  

public void populateEmployeeListDb(){
    try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from Employee";
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          System.out.print("Inside populate Employee");
          this.employeeList=new ArrayList<Employee>();
          while(st.next())
             {
                String name=st.getString("Name");
                int employeeId=st.getInt("EmployeeId");
                int restarauntId=st.getInt("RestaurantId");
                String role=st.getString("Role");
                Employee e=new Employee(name,employeeId,restarauntId,role);
                this.employeeList.add(e);
             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null,ex);}
}


public void replaceEmployeeDb()
{
    try {
          //db replace old list  with new this.foodList;
          System.out.print("Inside replace employee db");
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from Employee";
          stmt.executeUpdate(TruncQuery);
          

          for (Employee e:this.employeeList)
          {
              String InsertQuery="Insert into Employee (Name,EmployeeId,RestaurantId,Role) values ('"+e.getName()+"','"+e.getEmployeeId()+"','"+e.getRestaurantId()+"','"+e.getRole()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          
          
          populateEmployeeListDb();
          
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null,ex);}
}

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }


    
public void removeEmployee(int employeeId)
{
      int index = 0;
    for(Employee r : this.employeeList) {

      if (r.getEmployeeId()== employeeId) {
        this.employeeList.remove(index);
        return;
      }
      index++;
    }
}    


public ArrayList<Employee> getEmployeeListByRestaurant(int restaurantId){
    ArrayList<Employee> employeeListByRestaurant = new ArrayList<>();
    
    for(Employee f:this.employeeList)
    {
        if(f.getRestaurantId()==restaurantId)
        {
            employeeListByRestaurant.add(f);
        }
    }
    
    return employeeListByRestaurant;
}

public Boolean isEmployeeIdUnique(int employeeId){
      for(Employee emp : this.employeeList)
        {
            if(emp.getEmployeeId()==employeeId)
            {
                return false;
            }
        }
        return true;
}

public Employee getEmployeeById(int employeeById){
    for(Employee emp : this.employeeList)
    {
       if(emp.getEmployeeId()==employeeById)
            {
                return emp;
            }
        }
        return null;
}

public void updateEmployee(int employeeId,  String employeeName,  String role)
{
    for(Employee emp : this.employeeList)
    {
        if(emp.getEmployeeId()==employeeId)
        {
            emp.setName(employeeName);
            emp.setRole(role);
        }
    }
}

public void addEmployee(Employee emp)
{
    this.employeeList.add(emp);
}

    
}
