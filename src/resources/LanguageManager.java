package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class LanguageManager {
    public static ResourceBundle rb = ResourceBundle.getBundle("resources/lang", Locale.getDefault());
    private static boolean textExists = false;

    public static String getLocalString(String translate) throws MissingResourceException {
        try {
            rb.getString(translate);
            textExists = true;
        }
        catch (MissingResourceException mre){
            textExists = false;
        }
        if (textExists) {
            return rb.getString(translate);
        }
        else {
            return translate;
        }
    }
}