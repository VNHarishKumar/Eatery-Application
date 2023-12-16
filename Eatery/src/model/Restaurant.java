/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ktkir
 */
public class Restaurant {
  private int restaurantId;
  private String restaurantName;
  private String city;
  private List<Food> dishes;
  private List<Order> currentOrders;
  private List<Employee> employeeList;
  private  Boolean acceptingOrders;
  private int rating=0;

  public Restaurant(int restaurantId, String restaurantName, String city,int rating) {

    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
    this.city = city;
    this.dishes = new ArrayList<>();
    this.currentOrders = new ArrayList<>();
    this.employeeList = new ArrayList<>();
    this.acceptingOrders=true;
    this.rating=rating;
  }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public Boolean getAcceptingOrders() {
        return acceptingOrders;
    } 

    public void changeAcceptingOrders() {
        this.acceptingOrders = !this.acceptingOrders;
    }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantId(int restaurantId) {
    this.restaurantId = restaurantId;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  public String getCity() {
    return city;
  }

  public List<Employee> getEmployeeList() {
    return employeeList;
  }

  public void addToCurrentOrders(Order order) {
    this.currentOrders.add(order);
  }

  public void removeFromCurrentOrders(int orderId) {
    int index = 0;
    for (Order order : this.currentOrders) {

      if (order.getOrderId() == orderId) {
        this.currentOrders.remove(index);
        return;
      }
      index++;
    }

  }

  public List<Food> getDishes() {
    return dishes;
  }

  public List<Order> getCurrentOrders() {
    return currentOrders;
  }

  public void setCurrentOrders(List<Order> currentOrders) {
    this.currentOrders =  currentOrders;
  }
  public void addToEmployeeList(Employee emp) {
    this.employeeList.add(emp);
  }

  public void removeFromEmployeeList(int EmployeeId) {
    int index = 0;
    for (Employee emp : this.employeeList) {

      if (emp.getEmployeeId() == EmployeeId) {
        this.employeeList.remove(index);
        return;
      }
      index++;
    }
  }

  public void setEmployeeList(List<Employee> employeeList) {
    this.employeeList = employeeList;
  }

  public void addToDishes(Food Food) {
    this.dishes.add(Food);
  }

  public void removeFromDishes(int foodId) {
    int index = 0;
    for (Food f : this.dishes) {

      if (f.getFoodId() == foodId) {
        this.dishes.remove(index);
        return;
      }
      index++;
    }
  }

  public void updateDish(int foodId, String foodName, int price) {
    for (Food f : this.dishes) {
      if (f.getFoodId() == foodId) {
        f.setName(foodName);
        f.setPrice(price);
      }
    }
  }

  public void setDishes(List<Food> dishes) {
    this.dishes = dishes;
  }

  public void updateEmployee(int employeeId, String employeeName, String role) {
    for (Employee emp : this.employeeList) {
      if (emp.getEmployeeId() == employeeId) {
        emp.setName(employeeName);
        emp.setRole(role);
      }
    }
  }

}
