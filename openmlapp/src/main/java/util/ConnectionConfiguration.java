package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class ConnectionConfiguration {

    public static String CONNECTION_URL = "jdbc:mysql://13.69.186.11:3306/openml";

    public static Connection getConnnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;

        List<String> connectionString = new FileUtils().getStrings("./connectionString.txt");
        CONNECTION_URL = connectionString.get(0);
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionString.get(0), connectionString.get(1), connectionString.get(2));

        return connection;
    }
}
