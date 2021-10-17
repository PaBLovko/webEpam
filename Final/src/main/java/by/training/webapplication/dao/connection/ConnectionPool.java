package by.training.webapplication.dao.connection;

import by.training.webapplication.dao.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger log = LogManager.getLogger(ConnectionPool.class.getName());
    private static final String THREAD_ERROR= "Thread operation error, cause ";
    private static final int POOL_SIZE = 32;
    private Semaphore semaphore = new Semaphore(POOL_SIZE);

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionInUse;
    private static AtomicReference<ConnectionPool> instance = new AtomicReference<>();

    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private final Lock connectionLock = new ReentrantLock();

    public ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionInUse = new ArrayDeque<>();
        createPool();
    }
    private void createPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = ConnectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }
    public static ConnectionPool getInstance() {
        if (instance.get() == null) {
            INSTANCE_LOCK.lock();
            try {
                instance.compareAndSet(null, new ConnectionPool());
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance.get();
    }
    public ProxyConnection getConnection() {
        ProxyConnection connection;
        try {
            semaphore.acquire();
            connectionLock.lock();
            connection = availableConnections.poll();
            connectionInUse.add(connection);
        } catch (InterruptedException e) {
            log.error("Getting connection error");
            throw new ConnectionException(THREAD_ERROR, e);
        } finally {
            connectionLock.unlock();
        }
        return connection;
    }
    public void returnConnection(ProxyConnection connection) {
        connectionLock.lock();
        try {
            if (connectionInUse.contains(connection)) {
                availableConnections.add(connection);
                connectionInUse.poll();
            }
        } finally {
            connectionLock.unlock();
            semaphore.release();
        }
    }
}
