package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public Connection connection = null;

    public DBConnection() {
    }

    public Connection getConnection() {
         String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/personne";
         String DB_USER = "root";
         String DB_PASSWORD = "nouhaila";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (Exception var6) {
            var6.printStackTrace();
            var6.getCause();
        }

        return this.connection;
    }
}

