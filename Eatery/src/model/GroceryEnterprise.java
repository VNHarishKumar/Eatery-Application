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
public class GroceryEnterprise {
  private List<Grocery> groceryDirectory;
  private boolean isAcceptingOrders;
  private List<Order> currentOrders;

  public GroceryEnterprise() {
    this.groceryDirectory = new ArrayList<Grocery>();
    this.isAcceptingOrders = true;
    this.currentOrders = new ArrayList<Order>();
    populateGroceryDb();
  }

    public List<Order> getCurrentOrders() {
        return currentOrders;
    } 

    public void setIsAcceptingOrders(boolean isAcceptingOrders) {
        this.isAcceptingOrders = isAcceptingOrders;
    }

    public void setCurrentOrders(List<Order> currentOrders) {
        this.currentOrders = currentOrders;
    }
    
       public void removeOrderFromGrocery(int orderId)
    {
         int index = 0;
    for (Order order : this.currentOrders) {
      if (order.getOrderId() == orderId) {
        this.currentOrders.remove(index);
        return;
      }
      index++;
    }

    }
    

  public List<Grocery> getGroceryDirectory() {
    return groceryDirectory;
  }

  public void setGroceryDirectory(List<Grocery> groceryDirectory) {
    this.groceryDirectory = groceryDirectory;
  }

  public void addGrocery(Grocery grocery) {
    this.groceryDirectory.add(grocery);
  }
  
  public Boolean checkIfGroceryIdIsUnique(int groceryId)
  {
      for(Grocery gc :this.groceryDirectory)
      {
          if(gc.getGroceryId()==groceryId)
          {
              return false;
          }
      }
      return true;
  }

  public void removeGrocery(int groceryId) {
    int index = 0;
    for (Grocery grocery : this.groceryDirectory) {

      if (grocery.getGroceryId() == groceryId) {
        this.groceryDirectory.remove(index);
        return;
      }
      index++;
    }
  }

  void updateGrocery(int groceryId, String groceryName, double price, int quantity) {
    for (Grocery r : this.groceryDirectory) {

      if (r.getGroceryId() == groceryId) {
        r.setGroceryName(groceryName);
        r.setPrice(price);
        r.setQuantity(quantity);
        return;
      }
    }

  }

  public Boolean isGroceryIdUnique(int groceryId) {
    for (Grocery grocery : this.groceryDirectory) {
      if (grocery.getGroceryId() == groceryId) {
        return false;
      }
    }
    return true;
  }

  public Boolean checkIfGroceryStoreAcceptsOrder() {
    return this.isAcceptingOrders;
  }

  public void setGroceryStoreAcceptsOrder(boolean isAcceptingOrder) {
    this.isAcceptingOrders = isAcceptingOrder;
  }

  public Grocery getGroceryById(int groceryId) {
    for (Grocery grocery : this.groceryDirectory) {
      if (grocery.getGroceryId() == groceryId) {
        return grocery;
      }
    }
    return null;
  }

  public Grocery getGroceryByName(String grocery) {
    for (Grocery g : this.groceryDirectory) {
      if (g.getGroceryName().equals(grocery)) {
        return g;
      }
    }
    return null;
  }

  public void assingOrdertoGrocery(Order order) {
    this.currentOrders.add(order);
  }

  public void cancelOrderByGrocery(int orderId) {
    int index = 0;
    for (Order order : this.currentOrders) {
      if (order.getOrderId() == orderId) {
        this.currentOrders.remove(index);
        order.setStatus(OrderStatus.CANCELLED);
        return;
      }
      index++;
    }
  }

  public boolean checkIfQuantityStoreHasGroceryQuantity(int groceryId, int quantity) {
for(Grocery grocery: this.groceryDirectory) {
  if(grocery.getGroceryId() == groceryId) {
     return grocery.getQuantity() >=quantity;
  } 
}
return false;
    
  }
  
  public void populateGroceryDb()
  {
      try {
          Connection con=SQLConnection.dbconnector();
          String sql="select * from Grocery";
          PreparedStatement ps=con.prepareStatement(sql);
          ResultSet st=ps.executeQuery();
          this.groceryDirectory=new ArrayList<>();
          while(st.next())
             {
                int groceryId=st.getInt("GroceryId");
                String groceryName=st.getString("Name");
                int quantity=st.getInt("Quantity");
                double price=st.getDouble("Price");
                Grocery g= new Grocery(groceryId, groceryName,price,quantity);
                this.groceryDirectory.add(g);

             }
      } catch (SQLException ex) {
          Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);
     }
  }
  public void replaceGrocerDb()
  {
      try
      {
       System.out.print("Inside replace grocery db");
          Connection con=SQLConnection.dbconnector();
          Statement stmt=con.createStatement();
          String TruncQuery="delete from Grocery";
          stmt.executeUpdate(TruncQuery);
          for(Grocery g: this.groceryDirectory)
          {
              String InsertQuery="Insert into Grocery (GroceryId,Name,Price,Quantity) values ('"+g.getGroceryId()+"','"+g.getGroceryName()+"','"+g.getPrice()+"','"+g.getQuantity()+"')";
              stmt.executeUpdate(InsertQuery);
          }
          stmt.close();
          con.close();
          populateGroceryDb();
      } catch (SQLException ex) {
           Logger.getLogger(RestaurantFoodManagement.class.getName()).log(Level.SEVERE, null, ex);

      }
  }
  

}
