package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Main;
import resources.LanguageManager;

/**
 * Used to instance alerts with a custom message and title.
 * */
public abstract class Alerter {
    /**
     * Creates an alert with a custom message and title. Contents are localized using the LanguageManager.
     * @param message is the desired message for the alert to contain.
     * @param title is the desired title for the alert window.
     * */
    public static void alert(String message, String title) {
        Alert a = new Alert(Alert.AlertType.NONE, LanguageManager.getLocalString(message), ButtonType.OK);
        a.setTitle(LanguageManager.getLocalString(title));
        a.setHeight(900);
        a.show();
    }
    /**
     * Creates a confirmation window and returns true or false depending on what the user selects.
     * @param message the desired message to be shown.
     * @return true if the user selects YES, false if NO or CANCEL is selected.
     * */
    public static boolean confirm(String message) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, LanguageManager.getLocalString(message), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            return true;
        }
        else {
            return false;
        }
    }
}
