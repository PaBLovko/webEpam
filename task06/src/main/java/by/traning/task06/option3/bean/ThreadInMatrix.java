package by.traning.task06.option3.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ThreadInMatrix extends Thread {
    private static Logger log = LogManager.getLogger(ThreadInMatrix.class);

    private Matrix matrix;
    private Semaphore sem;
    private int unique;

    public ThreadInMatrix(Matrix matrix, Semaphore sem, int unique) {
        this.matrix = matrix;
        this.sem = sem;
        this.unique = unique;
    }

    public void run() {
        while (!checkDiagonal(matrix)) {
            int i = getRandomIndex(matrix);
            try {
                matrix.getDiagonal().get(i).lockObjects();
            }catch (InterruptedException e){
                log.error("Thread was interrupted");
            }
            if (matrix.getDiagonal().get(i).getValue() == 0) {
                matrix.getDiagonal().get(i).setValue(unique);
                log.info("Thread " + unique + " changed value of element " + i + " " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    log.error("Thread was interrupted.");
                }
                matrix.getMatrix()[i][i] = unique;
            }
            matrix.getDiagonal().get(i).unLock();
        }
    }

    public boolean checkDiagonal(Matrix matrix) {
        int length = matrix.getMatrix().length;
        for (int i = 0; i < length; i++) {
            if (matrix.getMatrix()[i][i] == 0) {
                return false;
            }
        }
        return true;
    }

    public int getRandomIndex(Matrix matrix) {
        return new Random().nextInt(matrix.getMatrix().length);
    }

}
