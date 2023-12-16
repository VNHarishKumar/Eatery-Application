package model;


/**
 * Represents a player type ie. Human or computer.
 */
public enum OrderStatus {
  ORDER_PLACED("ORDER_PLACED"), PREPARING_FOOD("PREPARING_FOOD"),READY_FOR_DELIVERY("READY_FOR_DELIVERY"), OUT_FOR_DELIVERY("OUT_FOR_DELIVERY"), DELIVERED("DELIVERED"), CANCELLED("CANCELLED");

  private final String disp;

  private OrderStatus(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}