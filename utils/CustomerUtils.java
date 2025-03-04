import java.util.List;
import java.util.Scanner;

public class CustomerUtils {

    private Scanner sc = new Scanner(System.in);
    private List<Customer> customers = CustomerService.customers;
    
    /**
     * Check if the data is valid, if not, ask the user to input again
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
     * Check if the user wants to continue
     * @param message 
     * @return  true if the user wants to continue, false otherwise
     */

    public boolean isContinue(String message) {
        System.out.print(message);
        String choice = sc.nextLine().trim();
        return choice.equalsIgnoreCase("Y");
    }

    /**
     * Input customer information
     * @param isRegistered
     * @param existCustomer
     * @return the customer
     */

    public Customer inputCustomerInfo(boolean isRegistered) {
        String customerCode;
        Customer existCustomer = null;

        if (isRegistered) {
            customerCode = getExistCustomerCode();
            existCustomer = getExiCustomer(customerCode);
        } else {
            customerCode = inputUntilValid("Enter customer code (G/C/Kxxxx): ", Validation.CUSTOMER_CODE_VALID);
        }

        String customerName = inputUntilValid("Enter customer name (2-25): ", Validation.CUSTOMER_NAME_VALID);
        String customerPhone = inputUntilValid("Enter customer phone: ", Validation.CUSTOMER_PHONE_VALID);
        String customerEmail = inputUntilValid("Enter customer email: ", Validation.CUSTOMER_EMAIL_VALID);

        if (isRegistered) {
            existCustomer.setCustomerName(customerName);
            existCustomer.setCustomerPhone(customerPhone);
            existCustomer.setCustomerEmail(customerEmail);
            return existCustomer;
        } else {
            return new Customer(customerCode, customerName, customerPhone, customerEmail);
        }
    }

    /**
     * Get existing customer
     * @param customers
     * @return the existing customer
     */

    public Customer getExiCustomer(String customerCode) {
        Customer findCustomer = null;
        for (Customer customer : customers) {
            if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
                findCustomer = customer;
            }
        }
        return findCustomer;
    }

    /**
     * Get existing customer code
     * @return the existing customer code
     */

    public String getExistCustomerCode() {
        boolean isExist = false;
        String customerCode = "";
        while (!isExist) {
            customerCode = inputUntilValid("Enter customer code (G/C/Kxxxx): ", Validation.CUSTOMER_CODE_VALID);
            for (Customer customer : customers) {
                if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                System.out.println("Customer not found. Please try again.");
            }
        }
        return customerCode;
    }
}
