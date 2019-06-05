package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductTableItem {

    private final SimpleIntegerProperty itemNum = new SimpleIntegerProperty(0);
    private final SimpleStringProperty description = new SimpleStringProperty("");
    private final SimpleStringProperty department = new SimpleStringProperty("");
    private final SimpleStringProperty subDepartment = new SimpleStringProperty("");
    private final SimpleDoubleProperty barcode = new SimpleDoubleProperty(0);
    private final SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private final SimpleStringProperty status = new SimpleStringProperty("");

    private void setItemNum(int itemNum) {
        this.itemNum.set(itemNum);
    }

    private void setDescription(String description) {
        this.description.set(description);
    }

    private void setDepartment(String department) {
        this.department.set(department);
    }

    private void setSubDepartment(String subDepartment) {
        this.subDepartment.set(subDepartment);
    }

    private void setBarcode(double barcode) {
        this.barcode.set(barcode);
    }

    private void setPrice(double price) {
        this.price.set(price);
    }

    private void setStatus(String status) {
        this.status.set(status);
    }

    public int getItemNum() {
        return itemNum.get();
    }

    public SimpleIntegerProperty itemNumProperty() {
        return itemNum;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public String getSubDepartment() {
        return subDepartment.get();
    }

    public SimpleStringProperty subDepartmentProperty() {
        return subDepartment;
    }

    public double getBarcode() {
        return barcode.get();
    }

    public SimpleDoubleProperty barcodeProperty() {
        return barcode;
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public ProductTableItem(int itemNum, String description, String department, String subDepartment, double barcode, double price, String status){
        setItemNum(itemNum);
        setDescription(description);
        setDepartment(department);
        setSubDepartment(subDepartment);
        setBarcode(barcode);
        setPrice(price);
        setStatus(status);
    }

}
