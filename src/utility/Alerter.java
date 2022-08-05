package utility;

import javafx.scene.control.Alert;
import resources.LanguageManager;

/**
 * Used to instance alerts for the user.
 * */
public abstract class Alerter {
    public static void alert(String message) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText(LanguageManager.getLocalString(message));
        a.show();
    }
}
