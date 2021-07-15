package by.traning.task06.bean;

import lombok.Data;
import lombok.NonNull;

/**
 * The class that has the necessary data to work with array
 * @param <K> the dummy object of the type that is used when declaring class members and processed data
 */
@Data
public class Array<K extends Number> implements Cloneable{
    /**
     * Array
     */
    @NonNull
    private K[] values;

    /**
     * The method that returns the length of the array
     * @return the length of the array
     */
    public int getLength(){
        return values.length;
    }

    /**
     * The method sets the new value of the array element
     * @param id cell number
     * @param value new value
     */
    public void addElement(final int id, @NonNull final K value){
        values[id] = value;
    }

    /**
     * The method returns the element by the specified values
     * @param id cell number
     * @return stored value
     */
    public K getElement(final int id){
        return values[id];
    }

    @Override
    public Array<K> clone(){
        Array<K> copy = null;
        try {
            copy = (Array<K>) super.clone();
            copy.values = values.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
