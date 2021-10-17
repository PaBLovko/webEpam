package by.training.webapplication.dao.connection;

import by.training.webapplication.dao.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;


public class ConnectionFactory {
    private static Logger log = LogManager.getLogger(ConnectionFactory.class);
    private static final String DATABASE_URL;
    private static final String CONNECTION_ERROR = "Failed to create a database connection";
    private static final String PATH_TO_PROPERTIES = "/database.properties";
    private static final String READ_ERROR = "Could not read the property file to create a database connection";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConnectionPool.class.getResourceAsStream(PATH_TO_PROPERTIES)) {
            properties.load(input);
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            log.error("Could not read the property file!");
            throw new ConnectionException(READ_ERROR, e);
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    private ConnectionFactory() { }

    public static Connection create() {
        log.debug("Creating connection started.");
        Connection connection;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
        } catch (SQLException e) {
            log.error("Failed to create a database connection!");
            throw new ConnectionException(CONNECTION_ERROR, e);
        }
        log.debug("Creating connection finished");
        return connection;
    }
}