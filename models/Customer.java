import java.io.Serializable;

public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;
    private String customerCode;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    public Customer(String customerCode, String customerName, String customerPhone, String customerEmail) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public String getCustomerCode() {
        return customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String toString() {
        String header = String.format(" %-5s | %-25s | %-10s | %-15s", "Code", "Name", "Phone", "Email");
        String value = String.format(" %-5s | %-25s | %-10s | %-15s", customerCode.toUpperCase(), Formatter.customerNameFormat(customerName), customerPhone, customerEmail);
        String separator = "";
        for (int i = 0; i < header.length(); i++) {
            separator += "-";
        }
        return separator + "\n" + value;
    }
}
