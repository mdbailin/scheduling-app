package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import resources.LanguageManager;

/**
 * Used to instance alerts for the user.
 * */
public abstract class Alerter {
    public static void alert(String message, String title) {
        Alert a = new Alert(Alert.AlertType.NONE, LanguageManager.getLocalString(message), ButtonType.OK);
        a.setTitle(LanguageManager.getLocalString(title));
        a.show();
    }
    public static boolean confirm(String string) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, LanguageManager.getLocalString(string), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            return true;
        }
        else {
            return false;
        }
    }
}
