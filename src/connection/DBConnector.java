package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * DBConnector is configured to connect to a MySQL server that is provided on a virtual machine, for this assignment.
 * */
public abstract class DBConnector {
    private static String jdbcUrl = "";
    /**
     * Driver reference.
     * */
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static String username = "";
    private static String password = "";
    /**
     * Connection interface.
     * */
    public static Connection connection;

    /**
     * Attempts to establish a connection. Prints either a success message or a detailed stack trace.
     * */
    public static void openConnection()
    {
        loadDatabaseCredentials();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current Connection.
     * @Return Connection created from the current session.
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
        }
        catch(Exception e){}
    }
    /**
     * Loads username, password, and URL for database access from the connector.properties file.
     * */
    private static void loadDatabaseCredentials() {
        Properties p = new Properties();
        try {
            p.load(DBConnector.class.getClassLoader().getResourceAsStream("connection/connector.properties"));
        }
        catch (IOException ignored){}
        setJdbcUrl(p.getProperty("jdbcUrl"));
        setUsername(p.getProperty("username"));
        setPassword(p.getProperty("password"));
    }
    /**
     * Sets the URL.
     * @param url URL for server access.
     * */
    public static void setJdbcUrl(String url) {
        jdbcUrl = url;
    }
    /**
     * Sets the server username.
     * @param serverUsername Username for server access.
     * */
    public static void setUsername(String serverUsername) {
        username = serverUsername;
    }
    /**
     * Sets the server password.
     * @param serverPassword Password for server access.
     * */
    public static void setPassword(String serverPassword) {
        password = serverPassword;
    }
}
