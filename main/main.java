
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerService customerService = new CustomerService();
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();

        while (true) {
            System.out.println("\n=== TRADITIONAL FEAST ORDER MANAGEMENT ===");
            System.out.println("1. Register new customer");
            System.out.println("2. Update customer information");
            System.out.println("3. Search customer by name");
            System.out.println("4. Show feast menu");
            System.out.println("5. Place order");
            System.out.println("6. Update order");
            System.out.println("7. Show order or customer");
            System.out.println("8. Exit");
            System.out.print("Please choose your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    customerService.registerCustomer();
                    break;
                case 2:
                    customerService.updateCustomer();
                    break;
                case 3:
                    customerService.searchCustomerByName();
                    break;
                case 4:
                    menuService.displayFeastMenu();
                    break;
                case 5:
                    orderService.placeOrder();
                    break;
                case 6:
                    orderService.updateOrder();
                    break;
                case 7: 
                    System.out.println("Show order or customer");
                    System.out.print("Show customer (1) | Show order (2): ");
                    int showChoice = scanner.nextInt();
                    if (showChoice == 1) {
                        customerService.showCustomer();
                        break;
                    } else if (showChoice == 2) {
                        orderService.showOrder();
                        break;
                    } else {
                        System.out.println("Invalid choice! Please choose again!");
                        break;
                    }
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice! Please choose again!");
            }
        }
    }
}