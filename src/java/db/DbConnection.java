package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection connection_handler;
    private final String dbName = "projekt_znaczki";
    private final String dbUser = "root";
    private final String dbPassword = "";

    private static DbConnection instance = new DbConnection();

    public static DbConnection getInstance() {
        if (instance == null)
            instance = new DbConnection();
        return instance;
    }

    private DbConnection() 
    {
        this.connection_handler = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("Mysql connector driver error occured: " + e);
            System.exit(-1);
        }
        try {
            this.connection_handler = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8", dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println("Can't connect to database: " + e);
            System.exit(-1);
        }
        System.out.println("Successfully connected with database");
    }
   
    
    public Connection getConnectionHandler()
    {
        return connection_handler;
    }

    public void close() {
        try {
            connection_handler.close();
        } catch (SQLException e) {
            System.out.print("Error on closing database connection");
            System.exit(-1);
        }
        System.out.print("Database connection closed successfully");
    }

}
