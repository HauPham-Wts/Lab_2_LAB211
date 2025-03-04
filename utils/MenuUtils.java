import java.util.List;
import java.util.Scanner;

public class MenuUtils {

    Scanner sc = new Scanner(System.in);
    List<Menu> menus = MenuService.menus;

    public MenuUtils() {
        if (menus == null) {
            System.out.println("No menus found in the file.");
        }
    }

    /**
     * Check if the data is valid, if not, ask the user to input again
     * 
     * @param message
     * @param regex
     * @return the valid input
     */

    public String inputUntilValid(String message, String regex) {
        String input = "";
        boolean valid = false;
        while (!valid) {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (Validation.isValid(input, regex)) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
        return input;
    }

    /**
     * Get exist menu code
     * 
     * @return menu code
     */

    public String getExistMenuCode(String oldExistMenuCode) {
        boolean isExist = false;
        String menuCode = "";

        while (!isExist) {
            System.out.print("Enter menu code (PWxxx): ");
            menuCode = sc.nextLine().trim();

            // If user doesn't input anything, return the old menu code
            if (menuCode.isEmpty()) {
                return oldExistMenuCode;
            }

            // Validate menu code format before checking existence
            if (!menuCode.matches(Validation.MENU_CODE_VALID)) {
                System.out.println("Invalid menu code format! Please try again.");
                continue;
            }

            // Check if the entered menu code exists in the menus list
            for (Menu menu : menus) {
                if (menu.getMenuCode().equalsIgnoreCase(menuCode)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                System.out.println("Menu code does not exist! Please try again.");
            }
        }
        return menuCode;
    }

    /**
     * Get exist menu
     * 
     * @param menuCode
     * @return exist menu
     */

    public Menu getExistMenu(String menuCode) {
        Menu existMenu = null;
        for (Menu menu : menus) {
            if (menu.getMenuCode().equalsIgnoreCase(menuCode)) {
                existMenu = menu;
            }
        }
        return existMenu;
    }
}
