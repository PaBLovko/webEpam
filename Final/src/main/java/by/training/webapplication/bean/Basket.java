package by.training.webapplication.bean;

import java.io.Serializable;


public class Basket extends Entity implements Serializable {
    private User user;
    private double total;

    public Basket() {
    }

    public Basket(int id, User user, double total) {
        super(id);
        this.user = user;
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;
        if (!super.equals(o)) return false;

        Basket basket = (Basket) o;

        if (Double.compare(basket.total, total) != 0) return false;
        return user != null ? user.equals(basket.user) : basket.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Basket " +
                "user: " + user +
                ", total:" + total;
    }
}
