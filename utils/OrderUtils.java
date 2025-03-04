import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderUtils {

    Scanner sc = new Scanner(System.in);
    List<Order> orders;
    CustomerUtils customerUtils = new CustomerUtils();
    MenuUtils menuUtils = new MenuUtils();
    int orderCodeCounter = 0;

    public OrderUtils(List<Order> orders) {
        this.orders = orders;
        if (orders == null) {
            System.out.println("No orders found in the file.");
            orders = new ArrayList<>();
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

    private String isEmpty(String message, String oldData, String regex) {
        String newData = inputUntilValid(message, regex);
        if (newData.isEmpty()) {
            return oldData;
        } else {
            return newData;
        }
    }

    /**
     * Enter order infomation
     * 
     * @param isPlaced
     * @return order
     */

    public Order inputOrderInfo(boolean isPlaced) {
        String orderCode;
        Order existOrder = null;
        String customerCode;
        String setMenuCode;
        int numOfTables;
        int totalCost;
        boolean isDuplicated = false;

        if (isPlaced) {
            LocalDate eventDate = null;
            orderCode = getExistOrderCode();
            existOrder = getExistOrder(orderCode);

            String oldSetMenuCode = existOrder.getSetMenuCode();
            setMenuCode = menuUtils.getExistMenuCode(oldSetMenuCode);

            String oldEventDate = existOrder.getEventDate().toString();
            LocalDate registeredDate = LocalDate.now();
            while (!eventDate.isAfter(registeredDate)) {
                System.out.println("Event date must be after the registered date (" + registeredDate + ").");
                eventDate = LocalDate
                    .parse(isEmpty("Enter event date (yyyy-MM-dd): ", oldEventDate, Validation.DATE_VALID));
            }
            
            int oldNumOfTables = existOrder.getNumOfTables();
            numOfTables = Integer.parseInt(
                    isEmpty("Enter num of table: ", Integer.toString(oldNumOfTables), Validation.TABLE_NUMBER_VALID));

            totalCost = (menuUtils.getExistMenu(setMenuCode).getMenuPrice()) * numOfTables;
            customerCode = existOrder.getCustomerCode();

            if (isDuplicatedOrder(customerCode, setMenuCode, eventDate)) {
                System.out.println("Duplicate order data!");
                isDuplicated = true;
            }
            if (isDuplicated) {
                return null;
            } else {
                existOrder.setSetMenuCode(setMenuCode);
                existOrder.setEventDate(eventDate);
                existOrder.setNumOfTables(numOfTables);
                existOrder.setTotalCost(totalCost);
                return existOrder;
            }

        } else {
            customerCode = customerUtils.getExistCustomerCode();
            setMenuCode = menuUtils.getExistMenuCode(null);
            LocalDate eventDate = LocalDate.parse(inputUntilValid("Enter event date (yyyy-MM-dd): ", Validation.DATE_VALID));
            numOfTables = Integer.parseInt(inputUntilValid("Enter num of table: ", Validation.TABLE_NUMBER_VALID));
            totalCost = (menuUtils.getExistMenu(setMenuCode).getMenuPrice()) * numOfTables;

            if (isDuplicatedOrder(customerCode, setMenuCode, eventDate)) {
                System.out.println("Duplicate order data!");
                isDuplicated = true;
            }

            if (isDuplicated) {
                return null;
            } else {
                orderCode = generateOrderCode(customerCode, setMenuCode, eventDate);

                return new Order(orderCode, customerCode, setMenuCode, eventDate, numOfTables, totalCost);
            }
        }
    }

    /**
     * A duplicate is defined as an order having the same customer code, set menu
     * code, and event date.
     * 
     * @param customerCode the customer code to check
     * @param setMenuCode  the set menu code to check
     * @param eventDate    the event date to check
     * @return true if a duplicate exists, false otherwise
     */

    public boolean isDuplicatedOrder(String customerCode, String setMenuCode, LocalDate eventDate) {
        for (Order order : orders) {
            if (order.getCustomerCode().equalsIgnoreCase(customerCode) &&
                    order.getSetMenuCode().equalsIgnoreCase(setMenuCode) &&
                    order.getEventDate().equals(eventDate)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Generate order code
     * 
     * @return orderCode
     */

    public String generateOrderCode(String customerCode, String setMenuCode, LocalDate eventDate) {
        return customerCode + setMenuCode + eventDate;
    }

    /**
     * Get existing order
     * 
     * @param orderCode
     * @return the existing order
     */

    public Order getExistOrder(String orderCode) {
        Order findOrder = null;
        for (Order order : orders) {
            if (order.getOrderCode().equalsIgnoreCase(orderCode)) {
                findOrder = order;
            }
        }
        return findOrder;
    }

    /**
     * Get existing order code
     * 
     * @return the existing order code
     */

    public String getExistOrderCode() {
        String orderCode = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter order code: ");
            orderCode = sc.nextLine();
            for (Order order : orders) {
                if (order.getOrderCode().equalsIgnoreCase(orderCode)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Order code does not exist. Please try again.");
            }
        }
        return orderCode;
    }
}
