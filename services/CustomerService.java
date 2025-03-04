import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    CustomerUtils customerUtils;

    public static List<Customer> customers;

    private final String header = String.format(" %-5s | %-25s | %-10s | %-15s", "Code", "Name", "Phone", "Email");

    public CustomerService() {
        customers = FileHandler.readCustomerFromFile();
        customerUtils = new CustomerUtils();
    }
    
    /**
     * Register new customer
     */

    public void registerCustomer() {
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("=== Register new customer ===");

            // 1. Input customer information
            Customer newCustomer = customerUtils.inputCustomerInfo(false); // false = not registered

            // 2. Add new customer to customers
            customers.add(newCustomer);

            // 3. Save data to file
            System.out.println("Customer registered successfully!");
            saveDataToFile();

            // 4. Ask if the user wants to continue
            isContinue = customerUtils.isContinue("Do you want to continue register (Y/N)? ");
        }    
    }

    /**
     * Update customer information
     */

    public void updateCustomer() {
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("=== Update customer information ===");

            // 1. Input customer information
            Customer exiCustomer = customerUtils.inputCustomerInfo(true); // true = registered

            // 2. Save data to file
            System.out.println("Customer information updated successfully!");
            saveDataToFile();

            // 3. Ask if the user wants to continue
            isContinue = customerUtils.isContinue("Do you want to continue update (Y/N)? ");
        } 
    }

    /**
     * Search customer by name
     */

    public void searchCustomerByName() {
        System.out.println("=== Search customer by name ===");

        // 1. Input customer name
        String keySearch = customerUtils.inputUntilValid("Enter customer name: ", Validation.CUSTOMER_NAME_VALID);

        // 2. Search customer by first name and add to foundCustomers
        List<Customer> foundCustomers = new ArrayList<>();
        
        for (Customer customer : customers) {
            String firstName = Formatter.getFirstNameOfCustomer(customer.getCustomerName());
            if (firstName.toLowerCase().contains(keySearch.toLowerCase())) {
                foundCustomers.add(customer);
            }
        }      

        // 3. Sort foundCustomers by alphabetical order
        foundCustomers.sort((c1, c2) -> c1.getCustomerName().compareToIgnoreCase(c2.getCustomerName()));

        // 4. Display foundCustomers
        if (foundCustomers.isEmpty()) {
            System.out.println("No customer found");
        } else {
            System.out.println("Found customers:");
            System.out.println(header);
            for (Customer customer : foundCustomers) {
                System.out.println(customer.toString());
            }
        }
    }

    /**
     * Show registered customer
     */

    public void showCustomer() {
        System.out.println("=== Show registered customer ===");
        System.out.println(header);
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
    }

    /**
     * Save data to file
     */
    
    public void saveDataToFile() {
        FileHandler.writeCustomerToFile(customers);
        System.out.println("Data saved to file");
    }
}
