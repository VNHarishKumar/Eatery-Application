/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.SQLConnection.SQLConnection;

/**
 *
 * @author ktkir
 */
public class DeliveryPersonManagement {
private ArrayList<DeliveryPerson> deliveryPersonDirectory;  
  
  public DeliveryPersonManagement(){
      
      this.deliveryPersonDirectory=new ArrayList<DeliveryPerson>();
      populateDeliveryPersonFromDb();
  }

    public ArrayList<DeliveryPerson> getDeliveryPersonDirectory() {
        return deliveryPersonDirectory;
    }

    public void setDeliveryPersonDirectory(ArrayList<DeliveryPerson> deliveryPersonDirectory) {
        this.deliveryPersonDirectory = deliveryPersonDirectory;
    }
    
    //get deliveryPerson from db
    public void populateDeliveryPersonFromDb()
    {
      try {
      Connection con=SQLConnection.dbconnector();
      String sql="select * from DeliveryPerson";
      PreparedStatement ps=con.prepareStatement(sql);
      ResultSet st=ps.executeQuery();
      this.deliveryPersonDirectory=new ArrayList<DeliveryPerson>();
      while(st.next())
         {
            int stateId=st.getInt("DeliveryPersonId");
            String email=st.getString("Email");
            String mobile=st.getString("Mobile");
            String address=st.getString("Address");
            String name=st.getString("Name");
            String password=st.getString("Password");
            String city=st.getString("City");
            DeliveryPerson d=new DeliveryPerson(stateId,name,mobile,email,city,password,address);
            this.deliveryPersonDirectory.add(d);
            
         }
  } catch (SQLException ex) {
      Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
 }
        
    }
    
    public void replaceDeliveryPersonDb(){
      try {
        //db replace old list  with new this.foodList;
        System.out.print("Inside replace customer db");
        Connection con=SQLConnection.dbconnector();
        Statement stmt=con.createStatement();
        String TruncQuery="delete from DeliveryPerson";
        stmt.executeUpdate(TruncQuery);
        for (DeliveryPerson d: this.deliveryPersonDirectory)
        {
            String InsertQuery="Insert into DeliveryPerson (DeliveryPersonId,Name,Mobile,Password,Email,City,Address) values ('"+d.getStateId()+"','"+d.getName()+"','"+d.getMobile()+"','"+d.getPassword()+"','"+d.getEmail()+"','"+d.getCity()+"','"+d.getAddress()+"')";
            stmt.executeUpdate(InsertQuery);
        }
        stmt.close();
        con.close();
        
       populateDeliveryPersonFromDb();
    } catch (SQLException ex) {
         Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);

    }
  }
  

    public void addNewDeliveryPerson(DeliveryPerson delPerson)
    {
 
        deliveryPersonDirectory.add(delPerson);

    }
    public void deleteDeliveryPerson(DeliveryPerson delPerson)
    {
        deliveryPersonDirectory.remove(delPerson);
    }
    
    public DeliveryPerson loginDeliveryPerson(int stateID, String password){
        for(DeliveryPerson delPerson:this.deliveryPersonDirectory)
        {
            if(delPerson.getStateId()==stateID && delPerson.getPassword().equals(password))
            {
                return delPerson;
            }
        }
        return null;
    }

    public List<DeliveryPerson> getDeliveryPersonThatAreFree() {
      List<DeliveryPerson> freeDeliveryPersons =  new ArrayList<DeliveryPerson>();
      for(DeliveryPerson dp:this.deliveryPersonDirectory) {
        if(dp.isAvailable()) {
          freeDeliveryPersons.add(dp);
        }
      }
      return freeDeliveryPersons;
    }

    public boolean assignOrderToDeliveryPerson(Order order, int deliveryPersonId) {
      for(DeliveryPerson del:this.deliveryPersonDirectory) {
        if(del.getStateId() == deliveryPersonId) {
          return del.setActiveOrder(order);
        }
      }
      return false;
      
    }

    public List<DeliveryPerson> getDeliveryBoyThatAreFreeInSameCity(String city) {
      List<DeliveryPerson> freeDeliveryPersons =  new ArrayList<DeliveryPerson>();
      for(DeliveryPerson dp:this.deliveryPersonDirectory) {
        if(dp.isAvailable() && dp.getCity().equals(city)) {
          freeDeliveryPersons.add(dp);
        }
      }
      return freeDeliveryPersons;
    }

    public void deliveryBoyCompleteOrder(int deliveryBoyId) {
      
     for(DeliveryPerson dp : this.deliveryPersonDirectory) {
       if(deliveryBoyId == dp.getStateId()) {
         dp.completeActiveOrder();
         return;
       }
     }
      
    }
    
     public DeliveryPerson getDeliveryPersonById (int deliverBoyId)
    {
       
          for(DeliveryPerson res:this.deliveryPersonDirectory) {
          if(res.getStateId()== deliverBoyId) {
            return res;
          }
        }
          return null;
    }
    
    public void deleteDeliveryPerson(int deliveryBoyId)
    {
        int index=0;
        for (DeliveryPerson res:this.deliveryPersonDirectory) {

      if (res.getStateId()== deliveryBoyId) {
        this.deliveryPersonDirectory.remove(index);
        return;
      }
      index++;
    }     
        
    }
    
    public void updateDeliveryBoy(int deliveryBoyId,String name,String city,String phno,String email,String address)
    {
         for (DeliveryPerson res:this.deliveryPersonDirectory) {
             
             if(res.getStateId()== deliveryBoyId)
             {
                 res.setName(name);
                 res.setCity(city);
                 res.setMobile(phno);
                 res.setEmail(email);
                 res.setAddress(address);
                 
             }
         }
    }

    public void sendQueryToCustomer(int deliveryPersonId, String query) {
       
      for (DeliveryPerson dp:this.deliveryPersonDirectory) {
        
        if(dp.getStateId()== deliveryPersonId)
        {
            dp.sendQueryToCustomer(query);
        }
    }
    }
    public Boolean isDeliveryBoyIdUnique(int deliveryBoyId)
    {
        for(DeliveryPerson del : this.deliveryPersonDirectory)
        {
            if(del.getStateId()==deliveryBoyId)
            {
                return false;
            }
        }
        return true;
    }
    
}
