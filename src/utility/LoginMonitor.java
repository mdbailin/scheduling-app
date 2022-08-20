package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
/**
 * LoginMonitor is used to record all login attempts. Date, time, username, and incorrect passwords are appended to
 * login_activity.txt. Correct passwords are recorded as CORRECT_PASSWORD.
 * */
public abstract class LoginMonitor {
    /**
     * Constructs a String containing the login attempt details.
     * @param username The username entered.
     * @param password The password entered by the user.
     * */
    public static void logAttempt(String username, String password) {
        String date = TimeManager.getDate(ZonedDateTime.now());
        String time = TimeManager.getTime(ZonedDateTime.now());
        String attempt = "Date: " + date + "\nTime: " + time + "\nUsername: " + username +"\nPassword: " + password + "\n----------";
        report(attempt);
    }
    /**
     * Handles opening and closing all Writer objects.
     * @param attemptDetails A string containing the details to be recorded.
     * */
    public static void report(String attemptDetails) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("login_activity.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(attemptDetails);
            pw.flush();
        } catch (IOException ignored) {}
        finally {
            try {
                fw.close();
                bw.close();
                pw.close();
            } catch (NullPointerException | IOException ignored) {}
        }
    }

}
