package by.traning.task06.option2.bean;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
public class Matrix {
    private int[][] a;
    private List<Item> diagonal;
    private ReentrantLock locker;

}
