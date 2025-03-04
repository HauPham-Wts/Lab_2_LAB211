import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    public static List<Order> orders;
    
    Scanner sc = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#,###");

    OrderUtils orderUtils;

    public OrderService() {
        orders = FileHandler.readOrderFromFile();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orderUtils = new OrderUtils(orders);
    }

    private final String header = String.format("%-20s | %-8s | %-5s | %-10s | %-5s | %-10s", "Code", "Customer", "Menu", "Date", "Table", "Cost");

    /**
     * Place an order
     */

    public void placeOrder() {
        System.out.println("=== Place an order ===");

        Order newOrder = orderUtils.inputOrderInfo(false);

        if (newOrder == null) {
            return;
        }

        orders.add(newOrder);

        System.out.println("Order has been placed successfully!");
        saveToFile();
        
        System.out.println("-----------------------------");
        newOrder.toString();
    }   

    /**
     * Update order
     */

    public void updateOrder() {
        System.out.println("=== Update order ===");
        
        Order existOrder = orderUtils.inputOrderInfo(true);

        if (existOrder == null) {
            return;
        }

        System.out.println("Order has been updated successfully!");
        saveToFile();
    }

    /**
     * Check if the order is duplicated
     * @param customerCode
     * @param setOfMenuCode
     * @param eventDate
     * @return
     */

    public boolean isDuplicatedOrder(String customerCode, String setOfMenuCode, String eventDate) {
        for (Order order : orders) {
            if (order.getCustomerCode().equalsIgnoreCase(customerCode) && order.getSetMenuCode().equalsIgnoreCase(setOfMenuCode) && order.getEventDate().equals(eventDate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Show order
     */

    public void showOrder() {
        System.out.println("=== Show order ===");
        System.out.println(header);
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }

    /**
     * Write orders to file
     */

    public void saveToFile() {
        FileHandler.writeOrderToFile(orders);
        System.out.println("Data saved to file");
    }

}
