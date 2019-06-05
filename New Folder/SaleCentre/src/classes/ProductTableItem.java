package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class ProductTableItem {

    private SimpleIntegerProperty itemNum = new SimpleIntegerProperty(0);
    private SimpleStringProperty description = new SimpleStringProperty("");
    private SimpleStringProperty department = new SimpleStringProperty("");
    private SimpleStringProperty subDepartment = new SimpleStringProperty("");
    private SimpleDoubleProperty barcode = new SimpleDoubleProperty(0);
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private SimpleStringProperty status = new SimpleStringProperty("");

    public void setItemNum(int itemNum) {
        this.itemNum.set(itemNum);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment.set(subDepartment);
    }

    public void setBarcode(double barcode) {
        this.barcode.set(barcode);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setStatus(String status) {
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
