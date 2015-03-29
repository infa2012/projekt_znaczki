package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private Connection connectionHandler;
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
        this.connectionHandler = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.print("Mysql connector driver error occured: " + e);
            System.exit(-1);
        }
        try {
            this.connectionHandler = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8", dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println("Can't connect to database: " + e);
            System.exit(-1);
        }
        System.out.println("Successfully connected with database");
    }
   
    
    public Connection getConnectionHandler()
    {
        return connectionHandler;
    }

    public void close() {
        try {
            connectionHandler.close();
        } catch (SQLException e) {
            System.out.print("Error on closing database connection");
            System.exit(-1);
        }
        System.out.print("Database connection closed successfully");
    }

    public String getDbName()
    {
        return dbName;
    }
    
    

}
