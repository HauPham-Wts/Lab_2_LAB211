import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    /**
     * File path
     */

    private static final String CUSTOMER_REGISTER_FILE_PATH = "data/CustomerRegister.dat";
    private static final String FEAST_MENU_FILE_PATH = "data/FeastMenu.csv";
    private static final String FEAST_ORDER_FILE_PATH = "data/FeastOrder.dat";

    // 1. CUSTOMER DATA MANAGEMENT

    /**
     * Write customers to file
     * @return customers
     */

    public static List<Customer> readCustomerFromFile() {
        List<Customer> customers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMER_REGISTER_FILE_PATH))) {
            customers = (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return customers;
    }

    /**
     * Write customers to file
     * @param customers
     */

    public static void writeCustomerToFile(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMER_REGISTER_FILE_PATH))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    // 2. MENU DATA MANAGEMENT

    /**
     * Read menu from file
     * @return menus
     * @throws IOException
     */

    public static List<Menu> readMenuFromFile() {
        List<Menu> menus = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FEAST_MENU_FILE_PATH))) {
            String line;
            String header = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 4) {
                    Menu menu = new Menu(columns[0], columns[1], Integer.parseInt(columns[2]), columns[3]);
                    menus.add(menu);
                } else {
                    System.err.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return menus;
    }

    // 3. ORDER DATA MANAGEMENT

    /**
     * Read order from file
     * @return menus
     */

    public static List<Order> readOrderFromFile() {
        List<Order> orders = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FEAST_ORDER_FILE_PATH))) {
            orders = (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return orders;
    }

    /**
     * Write orders to file
     * @param orders
     */

    public static void writeOrderToFile(List<Order> orders) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FEAST_ORDER_FILE_PATH))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
