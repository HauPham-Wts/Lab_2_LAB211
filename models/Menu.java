public class Menu {
    private String menuCode;
    private String menuName;
    private int menuPrice;
    private String menuIngredients;

    public Menu(String menuCode, String menuName, int menuPrice, String menuIngredients) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuIngredients = menuIngredients;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuIngredients() {
        return menuIngredients;
    }

    public void setMenuIngredients(String menuIngredients) {
        this.menuIngredients = menuIngredients;
    }

    public String toString() {
        return String.format("Menu Code: %s%nMenu Name: %s%nMenu Price: %,d VND%nMenu Ingredients:%n%s",
        menuCode, menuName, menuPrice, Formatter.feastMenuFormat(menuIngredients));
    }
}
