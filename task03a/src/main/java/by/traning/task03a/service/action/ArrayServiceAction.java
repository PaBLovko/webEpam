package by.traning.task03a.service.action;

import by.traning.task03a.bean.Array;
import by.traning.task03a.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the logic of the {@link by.traning.task03a.bean.Array Array} class
 */
public class ArrayServiceAction {

    private static Logger logger = LogManager.getLogger(ArrayServiceAction.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED= "The method is invoked, array = %s";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY= "The method worked correctly, array = %s";

    /**
     * The string literal describing that method is exception
     */
    private static final String METHOD_IS_EXCEPTION = "The method is exception, exception impossible";

    /**
     * The method of filling an array with random values
     * @param array the object of the array on which the action takes place
     * @param minValue minimum value of the array
     * @param maxValue maximum value of the array
     */
    public void arrayFillRandomized(@NonNull Array<Integer> array, int minValue, int maxValue){
        logger.debug(String.format("The method is invoked, array = %s, minValue = %s, maxValue = %s",
                array, minValue, maxValue));
        for(int i = 0; i < array.getLength(); i++) {
            try {
                int value = (int) ((Math.random() * (maxValue - minValue)) + minValue);
                setElement(array, i, value);
            } catch (ServiceException e) {
                logger.error(METHOD_IS_EXCEPTION, e);
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method for filling in the array
     * @param array the array object to be filled in on
     * @param list an array object containing data
     */
    public void arrayFill(@NonNull Array<Integer> array, @NonNull List<Integer> list){
        logger.debug(String.format("The method is invoked, array = %s, list = %s", array, list));
        for(int i = 0; i < array.getLength(); i++) {
            try {
                setElement(array, i, list.get(i));
            } catch (ServiceException e) {
                logger.error(METHOD_IS_EXCEPTION, e);
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method for getting a value for a specified id
     * @param array the object of the array on which the action takes place
     * @param id cell number
     * @return the variable contained in the specified location
     * @throws ServiceException when invalid id values are specified
     */
    private int getElement(@NonNull Array<Integer> array, final int id) throws ServiceException {
        logger.debug(String.format("The method is invoked, array = %s, id = %s", array, id));
        if (checkRange(array,id)) {
            int result = array.getElement(id);
            logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
            return result;
        } else {
            logger.error("The method is exception, not correct id");
            throw new ServiceException("Not correct id");
        }
    }

    /**
     * The method for setting a value for a specified id
     * @param array the object of the matrix on which the action takes place
     * @param id cell number
     * @param value new value
     * @throws ServiceException when invalid length values are specified
     */
    private void setElement(@NonNull Array<Integer> array, int id, int value) throws ServiceException {
        logger.debug(String.format("The method is invoked, array = %s, id = %s, value = %s",
                array, id, value));
        if (checkRange(array,id)) {
            array.addElement(id,value);
            logger.info(String.format("The method worked correctly, array = %s, id = %s, value = %s",
                    array, id, value));
        } else {
            logger.error("The method is exception, not correct id");
            throw new ServiceException("Not correct id");
        }
    }

    /**
     * The method that checks the boundaries of a array
     * @param array the object of the matrix on which the action takes place
     * @param id cell number
     * @return true if the value is within the array otherwise returns false
     */
    private boolean checkRange(@NonNull Array<Integer> array, int id) {
        logger.debug(String.format("The method is invoked, array = %s, id = %s",array, id));
        boolean result = id >= 0 && id <= array.getLength() - 1;
        logger.info(String.format(METHOD_WORKED_CORRECTLY,result));
        return result;
    }

    /**
     * The method changes the values in the passed array by two id
     * @param array array with data
     * @param firstId the first number for comparison
     * @param secondId the second number for comparison
     * @throws ServiceException when first or second id is wrong
     */
    private void swapPlaces(@NonNull Array<Integer> array, int firstId, int secondId) throws ServiceException {
        int buf = getElement(array, firstId);
        setElement(array, firstId, getElement(array,secondId));
        setElement(array, secondId, buf);
    }

    /**
     * The method that sorts an array by "bubble"
     * @param array array with data
     */
    public void arraySortBubble(@NonNull Array<Integer> array){
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        try {
            for (int i = array.getLength()-1 ; i > 0 ; i--) {
                for(int j = 0 ; j < i ; j++){
                    if(getElement(array, j) > getElement(array, j+1)) {
                        swapPlaces(array, j, j + 1);
                    }
                }
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method that sorts an array by "shaker"
     * @param array array with data
     */
    public void arraySortShaker(Array<Integer> array) {
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        int left = 0;
        int right = array.getLength() - 1;
        try {
            do {
                for (int i = left; i < right; i++) {
                    if (getElement(array, i) > getElement(array, i+1)) {
                        swapPlaces(array, i, i + 1);
                    }
                }
                right--;
                for (int i = right; i > left; i--) {
                    if (getElement(array, i) < getElement(array, i-1)) {
                        swapPlaces(array, i, i - 1);
                    }
                }
                left++;
            } while (left < right);
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method that sorts an array by "selection"
     * @param array array with data
     */
    public void arraySortSelection(Array<Integer> array) {
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        try {
            for (int i = 0; i < array.getLength(); i++) {
                int pos = i;
                int min = getElement(array, i);
                for (int j = i + 1; j < array.getLength(); j++) {
                    if (getElement(array, j) < min) {
                        pos = j;
                        min = getElement(array, j);
                    }
                }
                setElement(array,pos, getElement(array, i));
                setElement(array,i,min);
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method that sorts an array by "insertion"
     * @param array array with data
     */
    public void arraySortInsertion(Array<Integer> array){
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        try {
            for (int left = 0; left < array.getLength(); left++) {
                int value = getElement(array, left);
                int i = left - 1;
                for (; i >= 0; i--) {
                    if (value < getElement(array, i)) {
                        setElement(array, i+1, getElement(array,i));
                    } else {
                        break;
                    }
                }
                setElement(array,i+1, value);
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method that sorts an array by "hashing method"
     * @param array array with data
     */
    public void arraySortHashing(Array<Integer> array) {
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        HashMap<Integer, Integer> hm = new HashMap<>();
        Integer temp;
        for (Integer i : array.getValues()) {
            temp = hm.get(i);
            if (temp == null){
                hm.put(i, 1);
            }
            else{
                hm.put(i, temp + 1);
            }
        }
        ArrayList<Integer> keys = new ArrayList<>(hm.keySet().size());
        keys.addAll(hm.keySet());
        keys.sort(Comparator.naturalOrder());
        int ptr = 0;
        try {
            for (Integer i : keys){
                for (int j = 0; j < hm.get(i); j++){
                    setElement(array, ptr++, i);
                }
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method that sorts an array by "binary merge"
     * @param array array with data
     */
    public void arraySortMerge(Array<Integer> array) {
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        mergeSort(array, 0, array.getLength()-1);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The method which will partition the collection and recursively call itself
     * @param array array with data
     * @param low low pointer
     * @param high high pointer
     */
    public void mergeSort(Array<Integer> array, int low, int high) {
        logger.debug(String.format("The method is invoked, array = %s, low = %s, high = %s",
                array, low, high));
        if (low >= high) {
            return;
        }
        int mid = (high - low) / 2 + low;
        mergeSort(array, low, mid);
        mergeSort(array, mid + 1, high);
        merge(array, low, mid, high);
        logger.info(String.format("The method worked correctly, array = %s, low = %s, high = %s", array, low, high));
    }

    /**
     * The method, which merges the results into a sorted array:
     * @param array array with data
     * @param low low pointer
     * @param mid average value of pointers
     * @param high high pointer
     */
    private void merge(Array<Integer> array, int low, int mid, int high) {
        logger.debug(String.format("The method is invoked, array = %s, low = %s, mid = %s, high = %s",
                array, low, mid, high));
        try {
            Array<Integer> copy = array.clone();
            int i = low;
            int j = mid + 1;
            int k = low;
            while (i <= mid && j <= high) {
                if (getElement(copy,i) <= getElement(copy, j)) {
                    setElement(array,k++,getElement(copy,i++));
                }
                else {
                    setElement(array,k++,getElement(copy,j++));
                }
            }
            while (i <= mid) {
                setElement(array,k++,getElement(copy,i++));
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format("The method worked correctly, array = %s, low = %s, mid = %s high = %s",
                array, low, mid, high));
    }

    /**
     * The method that sorts an array by "shell"
     * @param array array with data
     */
    public void arraySortShell(Array<Integer> array){
        logger.debug(String.format(METHOD_IS_INVOKED, array));
        try {
            int n = array.getLength();
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int key = getElement(array, i);
                    int j = i;
                    while (j >= gap && getElement(array, j-gap) > key) {
                        setElement(array, j, getElement(array, j - gap));
                        j -= gap;
                    }
                    setElement(array, j, key);
                }
            }
        }catch (ServiceException e){
            logger.error(METHOD_IS_EXCEPTION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }
}
