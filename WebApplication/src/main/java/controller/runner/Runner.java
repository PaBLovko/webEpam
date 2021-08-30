package controller.runner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/library_dp";
        Properties prop = new Properties();
        prop.put("user", "library_app");
        prop.put("password", "library_password");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliantTimezoneShift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath:server.crt");
        try (Connection connection = DriverManager.getConnection(url, prop);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT id, login, password, role, inSystem FROM library_app.user";
            ResultSet resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String role = resultSet.getString(4);
                int inSystem = resultSet.getInt(5);
                users.add(new User(id, login, password, role, inSystem));
            }
            System.out.println(users);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
