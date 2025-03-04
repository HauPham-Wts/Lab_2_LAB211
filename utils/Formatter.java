import java.text.DecimalFormat;

public class Formatter {

    /**
     * Format customer name
     * @param name
     * @return formatted name
     */
    
    public static String customerNameFormat(String name) {
        // 1. Split name into words
        String[] words = name.split("\\s+");

        // 2. If the name has one word, return the name
        if (words.length == 1) {
            return words[0];
        }

        // 3. Get the first name
        String firstName = words[words.length-1];

        // 4. Get the last name
        String lastName = "";
        for (int i=0; i<words.length-1; i++) {
            lastName += words[i] + " ";
        }

        // 5. Return the formatted name
        return firstName + ", " + lastName.trim();
    }

    /**
     * Get first name of customer
     * @param name
     * @return first name
     */

    public static String getFirstNameOfCustomer(String name) {
        String[] words = name.split("\\s+");
        return words[words.length-1];
    }

    /**
     * Format feast menu
     * @param menu
     * @return formatted menu
     */

    public static String feastMenuFormat(String menu) {
        String cleanString = menu.substring(1, menu.length() -1);
        // 1. Split menu into words
        String[] words = cleanString.split("#");

        // 2. Add new line character to each word
        for (int i=0; i<words.length-1; i++) {
            words[i] = words[i].trim();
            words[i] += "\n";
        }

        // 3. Join and return the formatted menu
        return String.join("\n", words);
    }

}
