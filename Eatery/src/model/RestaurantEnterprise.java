/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.*;
import model.SQLConnection.SQLConnection;

/**
 *
 * @author ktkir
 */
public class RestaurantEnterprise {
       private ArrayList<Restaurant> restaurantDirectory;  
       private RestaurantFoodManagement foodDirect;
       private RestaurantEmployeeManagement employeeList;
       public RestaurantEnterprise(){
       this.restaurantDirectory=new ArrayList<Restaurant>();
       this.foodDirect=new RestaurantFoodManagement();
       this.employeeList=new RestaurantEmployeeManagement();
       populateDishesInRestaurant();
       populateRestaurantDb();
       populateEmployeeInRestaurant();
  }
       
       public void populateRestaurantDb()
    {
        try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from Restaurant";
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          System.out.print("Inside populate Restaurant");
          this.restaurantDirectory=new ArrayList<Restaurant>();
          while(st.next())
             {
                 
                int restarauntId=st.getInt("RestaurantId");
                String restarauntName=st.getString("RestaurantName");
                String city=st.getString("City");
                int rating=st.getInt("Rating");
                 Restaurant res=new Restaurant(restarauntId,restarauntName,city,rating);
                 this.restaurantDirectory.add(res);
             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
       
       public void replaceRestaurantDb()
    {
         try {
          //db replace old list  with new this.foodList;
          System.out.print("Inside replace restaurant db");
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from Restaurant";
          //this.restaurantDirectory=new ArrayList<Restaurant>();
          stmt.executeUpdate(TruncQuery);
          for (Restaurant res: this.restaurantDirectory)
          {
              String InsertQuery="Insert into Restaurant (RestaurantId,RestaurantName,City,Rating) values ('"+res.getRestaurantId()+"','"+res.getRestaurantName()+"','"+res.getCity()+"','"+res.getRating()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          
          
          populateRestaurantDb();
         populateEmployeeInRestaurant();
         populateDishesInRestaurant();
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);}}

  


   
   
    public ArrayList<Restaurant> getRestaurantDirectory() {
        return restaurantDirectory;
    }

    public void setRestaurantDirectory(ArrayList<Restaurant> restaurantDirectory) {
        this.restaurantDirectory = restaurantDirectory;
    }
    
    public void addRestaurant(Restaurant rest)
    {
        this.restaurantDirectory.add(rest);
    }
    
    public void removeRestaurant(int restId)
    {
           int index = 0;
    for (Restaurant r : this.restaurantDirectory) {

      if (r.getRestaurantId()== restId) {
        this.restaurantDirectory.remove(index);
        return;
      }
      index++;
    } 
    }
    
    
    void updateRestaurant(int restaurantId,String restaurantName, String cityName) {
    for (Restaurant r : this.restaurantDirectory) {

      if (r.getRestaurantId()== restaurantId) {
          r.setRestaurantName(restaurantName);
          r.setCity(cityName);
        return;
      }
    }

  }
    
    //food starts
    
    void addFoodToRestaurant(Food f, int restaurantId){
        this.foodDirect.addFood(f);
            
        for(Restaurant res: this.restaurantDirectory)
        {
            if(res.getRestaurantId()==restaurantId)
            {
                res.addToDishes(f);
            }
        } 
    }
    
    public ArrayList<Food> getFoodList()
    {
        return this.foodDirect.getFoodList();
    }
    
       public void populateDishesInRestaurant(){
       
       if(this.foodDirect.getFoodList().size() > 0 )
       {
         
        for(Restaurant res: this.restaurantDirectory)
        {
         res.setDishes(this.foodDirect.getFoodByRestaurant(res.getRestaurantId()));  
        } 
       }
   }
       
       public Boolean isFoodIdUnique(int foodId)
       {
             for(Food food:this.foodDirect.getFoodList()) {
          if(food.getFoodId()== foodId) {
            return false;
          }
        }
        return true;   
       }
       
       public void removeFood(int restId, int foodId)
       {
           this.foodDirect.removeFood(foodId);
           removeFoodFromRestaurant(restId,foodId);
       }
       
           public void removeFoodFromRestaurant(int restaurantId,int foodId)
    {
        for(Restaurant res :this.restaurantDirectory)
        {
            if(res.getRestaurantId()==restaurantId)
            {
               res.removeFromDishes(foodId);
            }
        }
    }
           
           public Boolean checkIfRestaurantAcceptsOrder(int restId)
           {
               for(Restaurant res : this.restaurantDirectory)
               {
                   if(res.getRestaurantId()==restId)
                   {
                       if(res.getAcceptingOrders()==true)
                       {
                           return true;
                       }
                   }
               }
               return false;
           }
           
           public Food getFoodById(int foodId){
               return this.foodDirect.getFoodById(foodId);
           }
           
           public ArrayList<Food> getFoodByRestaurant(int restId)  
           {
              return this.foodDirect.getFoodByRestaurant(restId);
           }
       
           public void updateFood(int restaurantId, int foodId, String foodName, int price)
           {
               this.foodDirect.updateFood( foodId,  foodName,  price);
                 updateFoodinRestaurant( restaurantId,  foodId,  foodName,  price);
           }
       
           public void updateFoodinRestaurant(int restaurantId, int foodId, String foodName, int price)
           {
               for(Restaurant res: this.restaurantDirectory)
               {
                   if(res.getRestaurantId()==restaurantId)
                   {
                       res.updateDish(foodId,foodName,price);
                   }
               }
           }
       
       //food ends
       
         //employee starts 
         public void populateEmployeeInRestaurant(){
       
       if(this.employeeList.getEmployeeList().size() > 0 )
       {
         
        for(Restaurant res: this.restaurantDirectory)
        {
         res.setEmployeeList(this.employeeList.getEmployeeListByRestaurant(res.getRestaurantId()));  
        } 
       }
   }
     
     
     public void addEmployeeToRestaurant(Employee emp){
         this.employeeList.addEmployee(emp);
      for(Restaurant res :this.restaurantDirectory)
      {
          if(res.getRestaurantId()==emp.getRestaurantId())
          {
              res.addToEmployeeList(emp);
          }
      }
     }
     
     
        public void removeEmployeeFromRestaurant(int restaurantId,int employeeId){
      for(Restaurant res :this.restaurantDirectory)
      {
          if(res.getRestaurantId()==restaurantId)
          {
              res.removeFromEmployeeList(employeeId);
          }
      }
     }
        
        public Restaurant loginEmployee(String role,int employeeId, int restaurantId)
        {
            if(role=="Employee Manager")
            {
                for(Restaurant res: this.restaurantDirectory)
                {
                    if(res.getRestaurantId()==restaurantId)
                    {
                        return res;
                    }
                }
            }
           
             
           
            else
            {
            for(Restaurant res : this.restaurantDirectory)
            {
                if(res.getRestaurantId()==restaurantId)
                {
                    for(Employee emp : res.getEmployeeList())
                    {
                        if(emp.getEmployeeId()==employeeId && emp.getRole().equals(role) )
                        {
                            return res;
                        }
                    }
                }
            }
            }
            return null;
        }
        
        public List<Employee> getEmployeeByRestaurant(int restaurantId)
        {
           
            for(Restaurant res :this.restaurantDirectory)
            {
                if(res.getRestaurantId()==restaurantId)
                {
                   return res.getEmployeeList();
                }
            }
            
            
            
            return null;
        }
     
        public Employee getEmployeeById(int employeeId){
            return this.employeeList.getEmployeeById(employeeId);
        }
        
               
           public void updateEmployee(int restaurantId, int employeeId, String employeeName, String role)
           {
               this.employeeList.updateEmployee( employeeId,  employeeName,  role);
                 updateEmployeeInRestaurant( restaurantId, employeeId,  employeeName,  role);
           }
       
           public void updateEmployeeInRestaurant(int restaurantId, int employeeId, String employeeName, String role)
           {
               for(Restaurant res: this.restaurantDirectory)
               {
                   if(res.getRestaurantId()==restaurantId)
                   {
                       res.updateEmployee(employeeId,employeeName,role);
                   }
               }
           }
     //employee ends 
    
    
    public void assingOrdertoRestaurant(Order order, int restaurantId){
        for(Restaurant res : this.restaurantDirectory)
        {
            if(res.getRestaurantId()==restaurantId)
            {
                res.addToCurrentOrders(order);
            }
        }
        
    }
    
    public void removeOrderFromRestaurant(int orderId, int restaurantId)
    {
        for(Restaurant res :this.restaurantDirectory)
        {
            if(res.getRestaurantId()==restaurantId)
            {
               res.removeFromCurrentOrders(orderId);
            }
        }
    }
    
    public Boolean isRestaurantIdUnique(int restId){
      for(Restaurant res:this.restaurantDirectory) {
          if(res.getRestaurantId()== restId) {
            return false;
          }
        }
        return true;   
    }
    
    public Restaurant getRestaurantById (int restaurantId)
    {
          for(Restaurant res:this.restaurantDirectory) {
          if(res.getRestaurantId()== restaurantId) {
            return res;
          }
        }
          return null;
    }
    
    public Boolean isEmployeeIdUnique(int employeeId)
    {
      return this.employeeList.isEmployeeIdUnique(employeeId);
    }






    public void setOrderStatusCancelled(int restaurantId, int orderId) {
      for(Restaurant res: this.restaurantDirectory) {
        if(res.getRestaurantId() == restaurantId) {
          res.removeFromCurrentOrders(orderId);
        }
      }
    }






    public void cancelOrderByRestaurant(int restaurantId, int orderId) {
    for(Restaurant res: this.restaurantDirectory) {
      if(res.getRestaurantId() == restaurantId) {
      res.removeFromCurrentOrders(orderId);
      return;
      }
      
    }
      
    }
    
    public void replaceEmployeeDb(){
        this.employeeList.replaceEmployeeDb();
    }
    
    public void replacefoodDb(){
        this.foodDirect.replaceFoodList();  
    }

    public void populateOrdersInRestaurant(int restaurantId, List<Order> ordersOfRestaurant) {
      for(Restaurant res: this.restaurantDirectory) {
        if(res.getRestaurantId() == restaurantId) {
        res.setCurrentOrders(ordersOfRestaurant);
        return;
        }
        
      }
      
    }

     
  
    
   
  
}
