package by.traning.task03a.service.impl;

import by.traning.task03a.bean.Array;
import by.traning.task03a.service.action.ArrayServiceAction;
import by.traning.task03a.service.creator.ArrayCreator;
import by.traning.task03a.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ArrayServiceActionTest {
    private final ArrayServiceAction arrayServiceAction = new ArrayServiceAction();

    @DataProvider(name = "input_a_b")
    public Object[][] createCorrectDataForArray() throws ServiceException {
        Array<Integer> array = new ArrayCreator().create(7);
        arrayServiceAction.arrayFillRandomized(array, 1, 30);
        Array<Integer> arraySorted = array.clone();
        Arrays.sort(arraySorted.getValues());
        return
                new Object[][]{
                        {array, arraySorted},
                };
    }

    @Test(description = "Positive script of the bubble sorted",
            dataProvider = "input_a_b")
    public void bubbleSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortBubble(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the shaker sorted",
            dataProvider = "input_a_b")
    public void shakerSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortShaker(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the selection sorted",
            dataProvider = "input_a_b")
    public void selectionSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortSelection(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the hashing sorted",
            dataProvider = "input_a_b")
    public void hashingSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortHashing(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the insertion sorted",
            dataProvider = "input_a_b")
    public void insertionSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortInsertion(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the binary merge sorted",
            dataProvider = "input_a_b")
    public void mergeSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortMerge(actual);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the shell sorted",
            dataProvider = "input_a_b")
    public void shellSortTest(Array<Integer> actual, Array<Integer> expected){
        arrayServiceAction.arraySortShell(actual);
        assertEquals(actual, expected);
    }
}
