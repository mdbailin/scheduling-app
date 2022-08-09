package utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class LoginMonitor {
    /**
     * Store username and password values for login attempts.
     * */
    private static LinkedHashMap<String, String> userData = new LinkedHashMap<String, String>();
    /**
     * Store time combined with username and password.
     * */
    private static LinkedHashMap<String, LinkedHashMap<String, String>> loginAttempt = new LinkedHashMap<String, LinkedHashMap<String, String>>();

    public static void logAttempt(String username, String password) {
        String currentTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Timestamp.valueOf(LocalDateTime.now()));
        userData.put(username, password);
        loginAttempt.put(currentTime, userData);
    }
    public static void report() {
        System.out.println(loginAttempt.toString());
    }

}
