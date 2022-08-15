package utility;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Validator class is used to validate text fields against MySQL data types.
 * Checks for the following: VARCHAR(50), VARCHAR(100), INT(10), Email, Phone, and Postal Code.
 * */
public abstract class Validator {
    private static String name = "[a-zA-Z]+\\s[a-zA-Z]+";
    private static String address = "^(\\d+) ?([A-Za-z](?= ))? (.*?) ([^ ]+?) ?((?<= )APT)? ?((?<= )\\d*)?$";
    private static String intTen = "[0-9]{1,10}";
    private static String email = "\"(^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$){3,50}\"";
    private static String phone = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
    private static String postal = "^\\d{5}-\\d{4}|\\d{5}|[A-Z]\\d[A-Z] \\d[A-Z]\\d$" + // US and Canada https://regexlib.com/REDetails.aspx?regexp_id=23
            "(([A-Z]{1,2}[0-9][0-9A-Z]?)\\ ([0-9][A-Z]{2}))|(GIR\\ 0AA)"; // UK https://regexlib.com/REDetails.aspx?regexp_id=1295
    /**
     * Checks if input is between 1 and 50 characters. Warns the user if it does not.
     * @param fieldName The name of the field to be validated. Used in the error message.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isVarcharFifty(String fieldName, String validate) {
        Pattern pattern = Pattern.compile("^.{1,50}$");
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert(fieldName + " must contain between 1 and 50 characters.", "Invalid entry");
            return false;
        }
    }
    /**
     * Checks if input is between 1 and 100 characters. Warns the user if it does not.
     * @param fieldName The name of the field to be validated. Used in the error message.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isVarcharHundred(String fieldName, String validate) {
        Pattern pattern = Pattern.compile("^.{1,100}$");
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert(fieldName + " must contain between 1 and 100 characters.", "Invalid entry");
            return false;
        }
    }
    /**
     * Checks if input is a name consisting of at least 2 characters separated by a space.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isName(String validate) {
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert("Names must consist of at least two characters separated by a space.", "Invalid entry");
            return false;
        }
    }
    /**
     * Validates addresses that are in a similar format to the following addresses:
     * 123 ABC Street, CityName for US and Canadian addresses.
     * 123 ABC Street, BoroughName, CityName for UK addresses.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isAddress(String validate) {
        Pattern pattern = Pattern.compile(address, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert("Addresses must be in the format of 123 ABC Street, CityName or\n" +
                    "123 ABC Street, BoroughName, CityName.", "Invalid entry");
            return false;
        }
    }
    /**
     * Checks if input contains 1 - 10 numbers. Warns the user if the input is not valid.
     * @param fieldName The name of the field to be validated. Used in the error message.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isIntTen(String fieldName, String validate) {
        Pattern pattern = Pattern.compile(intTen);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert(fieldName + " must consist of 10 or less numbers.", "Invalid entry");
            return false;
        }
    }
    /**
     * Validates email addresses using RFC 5322 and checks that it is between 3 and 50 characters in length.
     * Warns the user if the input is not valid.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isEmail(String validate) {
        Pattern pattern = Pattern.compile(email, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert("Email must contain a valid email address.", "Invalid entry");
            return false;
        }
    }
    /**
     * Validates phone numbers. Phone numbers may be stored in a VARCHAR(50) datatype.
     * Checks if phone numbers are formatted correctly for US and international numbers.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isPhone(String validate) {
        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert("Phone must contain a valid phone number.", "Invalid entry");
            return false;
        }
    }
    /**
     * Validates postal codes for US, UK, and CA. Uses regex from http://unicode.org/Public/cldr/26.0.1/.
     * @param validate The String value to be validated.
     * @return true if the input is validated, false if it is not.
     * */
    public static boolean isPostalCode(String validate) {
        Pattern pattern = Pattern.compile(postal, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(validate);
        if (matcher.matches()) {
            return true;
        }
        else {
            Alerter.alert("Postal code must contain a valid postal code for US, Uk or CA.", "Invalid entry");
            return false;
        }
    }
    /**
     * Validates Date and Time entries.
     * */
    public static boolean isDateTime() {
        return true;
    }
}
