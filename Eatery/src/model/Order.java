/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SQLConnection.SQLConnection;

/**
 *
 * @author ktkir
 */
public class Order {
    private int orderId;
    private int restaurantId;
    private int customerId;
    private int deliveryPersonId;
    private OrderStatus status;
    private String devlieryAddress;
    private String city;
    private String deliveryInstructions;
    private int totalPrice;
      private List<Food> orderedFoodList;
      private String queryFromDeliveryBoy;
      private boolean isGroceryOrder;
      private List<Grocery> orderedGroceryList;


      
      public Order(int orderId, int customerId, int restaurantId, OrderStatus status, String devlieryAddress, String deliveryInstructions,int totalPrice,ArrayList<Food>foodList, String city, 
        boolean  isGroceryOrder, List<Grocery> orderedGroceryList,int deliveryPersonId)
      {
          this.orderId=orderId;
          this.customerId=customerId;
          this.status=status;
          this.devlieryAddress=devlieryAddress;
          this.deliveryInstructions=deliveryInstructions;
          this.totalPrice=totalPrice;
          this.orderedFoodList=foodList;
          this.restaurantId=restaurantId;
          this.city=city;
          this.queryFromDeliveryBoy = "";
          this.isGroceryOrder = isGroceryOrder;
          this.orderedGroceryList = orderedGroceryList;
          this.deliveryPersonId=-1;
      }
      public Order(int orderId, int customerId, int restaurantId, OrderStatus status, String devlieryAddress, String deliveryInstructions,int totalPrice, String city, boolean  isGroceryOrder,int deliveryPersonId,String queryFromDeliveryBoy)
      {
          this.orderId=orderId;
          this.customerId=customerId;
          this.status=status;
          this.devlieryAddress=devlieryAddress;
          this.deliveryInstructions=deliveryInstructions;
          this.totalPrice=totalPrice;
          //this.orderedFoodList=foodList;
          this.restaurantId=restaurantId;
          this.city=city;
          this.queryFromDeliveryBoy = queryFromDeliveryBoy;
          this.isGroceryOrder = isGroceryOrder;
          this.orderedGroceryList = new ArrayList<>();
          this.deliveryPersonId=deliveryPersonId;
          this.orderedFoodList=new ArrayList<>();
      }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDevlieryAddress(String devlieryAddress) {
        this.devlieryAddress = devlieryAddress;
    }

    public String getDevlieryAddress() {
        return devlieryAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

 

    public List<Food> getOrderedFoodList() {
       // System.out.println(this.orderedFoodList.size());
        return orderedFoodList;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDeliveryPersonId(int deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setOrderedFoodList(List<Food> orderedFoodList) {
        this.orderedFoodList = orderedFoodList;
    }
    
    public void addToOrderList(Food food)
    {
        this.orderedFoodList.add(food);
    }
    
    public void addToGroceryList(Grocery G)
    {
        this.orderedGroceryList.add(G);
    }

    public String getCity() {
      return this.city;
    }

    public String getQueryFromDeliveryBoy() {
      return queryFromDeliveryBoy;
    }

    public void setQueryFromDeliveryBoy(String queryFromDeliveryBoy) {
      this.queryFromDeliveryBoy = queryFromDeliveryBoy;
    }

    public boolean isGroceryOrder() {
      return isGroceryOrder;
    }

    public void setGroceryOrder(boolean isGroceryOrder) {
      this.isGroceryOrder = isGroceryOrder;
    }

    public List<Grocery> getOrderedGroceryList() {
      return orderedGroceryList;
    }

    public void setOrderedGroceryList(List<Grocery> orderedGroceryList) {
      this.orderedGroceryList = orderedGroceryList;
    }
  

    
  
}
