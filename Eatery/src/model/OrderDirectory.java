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
public class OrderDirectory {
     private ArrayList<Order> orderList; 

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
     
     public OrderDirectory(){
    this.orderList=new ArrayList<Order>();
    populateOrderList();
}  
     
     public void populateOrderList(){
         try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from OrderTable";
          System.out.print("Inside populate Order");
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          this.orderList=new ArrayList<Order>();
          while(st.next())
             {
                int orderId=st.getInt("OrderId");
                int restaurantId=st.getInt("RestaurantId");
                int customerId=st.getInt("CustomerId");
                int deliveryPersonId=st.getInt("DeliveryPersonId");
                OrderStatus status=OrderStatus.valueOf(st.getString("Status"));
                String devlieryAddress=st.getString("DeliveryAddress");
                String city=st.getString("City");
                String deliveryInstructions=st.getString("DeliveryInstructions");
                int totalPrice=st.getInt("TotalPrice");
                Boolean isGroceryOrder=Boolean.parseBoolean(st.getString("isGroceryOrder"));
                String queryFromDeliveryBoy=st.getString("queryFromDeliveryBoy");
                System.out.print(queryFromDeliveryBoy);
                 Order o= new Order(orderId,customerId,restaurantId,status,devlieryAddress,deliveryInstructions,totalPrice, city,isGroceryOrder,deliveryPersonId,queryFromDeliveryBoy);
             
                 this.orderList.add(o);
               
      
                 
             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     
     

     
     public void addOrder(Order order)
     {
         this.orderList.add(order);
        replaceOrderListInDB();
     }
     
    public void removeOrder(int orderId)
{
          int index = 0;
    for (Order r : this.orderList) {

      if (r.getOrderId()== orderId) {
        this.orderList.remove(index);
        return;
      }
      index++;
    } 
    
    replaceOrderListInDB();

}
    
    public Order getOrderId(int orderId)
    {
      for(Order ord : this.orderList)
      {
         //System.out.println(ord.getOrderId());
          if(ord.getOrderId()==orderId)
          {
              
              return ord;
          }
      }
      return null;
    }
    
    
   
    
    public void replaceOrderListInDB()
{
    try {
          //db replace old list  with new this.foodList;
          System.out.print("Inside replace customer db");
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from OrderTable";
          stmt.executeUpdate(TruncQuery);
          for (Order o: this.orderList)
          {
              String InsertQuery="Insert into OrderTable (OrderId,RestaurantId,CustomerId,DeliveryPersonId,Status,DeliveryAddress,DeliveryInstructions,TotalPrice,queryFromDeliveryBoy,isGroceryOrder,City) values ('"+o.getOrderId()+"','"+o.getRestaurantId()+"','"+o.getCustomerId()+"','"+o.getDeliveryPersonId()+"','"+o.getStatus().toString()+"','"+o.getDevlieryAddress()+"','"+o.getDeliveryInstructions()+"','"+o.getTotalPrice()+"','"+o.getQueryFromDeliveryBoy()+"','"+String.valueOf(o.isGroceryOrder())+"','"+o.getCity()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          populateOrderList();
      } catch (SQLException ex) {
           Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);

      }
    
}

    public  List<Order> getOrdersThatOutForDelivery() {
      List<Order> outForDeliveryOrders = new ArrayList<Order>();
      for(Order order: this.orderList) {
        if(order.getStatus().equals(OrderStatus.READY_FOR_DELIVERY)) {
          outForDeliveryOrders.add(order);
        }
      }
      return outForDeliveryOrders;
      
    }
    
        public  List<Order> getOrdersThatReadyForDelivery() {
      List<Order> outForDeliveryOrders = new ArrayList<Order>();
      for(Order order: this.orderList) {
        if(order.getStatus().equals(OrderStatus.READY_FOR_DELIVERY)) {
          outForDeliveryOrders.add(order);
        }
      }
      return outForDeliveryOrders;
      
    }

 

    public List<Order> getOrdersFromRestaurant(int restaurantId) {
      List<Order> orders = new ArrayList<Order>();
      for(Order order: this.orderList) {
        if(order.getRestaurantId() == restaurantId && order.getStatus().equals(OrderStatus.ORDER_PLACED)) {
          orders.add(order);
        }
      }
      return orders;
    }
    
    public List<Order> getOrderByDeliveryBoyId(int delId)
    {
        List<Order> orderForDeliveryPerson= new ArrayList<>();
        for(Order ord : this.orderList)
        {
            if(ord.getDeliveryPersonId()==delId)
            {
                orderForDeliveryPerson.add(ord);
            }
        }
        return orderForDeliveryPerson;
    }

    public List<Order> getPastOrdersOfCustomer(int customerId) {
      List<Order> orderOfCustomer= new ArrayList<>();
      for(Order ord : this.orderList)
      {
          System.out.print(ord.getOrderId());
          if(ord.getCustomerId()==customerId && (ord.getStatus().equals(OrderStatus.DELIVERED)||ord.getStatus().equals(OrderStatus.CANCELLED)))
          {
            orderOfCustomer.add(ord);
          }
      }
      return orderOfCustomer;
    }
    
    public List<Order> getActiveOrdersOfCustomer(int customerId) {
      List<Order> orderOfCustomer= new ArrayList<>();
      for(Order ord : this.orderList)
      {
          if(ord.getCustomerId()==customerId && !(ord.getStatus().equals(OrderStatus.DELIVERED)||ord.getStatus().equals(OrderStatus.CANCELLED)))
          {
            orderOfCustomer.add(ord);
          }
      }
      return orderOfCustomer;
    }

    public List<Order> getOrdersFromGrocery() {
     
      List<Order> orderOfGrocery= new ArrayList<>();
      for(Order ord : this.orderList)
      {
          if(ord.isGroceryOrder() && (ord.getStatus().equals(OrderStatus.ORDER_PLACED)))
          {
            orderOfGrocery.add(ord);
          }
      }
      return orderOfGrocery;
    }
    public Order getOrdersForDeliveryBoy(int deliveryBoyId) {
        
      for(Order ord : this.orderList)
      {
          if(ord.getDeliveryPersonId()==deliveryBoyId && ord.getStatus().equals(OrderStatus.OUT_FOR_DELIVERY))
          {
            return ord;
          }
      }
      return null;
    }
    public void AssignFoodToOrder(Order ord,Food f )
    {
        for (Order o:this.getOrderList())
        {
            if (o.getOrderId()==ord.getOrderId())
            {
                ord.addToOrderList(f);
            }
        }
    }

  
      
    
     
}
