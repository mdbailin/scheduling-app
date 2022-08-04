package utility;

import javafx.scene.control.Alert;

/**
 * Used to instance alerts for the user.
 * */
public abstract class Alerter {
    public static void alert(String message) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.show();
    }
}
