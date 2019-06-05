package otherForms;

import classes.DatabaseConnector;
import classes.StockAdjustmentsTableItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class itemDetailFormController {

    private int itemNumber = 0;

    @FXML
    private Label lblItemNumber = new Label();
    @FXML
    private Label lblItemDescription = new Label();
    @FXML
    private Label lblDepartment = new Label();
    @FXML
    private Label lblSubDepartment = new Label();
    @FXML
    private Button btnClose = new Button();
    @FXML
    private CheckBox chkRestricted = new CheckBox();
    @FXML
    private CheckBox chkWeighted = new CheckBox();
    @FXML
    private Label lblSOH = new Label();
    @FXML
    private Label lblSOO = new Label();
    @FXML
    private Label lblSIT = new Label();
    @FXML
    private Label lblItemStatus = new Label();
    @FXML
    private TableColumn colDateTime = new TableColumn();
    @FXML
    private TableColumn colAdjustedBy = new TableColumn();
    @FXML
    private TableColumn colQuantity = new TableColumn();
    @FXML
    private TableColumn colReason = new TableColumn();
    @FXML
    private TableView tblStockAdjustments = new TableView();

    public void load(int itemNumber){
        setItemNumber(itemNumber);
        loadValues();
    }

    public void handleCloseButton(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    private void setItemNumber(int itemNumber){
        this.itemNumber = itemNumber;
    }

    private void loadValues(){
        lblItemNumber.setText(String.valueOf(itemNumber));
        DatabaseConnector dbc = new DatabaseConnector();
        String itemDetailString = dbc.getItemDetails(itemNumber);
        String[] itemDetails = itemDetailString.split(",");
        String status = "";
        String itemDescription = itemDetails[0];
        String department = itemDetails[1];
        String subDepartment = itemDetails[2];
        double barcode = Double.parseDouble(itemDetails[3]);
        double price = Double.parseDouble(itemDetails[4]);
        double promotion = Double.parseDouble(itemDetails[5]);
        byte weightedItem = Byte.parseByte(itemDetails[6]);
        byte restrictedItem = Byte.parseByte(itemDetails[7]);
        byte doNotSell = Byte.parseByte(itemDetails[8]);
        int SOH = Integer.parseInt(itemDetails[9]);
        int SOO = Integer.parseInt(itemDetails[10]);
        int SIT = Integer.parseInt(itemDetails[11]);
        String itemStatus = itemDetails[12];
        String itemLock = itemDetails[13];

        lblItemDescription.setText(itemDescription);
        lblDepartment.setText(department);
        lblSubDepartment.setText(subDepartment);
        if(restrictedItem == 1){
            chkRestricted.setSelected(true);
        }
        if(weightedItem == 1){
            chkWeighted.setSelected(true);
        }
        lblSOH.setText(String.valueOf(SOH));
        lblSOO.setText(String.valueOf(SOO));
        lblSIT.setText(String.valueOf(SIT));
        switch (itemStatus) {
            case "C":
                status = "Currently Stocked";
                break;
            case "D":
                status = "Item Deleted";
                break;
        }
        lblItemStatus.setText(status);

        colDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colAdjustedBy.setCellValueFactory(new PropertyValueFactory<>("adjustedBy"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        dbc.open();
        ResultSet rs = dbc.getStockAdjustments(itemNumber);
        String dateTime = "";
        String adjustedBy = "";
        int quantity = 0;
        String reason = "";
        try {
            while (rs.next()) {
                dateTime = rs.getString(8);
                adjustedBy = rs.getString(7);
                quantity = rs.getInt(5);
                reason = rs.getString(3);
                StockAdjustmentsTableItem sati = new StockAdjustmentsTableItem(dateTime, adjustedBy, quantity, reason);
                tblStockAdjustments.getItems().add(sati);
            }
        }
        catch (SQLException e){
            System.out.println("Error retrieving ResultSet: " + e.getMessage());
        }
        dbc.close();
        }
    }


