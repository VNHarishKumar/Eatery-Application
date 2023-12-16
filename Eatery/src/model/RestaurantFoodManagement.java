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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SQLConnection.SQLConnection;

/**
 *
 * @author ktkir
 */


public class RestaurantFoodManagement {
  private ArrayList<Food> foodList;

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }
  
public RestaurantFoodManagement(){
 this.foodList=new ArrayList<Food>();
 populateFoodList();
}  

public void addFood(Food f)
{
   this.foodList.add(f);
// replaceFoodList();
}

public void replaceFoodList()
{
      try {
          //db replace old list  with new this.foodList;
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from Food";
          stmt.executeUpdate(TruncQuery);
           
          for (Food f: this.foodList)
          {
              System.out.println(f.getName());
              String InsertQuery="Insert into Food (FoodId,FoodName,Price,RestaurantId) values ('"+f.getFoodId()+"','"+f.getName()+"','"+f.getPrice()+"','"+f.getRestaurantId()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          populateFoodList();
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
      }
     
}

public void populateFoodList(){
      try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from Food";
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          this.foodList=new ArrayList<Food>();
          while(st.next())
             {
                 int foodId=(st.getInt("FoodId"));
                 int restaurantId=(st.getInt("RestaurantId"));
                 String Name=(st.getString("FoodName"));
                 int Price=st.getInt("Price");
                 Food f= new Food(Name,Price,restaurantId,foodId);
                foodList.add(f);
                 
             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
      }}

public void removeFood(int foodId)
{
          int index = 0;
    for (Food r : this.foodList) {

      if (r.getFoodId()== foodId) {
        this.foodList.remove(index);
        return;
      }
      index++;
    } 
//    replaceFoodList();
}

public ArrayList<Food> getFoodByRestaurant(int restaurantId){
    ArrayList<Food> foodByRestaurant = new ArrayList<>();
    
    for(Food f:this.foodList)
    {
        if(f.getRestaurantId()==restaurantId)
        {
            foodByRestaurant.add(f);
        }
    }
    
    return foodByRestaurant;
}

public Food getFoodById(int foodId)
{
   for(Food f : this.foodList)
   {
       if(f.getFoodId()==foodId)
       {
           return f;
       }
   }
   return null;
}

public void updateFood(int foodId, String foodName, int price)
{
    for(Food f:this.foodList)
    {
        if(f.getFoodId()==foodId)
        {
            f.setName(foodName);
            f.setPrice(price);
        }
    }
}

}
