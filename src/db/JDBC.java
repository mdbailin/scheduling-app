package db;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * JDBC is configured to connect to a MySQL server that is provided on a virtual machine, for this assignment.
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
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    /**
     * Driver reference.
     * */
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * Username for MySQL user on the provided virtual machine.
     * */
    private static String username = "";
    /**
     * Password for MySQL user on the provided virtual machine.
     * */
    private static String password = "";
    /**
     * Connection interface.
     * */
    public static Connection connection;

    /**
     * Attempts to establish a connection. Prints either a success message or a detailed stack trace.
     * */
    public static void openConnection(String usernameEntry, String passwordEntry)
    {
        setUsername(usernameEntry);
        setPassword(passwordEntry);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to close the connection. Prints either a success message or a detailed stack trace.
     * */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Access to set the user's password.
     * */
    public static void setPassword(String passwordEntry) {
        password = passwordEntry;
    }
    /**
     * Access to set the username.
     * */
    public static void setUsername(String usernameEntry) {
        username = usernameEntry;
    }

}
