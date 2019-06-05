package classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockAdjustmentsTableItem {

    private SimpleStringProperty dateTime = new SimpleStringProperty();
    private SimpleStringProperty adjustedBy = new SimpleStringProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
    private SimpleStringProperty reason = new SimpleStringProperty();

    public String getDateTime() {
        return dateTime.get();
    }

    public SimpleStringProperty dateTimeProperty() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime.set(dateTime);
    }

    public String getAdjustedBy() {
        return adjustedBy.get();
    }

    public SimpleStringProperty adjustedByProperty() {
        return adjustedBy;
    }

    public void setAdjustedBy(String adjustedBy) {
        this.adjustedBy.set(adjustedBy);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    public StockAdjustmentsTableItem(String dateTime, String adjustedBy, int quantity, String reason){
        setDateTime(dateTime);
        setAdjustedBy(adjustedBy);
        setQuantity(quantity);
        setReason(reason);
    }
}
