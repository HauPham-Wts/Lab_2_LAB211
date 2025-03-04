import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderCode;
    private String customerCode;
    private String setMenuCode;
    private LocalDate eventDate;
    private int numOfTables;
    private int totalCost;

    public Order(String orderCode, String customerCode, String setMenuCode, LocalDate eventDate, int numOfTables, int totalCost) {
        this.orderCode = orderCode;
        this.customerCode = customerCode;
        this.setMenuCode = setMenuCode;
        this.eventDate = eventDate;
        this.numOfTables = numOfTables;
        this.totalCost = totalCost;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSetMenuCode() {
        return setMenuCode;
    }

    public void setSetMenuCode(String setMenuCode) {
        this.setMenuCode = setMenuCode;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumOfTables() {
        return numOfTables;
    }

    public void setNumOfTables(int numOfTables) {
        this.numOfTables = numOfTables;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String header = String.format("%-20s | %-5s | %-5s | %-10s | %-5s | %-10s", "Code", "Customer", "Menu", "Date", "Table", "Cost");
        String value = String.format("%-20s | %-8s | %-5s | %-10s | %-5s | %-10s", orderCode, customerCode, setMenuCode, dtf.format(eventDate), numOfTables, totalCost);
        String separator = "";
        for (int i=0; i<header.length(); i++) {
            separator += "-";
        }
        return separator + "\n" + value;
    }
}
