package model;

public class Grocery {
  
  private int groceryId;
  private String groceryName;
  private int quantity;
  private double price;

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }
  private int orderQuantity;
  
  public Grocery(int groceryId, String groceryName, double price, int quantity ) {
    this.groceryId = groceryId;
    this.groceryName = groceryName;
    this.setQuantity(quantity);
    this.price = price;
  }

  public int getGroceryId() {
    return this.groceryId;
  }

  public void setGroceryName(String groceryName) {
    this.groceryName = groceryName;
  }
  
  public String getGroceryName() {  
    return this.groceryName;
  }

  public void setPrice(double price) {
    this.price =  price;    
  }
  
  public double getPrice() {
    return this.price;    
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
 



}
