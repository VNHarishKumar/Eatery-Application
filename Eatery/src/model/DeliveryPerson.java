
package model;

import java.util.ArrayList;

/**
 *
 * @author ktkir
 */

public class DeliveryPerson {
private int stateId;
private String email;
private String mobile;
private String address;
private String name;
private String password;
private String city;
private Order activeOrder;

public DeliveryPerson(int stateId, String name, String  mobile, String email, String city, String password, String address)
{
    this.stateId=stateId;
    this.name=name;
    this.mobile=mobile;
    this.email=email;
    this.city=city;
    this.password=password;
    this.address=address;
    this.activeOrder = null;
}


    public int getStateId() {
        return stateId;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }
    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public boolean isAvailable() {
      return this.activeOrder == null;
    }
    public boolean setActiveOrder(Order activeOrder) {
      if(this.isAvailable() == true && activeOrder.getCity().equals(this.city)) {
        this.activeOrder = activeOrder;
        return true;
        }else {
          return false;
        }
    }
    public void completeActiveOrder() {
      this.activeOrder = null;
    }

    public Order getActiveOrder() {
        return activeOrder;
    }


    public void sendQueryToCustomer(String query) {
       this.activeOrder.setQueryFromDeliveryBoy(query);
      
    }

  
}
