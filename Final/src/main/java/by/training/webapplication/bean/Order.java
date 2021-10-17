package by.training.webapplication.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order extends Entity implements Serializable {
    private User user;
    private Dish dish;
    private double total;
    private LocalDateTime preparationTime;
    private LocalDateTime deliveryTime;
    private StatusEnum status;

    public Order() {
    }
    public Order(int id, User user, double total, LocalDateTime preparationTime, LocalDateTime deliveryTime, StatusEnum status) {
        super(id);
        this.user = user;
        this.total = total;
        this.preparationTime = preparationTime;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(LocalDateTime preparationTime) {
        this.preparationTime = preparationTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        if (Double.compare(order.total, total) != 0) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (preparationTime != null ? !preparationTime.equals(order.preparationTime) : order.preparationTime != null)
            return false;
        if (deliveryTime != null ? !deliveryTime.equals(order.deliveryTime) : order.deliveryTime != null) return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (preparationTime != null ? preparationTime.hashCode() : 0);
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Order user: " + user +
                ", total: " + total +
                ", preparationTime: " + preparationTime +
                ", deliveryTime: " + deliveryTime +
                ", status: " + status;
    }
}
