package by.training.webapplication.bean;

import java.io.Serializable;

public class BasketProduct extends Entity implements Serializable {
    private Basket basket;
    private Dish dish;
    private int amount;
    private double cost;

    public BasketProduct() {
    }

    public BasketProduct(int id, Basket basket, Dish dish, int amount, double cost) {
        super(id);
        this.basket = basket;
        this.dish = dish;
        this.amount = amount;
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketProduct)) return false;
        if (!super.equals(o)) return false;

        BasketProduct that = (BasketProduct) o;

        if (amount != that.amount) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (basket != null ? !basket.equals(that.basket) : that.basket != null) return false;
        return dish != null ? dish.equals(that.dish) : that.dish == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (basket != null ? basket.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + amount;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "BasketProduct " +
                "basket: " + basket +
                ", dish: " + dish +
                ", amount: " + amount +
                ", cost: " + cost;
    }
}
