package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The LanguageManager facilitates translation by utilizing the Resource Bundle 'lang'.
 * */
public abstract class LanguageManager {
    public static ResourceBundle rb = ResourceBundle.getBundle("resources/lang", Locale.getDefault());
    private static boolean textExists = false;
    /**
     * Attempts to translate the String that is passed in. If it is not possible, the original String is returned.
     * @param translate The String to be translated.
     * @return String The translated string.
     * */
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