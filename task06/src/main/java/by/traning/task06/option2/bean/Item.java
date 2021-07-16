package by.traning.task06.option2.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@Data
public class Item {
    private static Logger log = LogManager.getLogger(Item.class);
    private ReentrantLock locker;
    int value;
    int n;
    int m;

    public void lockObjects() {
        log.info("Object is lock.");
        locker.lock();
    }

    public void unLock() {
        log.info("Object is unlock.");
        locker.unlock();
    }
}
