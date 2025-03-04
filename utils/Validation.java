public interface Validation {
    /**
     * Regular expressions for validating data
     */
    
    public static final String CUSTOMER_CODE_VALID = "[CcGgKk]\\d{4}";
    public static final String CUSTOMER_NAME_VALID = "^.{2,25}$";
    public static final String CUSTOMER_PHONE_VALID = "^0\\d{9}$";
    public static final String CUSTOMER_EMAIL_VALID = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String MENU_CODE_VALID = "^[Pp][Ww]\\d{3}$";
    public static final String TABLE_NUMBER_VALID = "^[1-9]\\d*$";
    public static final String DATE_VALID = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String ORDER_CODE_VALID = "^[a-zA-Z]+$";

    /**
     * Check if the data is valid
     * @param data 
     * @param regex
     * @return true if the data is valid, false otherwise
     */

    public static boolean isValid(String data, String regex) {
        return data.matches(regex);
    }
}
