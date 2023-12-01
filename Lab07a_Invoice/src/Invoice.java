import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private String customerAddress;
    private List<LineItem> lineItems;

    public Invoice(String customerAddress) {
        this.customerAddress = customerAddress;
        this.lineItems = new ArrayList<>();
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public double getTotalAmountDue() {
        double total = 0;
        for (LineItem item : lineItems) {
            total += item.getTotal();
        }
        return total;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }
}

