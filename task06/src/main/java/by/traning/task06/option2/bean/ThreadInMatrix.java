package by.traning.task06.option2.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadInMatrix extends Thread {
    private static Logger log = LogManager.getLogger(ThreadInMatrix.class);

    private Matrix matrix;
    private ReentrantLock locker;
    private int unique;

    public ThreadInMatrix(Matrix matrix, ReentrantLock locker, int unique) {
        this.matrix = matrix;
        this.locker = locker;
        this.unique = unique;
    }

    public void run() {
        while (!checkDiagonal(matrix)) {
            int i = getRandomIndex(matrix);
            matrix.getDiagonal().get(i).lockObjects();
            if (matrix.getDiagonal().get(i).getValue() == 0) {
                matrix.getDiagonal().get(i).setValue(unique);
                log.info("Thread " + unique + " changed value of element " + i + " " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    log.error("Thread was interrupted.");
                }
                matrix.getA()[i][i] = unique;
            }
            matrix.getDiagonal().get(i).unLock();
        }
    }

    public boolean checkDiagonal(Matrix matrix) {
        int length = matrix.getA().length;
        for (int i = 0; i < length; i++) {
            if (matrix.getA()[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public int getRandomIndex(Matrix matrix) {
        return new Random().nextInt(matrix.getA().length);
    }

}
