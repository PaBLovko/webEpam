package by.traning.task06.option3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private static Logger log = LogManager.getLogger(Item.class);
    private Semaphore sem;
    int value;
    int n;
    int m;

    public void lockObjects() throws InterruptedException {
        log.info("Object is lock.");
        sem.acquire();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void unLock() {
        log.info("Object is unlock.");
        sem.release();
    }
}
