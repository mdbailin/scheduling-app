package resources;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class LanguageManager {
    public static ResourceBundle rb = ResourceBundle.getBundle("resources/lang", Locale.getDefault());

    public static String getLocalString(String translate) {
        if(Locale.getDefault().getLanguage().equals("fr")) {
            return rb.getString(translate);
        }
        else if (Locale.getDefault().getLanguage().equals("en")){
            return rb.getString(translate);
        }
        else {
            return translate;
        }
    }
}