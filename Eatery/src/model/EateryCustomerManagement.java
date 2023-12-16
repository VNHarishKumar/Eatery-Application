/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.util.logging.*;
import model.SQLConnection.SQLConnection;

/**
 *
 * @author ktkir
 */

public class EateryCustomerManagement {
  private List<Customer> customerDirectory;  
  
  public EateryCustomerManagement(){
      
      this.customerDirectory=new ArrayList<Customer>();
      populateCustomerFromDb();
  }

    public List<Customer> getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(ArrayList<Customer> customerDirectory) {
        this.customerDirectory = customerDirectory;
    }
    


  

    public void addNewCustomer(Customer cust)
    {
 
        customerDirectory.add(cust);

    }
    public void deleteCustomer(Customer cust)
    {
        customerDirectory.remove(cust);
    }
    
    public Customer loginCustomer(int stateID, String password){
        for(Customer cus:this.customerDirectory)
        {
            if(cus.getStateId()==stateID && cus.getPassword().equals(password))
            {
                return cus;
            }
        }
        return null;
    }
    public Boolean isCustomerIdUnique(int CustomerId)
    {
        for (Customer cus:this.customerDirectory)
        {
            if (cus.getStateId()==CustomerId)
            {
                return false;
            }
        }
        return true;
    }
    public void updateCustomer(int CustomerId,String name,String mobile,String email,String City,String password,String address)
    {
        for (Customer c: this.customerDirectory)
        {
            if (c.getStateId()==CustomerId)
            {
                c.setName(name);
                c.setAddress(address);
                c.setCity(City);
                c.setEmail(email);
                c.setMobile(mobile);
                c.setPassword(password);
                
            }
        }
    }
    
    public void populateCustomerFromDb()
    {
        try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from Customer";
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          this.customerDirectory=new ArrayList<Customer>();
          while(st.next())
             {
                int stateId=st.getInt("CustomerId");
                String email=st.getString("Email");
                String mobile=st.getString("Number");
                String address=st.getString("Address");
                String name=st.getString("CustomerName");
                String password=st.getString("Password");
                String city=st.getString("City");
                 Customer c=new Customer(stateId, name, mobile,email,city, password, address);
                this.customerDirectory.add(c);
                 
             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
     }}
    
    
    public void replaceCustomerDb(){
         try {
          //db replace old list  with new this.foodList;
          System.out.print("Inside replace customer db");
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from Customer";
          stmt.executeUpdate(TruncQuery);
          for (Customer c: this.customerDirectory)
          {
              String InsertQuery="Insert into Customer (CustomerId,CustomerName,Number,Password,Email,City,Address) values ('"+c.getStateId()+"','"+c.getName()+"','"+c.getMobile()+"','"+c.getPassword()+"','"+c.getEmail()+"','"+c.getCity()+"','"+c.getAddress()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          populateCustomerFromDb();
      } catch (SQLException ex) {
           Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);

      }}
    
    
    
    public Customer getCustomerById(int CustomerId)
    {
        for(Customer c:this.customerDirectory)
        {
            if (c.getStateId()==CustomerId)
            {
                return c;
            }
        }
        return null;
    }
    public void removeCustomer(Customer Cust)
    {
        
            
                this.customerDirectory.remove(Cust);
           
    }

    public Boolean cancelOrderByCustomer(int custId, Order order) {
      for(Customer cust : this.customerDirectory) {
         if(cust.getStateId() == custId) {
           return cust.checkIfOrderPlacedByCustomer(order);
         }
      }
      return false;
      
    }

    public List<Order> getPastOrdersOfCustomer(int custId) {
      for(Customer cust : this.customerDirectory){
          if(cust.getStateId() == custId){
              return cust.getPastOrders();
          }
      }
      return null;
           }

    void addOrderToPastOrdersOfCustomer(Order order) {
        int custId = order.getCustomerId();
        for(Customer cust: this.customerDirectory){
            if(cust.getStateId() == custId){
                cust.addToPastOrders(order);
            }
        }
            }
    
      void addOrderToActiveOrdersOfCustomer(Order order) {
        int custId = order.getCustomerId();
        for(Customer cust: this.customerDirectory){
            if(cust.getStateId() == custId){
                cust.addToActiveOrders(order);
            }
        }
            }
      
        void removeFromActiveOrdersOfCustomer(Order order) {
        int custId = order.getCustomerId();
        for(Customer cust: this.customerDirectory){
            if(cust.getStateId() == custId){
                cust.removeFromActiveOrdersOfCustomer(order);
            }
        }
            }

        public void populatePastAndActiveOrdersOfCustomer(int customerId,
            List<Order> pastOrdersOfCustomer, List<Order> activeOrdersOfCustomer) {
          
          
          for(Customer cust: this.customerDirectory){
            if(cust.getStateId() == customerId){
                cust.setActiveOrders(activeOrdersOfCustomer);
                cust.setPastOrders(pastOrdersOfCustomer);
                return;
            }
        }
          
        }

}
