package helper;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * JDBC is used to establish a connection to the MySQL database on the provided virtual machine. It is configured to
 * work with a MySQL server that is provided on a virtual machine, for this assignment.
 * */
public abstract class JDBC {
    /**
     * Driver.
     * */
    private static final String protocol = "jdbc";
    /**
     * Database type.
     * */
    private static final String vendor = ":mysql:";
    /**
     * Desired location.
     * */
    private static final String location = "//localhost/";
    /**
     * Database name.
     * */
    private static final String databaseName = "client_schedule";
    /**
     * URL for the provided server.
     * */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /**
     * Driver reference.
     * */
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * Username for MySQL user on the provided virtual machine.
     * */
    private static final String userName = "";
    /**
     * Password for MySQL user on the provided virtual machine.
     * */
    private static String password = "";
    /**
     * Connection interface.
     * */
    public static Connection connection;

    /**
     * Attempts to establish a connection. Prints either a success message or error message.
     * */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Attempts to close the connection. Prints either a success message or error message.
     * */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
