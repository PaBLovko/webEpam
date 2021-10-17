package by.training.webapplication.bean;

import java.io.Serializable;

public class Dish extends Entity implements Serializable {
    private String name;
    private int weight;
    private double price;
    private String description;
    private String picture;

    public Dish() {
    }

    public Dish(int id, String name, int weight, double price, String description, String picture) {
        super(id);
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture(){return picture;}

    public void setPicture(String picture){this.picture = picture;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof  Dish)) return false;
        if (!super.equals(o)) return false;

        Dish dish = (Dish) o;

        if (weight != dish.weight) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (description != null ? !description.equals(dish.description) : dish.description != null) return false;
        return picture != null ? picture.equals(dish.picture) : dish.picture == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + weight;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return super.toString() + "Dish name=" + name +
                ", weight: " + weight +
                ", price: " + price +
                ", description: " + description +
                ", picture: " + picture;
    }
}

