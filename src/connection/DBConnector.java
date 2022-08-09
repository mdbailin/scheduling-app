package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DBConnector is configured to connect to a MySQL server that is provided on a virtual machine, for this assignment.
 * */
public abstract class DBConnector {
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
    private static final String username = "sqlUser";
    /**
     * Password for MySQL user on the provided virtual machine.
     * */
    private static final String password = "";
    /**
     * Connection interface.
     * */
    public static Connection connection;

    /**
     * Attempts to establish a connection. Prints either a success message or a detailed stack trace.
     * */
    public static void openConnection()
    {
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
     * Returns the current Connection.
     * @Return Connection
     * */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Attempts to close the connection. Prints either a success message or a detailed stack trace.
     * */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e){}
    }
    public static String getDBPassword() {
        return password;
    }
}
