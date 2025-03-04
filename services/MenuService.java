import java.util.List;

public class MenuService {
    
    public static List<Menu> menus;
    
    public MenuService() {
        menus = FileHandler.readMenuFromFile();
        if (menus == null) {
            System.out.println("No menus found in the file.");
        }
    }

    /**
     * Display all feast menus
     */

    public void displayFeastMenu() {    
        System.out.println("=== Feast menus for ordering ===");
        System.out.println("----------------------------------------------------------");
        for (Menu menu : menus) {
            System.out.println(menu.toString());
            System.out.println("----------------------------------------------------------");
        }
    }
}
