package mainForm;

import classes.DatabaseConnector;
import classes.LocationTableItem;
import classes.ProductTableItem;
import classes.UserTableItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class mainFormController {

    private final DecimalFormat df = new DecimalFormat("#.00");
    private final ToggleGroup radGroup = new ToggleGroup();
    //!!!!!!!!!!!!!!!!!!!! Panes !!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    Pane paneItemsTable = new Pane();
    @FXML
    Pane paneItemManagement = new Pane();
    @FXML
    Pane paneUserMaintenance = new Pane();
    @FXML
    Pane paneStockAdjust = new Pane();
    @FXML
    Pane paneLocationMaintenance = new Pane();
    @FXML
    Pane paneLISMaintenance = new Pane();
    @FXML
    Pane paneSLMaintenance = new Pane();
    @FXML
    ProgressBar loaderBar = new ProgressBar();
    @FXML
    Button btnViewItem = new Button();
    @FXML
    Button btnAddItem = new Button();
    @FXML
    Button btnDeleteItem = new Button();
    @FXML
    Button btnEditItem = new Button();
    @FXML
    Button btnViewItem1 = new Button();


    //!!!!!!!!!!!!!!!!!!!! Other containers !!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    Button btnCreateUser = new Button();
    @FXML
    Button btnEditUser = new Button();
    @FXML
    Button btnDeleteUser = new Button();
    @FXML
    Button btnDone = new Button();
    @FXML
    TextField txtEditDelete = new TextField();
    @FXML
    Label lblEditDeleteUser = new Label();
    @FXML
    TextField txtSearch = new TextField();
    @FXML
    Button btnSearch = new Button();
    @FXML
    TextField txtItemNum = new TextField();
    @FXML
    Label lblItemDescription = new Label();
    @FXML
    Label lblLabelItemDescription = new Label();


    //!!!!!!!!!!!!!!!!!!!Other Items!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    Label lblLabelReason = new Label();
    @FXML
    ComboBox cmbReason = new ComboBox();
    @FXML
    RadioButton radQuantityAdjusting = new RadioButton();
    @FXML
    RadioButton radEndSOH = new RadioButton();
    @FXML
    TextField txtQuantityAdjusting = new TextField();
    @FXML
    TextField txtEndSOH = new TextField();
    @FXML
    Label lblQuantityAdjusting = new Label();
    @FXML
    Label  lblLabelQuantityAdjusting = new Label();
    @FXML
    Label lblVOA = new Label();
    @FXML
    Label lblLabelVOA = new Label();
    @FXML
    Button btnSaveStockAdjust = new Button();
    @FXML
    Button btnAddLocation = new Button();
    @FXML
    Button btnEditLocation = new Button();
    @FXML
    Button btnDeleteLocation = new Button();
    @FXML
    TextField txtLIAID = new TextField();
    @FXML
    TextField txtLIADescription = new TextField();
    @FXML
    Tab tabLIA = new Tab();
    @FXML
    Tab tabSL = new Tab();
    @FXML
    TextField txtSLID = new TextField();
    @FXML
    TextField txtSLDescription = new TextField();
    private boolean itemTableLoaded = false;
    private boolean userTableLoaded = false;
    private int quantityToAdjust = 0;
    private String adjustType = "minus";
    private String locationMaintenanceTab = "LIS";
    @FXML
    private
    Pane loaderPane = new Pane();
    @FXML
    private TableView tblItems = new TableView();
    @FXML
    private TableView tblUsers = new TableView();
    @FXML
    private TableView tblLIS = new TableView();
    @FXML
    private TableView tblSL = new TableView();
    @FXML
    private TableColumn userIDCol;
    @FXML
    private TableColumn userNameCol;
    @FXML
    private TableColumn accessCol;
    @FXML
    private TableColumn locationIDCol = new TableColumn();
    @FXML
    private TableColumn locationDescriptionCol = new TableColumn();
    @FXML
    private TableColumn storageLocationIDCol = new TableColumn();
    @FXML
    private TableColumn storageLocationDescCol = new TableColumn();
    private Map itemMap = new HashMap();
    private Map locationInStoreMap = new HashMap();
    private Map storageLocationMap = new HashMap();
    private String userName = "";
    private int tableIndex = 0;

    @FXML
    private void initialize() {
        hideAllPanes();
        hideAllButtons();
    }

    public void setUser(String userName){
        this.userName = userName;
    }

    private void hideAllPanes() {
        if (paneItemsTable.isVisible()) {
            paneItemsTable.setVisible(false);
        }
        if (loaderPane.isVisible()) {
            loaderPane.setVisible(false);
        }
        if (loaderBar.isVisible()) {
            loaderBar.setVisible(false);
        }
        if (paneItemManagement.isVisible()) {
            paneItemManagement.setVisible(false);
        }
        if(paneStockAdjust.isVisible()){
            paneStockAdjust.setVisible(false);
        }
        if(paneUserMaintenance.isVisible()){
            paneUserMaintenance.setVisible(false);
        }
        if(paneLocationMaintenance.isVisible()){
            paneLocationMaintenance.setVisible(false);
        }
    }


    //!!!!!!!!!!!!!!!!!!!! Button Handlers !!!!!!!!!!!!!!!!!!!!!!!!!!

    private void hideAllButtons() {
        if (btnEditItem.isVisible()) {
            btnEditItem.setVisible(false);
        }
        if (btnDeleteItem.isVisible()) {
            btnDeleteItem.setVisible(false);
        }
        if (btnViewItem.isVisible()) {
            btnViewItem.setVisible(false);
        }
        if (btnAddItem.isVisible()) {
            btnAddItem.setVisible(false);
        }
        if (btnViewItem1.isVisible()) {
            btnViewItem1.setVisible(false);
        }
        if(btnCreateUser.isVisible()){
            btnCreateUser.setVisible(false);
        }
        if(btnEditUser.isVisible()){
            btnEditUser.setVisible(false);
        }
        if(btnDeleteUser.isVisible()){
            btnDeleteUser.setVisible(false);
        }
        if(btnDone.isVisible()){
            btnDone.setVisible(false);
        }
        if(btnSearch.isVisible()){
            btnSearch.setVisible(false);
        }
        if(txtSearch.isVisible()){
            txtSearch.setVisible(false);
        }
        if(btnAddLocation.isVisible()){
            btnAddLocation.setVisible(false);
        }
        if(btnEditLocation.isVisible()){
            btnEditLocation.setVisible(false);
        }
        if(btnDeleteLocation.isVisible()){
            btnDeleteLocation.setVisible(false);
        }
    }

    public void handleCloseButton() {
        Platform.exit();
    }

    public void handleStockListButton() {
        hideAllPanes();
        hideAllButtons();
        loaderBar.setVisible(true);
        tblItems.getColumns().clear();
        loadItemTable();
        paneItemsTable.setVisible(true);
        btnViewItem.setDisable(true);
    }

    public void handleStockAdjustButton(){
        hideAllButtons();
        hideAllPanes();
        paneStockAdjust.setVisible(true);
        radQuantityAdjusting.setToggleGroup(radGroup);
        radEndSOH.setToggleGroup(radGroup);
    }

    public void enableViewItem() {
        btnViewItem.setDisable(false);
        btnViewItem1.setDisable(false);
    }

    public void enableUserButtons(){
        btnDeleteUser.setDisable(false);
        btnEditUser.setDisable(false);
    }

    public void handleItemManagementButton() {
        hideAllPanes();
        hideAllButtons();
        if(itemTableLoaded == false){
            loadItemTable();
        }
        paneItemsTable.setVisible(true);
        btnAddItem.setVisible(true);
        btnViewItem1.setVisible(true);
        btnDeleteItem.setVisible(true);
        btnEditItem.setVisible(true);
        txtSearch.setVisible(false);
        btnSearch.setVisible(false);
        btnViewItem1.setDisable(true);
    }

    public void handleViewItemButton(){
        int tableSelectedIndex = tblItems.getSelectionModel().getSelectedIndex();
        Object itemNum = itemMap.get(tableSelectedIndex);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/otherForms/itemDetailForm.fxml"));
            Parent root = loader.load();
            otherForms.itemDetailFormController controller = loader.getController();
            controller.load((Integer) itemNum);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 914, 610));
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            btnViewItem.setDisable(true);
        }
    }

    public void handleAddItemButton() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/otherForms/itemMaintenanceForm.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        catch (Exception e){
            System.out.println("Couldn't load window: " + e.getMessage());
        }
    }

    public void handleEditItemButton() {

    }

    public void handleDeleteItemButton() {

    }

    public void handleUserMaintenanceButton(){
        hideAllButtons();
        hideAllPanes();
        itemTableLoaded = false;
        lblEditDeleteUser.setVisible(false);
        txtEditDelete.setVisible(false);
        txtEditDelete.setText("");
        loaderBar.setVisible(true);
        loadUserTable();
        paneUserMaintenance.setVisible(true);
    }

    public void handleCreateUserButton() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/otherForms/createUserForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void handleDeleteUserButton(){
        lblEditDeleteUser.setText("Enter User ID to Delete");
        lblEditDeleteUser.setVisible(true);
        txtEditDelete.setVisible(true);
    }

    public void handleRefreshUserTableButton(){
        userTableLoaded = false;
        loadUserTable();
    }

    public void handleDeleteDoneButton(){
        String userID = txtEditDelete.getText();
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.deleteUser(userID);
        loadUserTable();
    }


    //!!!!!!!!!!!!!!!!!!!! Loaders !!!!!!!!!!!!!!!!!!!!!!!!!!

    public void handleLocationMaintenanceButton(){
        hideAllButtons();
        hideAllPanes();
        paneLocationMaintenance.setVisible(true);
        btnAddLocation.setVisible(true);
        btnEditLocation.setVisible(true);
        btnDeleteLocation.setVisible(true);
        loadLocationMaintenanceTables();
    }

    //!!!!!!!!!!!!!!Stock List Controllers!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    private void updateLoaderBar(double progress) {
        loaderBar.setProgress(progress);
    }



        //!!!!!!!!!!User Maintenance Pane Controller!!!!!!!!!!!!!!!!!!!!!!!!!

    private void loadItemTable() {
        TableColumn itemNumberCol = new TableColumn("Item Number");
            itemNumberCol.setCellValueFactory(new PropertyValueFactory<>("itemNum"));
        TableColumn itemDescriptionCol = new TableColumn("Item Description");
            itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn itemDepartmentCol = new TableColumn("Department");
            itemDepartmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        TableColumn itemSubDepartmentCol = new TableColumn("Sub-Department");
            itemSubDepartmentCol.setCellValueFactory(new PropertyValueFactory<>("subDepartment"));
        TableColumn itemBarcodeCol = new TableColumn("Barcode");
            itemBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        TableColumn itemPriceCol = new TableColumn("Price");
            itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn itemStatusCol = new TableColumn("Sts");
            itemStatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
            itemNumberCol.setPrefWidth(125);
            itemDescriptionCol.setPrefWidth(380);
            itemDepartmentCol.setPrefWidth(140);
            itemSubDepartmentCol.setPrefWidth(140);
            itemBarcodeCol.setPrefWidth(125);
            itemPriceCol.setPrefWidth(65);
            itemStatusCol.setPrefWidth(50);
            tblItems.getColumns().addAll(itemNumberCol, itemDescriptionCol, itemDepartmentCol, itemSubDepartmentCol
                    , itemBarcodeCol, itemPriceCol, itemStatusCol);
            Task loadItemTable = new Task<Void>() {
                @Override
                protected Void call() {
                    double progress;
                    DatabaseConnector dbc = new DatabaseConnector();
                    ArrayList<Integer> itemNumbers;
                    itemNumbers = dbc.getItemNumbers();
                    ObservableList<ProductTableItem> data =
                            FXCollections.observableArrayList(new ProductTableItem(0, null, null, null, 0, 0, null));
                    int dataCounter = 1;
                    int size = itemNumbers.size();
                    for (int itemNumber : itemNumbers) {
                        progress = ((double) dataCounter / size);
                        int indexCounter = 0;
                        ArrayList<String> itemDetails = new ArrayList<>();
                        itemDetails.add(dbc.getItemSummary(itemNumber));
                        String[] productDetails = itemDetails.get(indexCounter).split(",");
                        ProductTableItem pti = new ProductTableItem(Integer.parseInt(productDetails[0]), productDetails[1],
                                productDetails[2], productDetails[3], Double.parseDouble(productDetails[4]), Double.parseDouble(productDetails[5]), productDetails[6]);
                        data.add(new ProductTableItem(pti.getItemNum(), pti.getDescription(), pti.getDepartment(), pti.getSubDepartment(), pti.getBarcode()
                                , pti.getPrice(), pti.getStatus()));
                        tblItems.getItems().add(pti);
                        updateLoaderBar(progress);
                        itemMap.put(dataCounter - 1, itemNumber);
                        dataCounter++;
                    }
                    data.remove(0);
                    tblItems.setItems(data);
                    loaderBar.setProgress(0);
                    loaderBar.setVisible(false);
                    btnViewItem.setVisible(true);
                    btnSearch.setVisible(true);
                    txtSearch.setVisible(true);
                    itemTableLoaded = true;
                    return null;

                }

            };
            try {
                if(!itemTableLoaded) {
                    Thread itth = new Thread(loadItemTable);
                    itth.start();
                }
                else {
                    loaderBar.setVisible(false);
                    btnViewItem.setVisible(true);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error loading item table: " + e.getMessage());
            }

            return;
        }

        private void loadUserTable(){
        itemMap.clear();
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        accessCol.setCellValueFactory(new PropertyValueFactory<>("userAccess"));
        tblUsers.getColumns();
        Task loadUserTable = new Task<Void>(){

            @Override
            protected Void call() {
                double progress;
                DatabaseConnector dbc = new DatabaseConnector();
                ArrayList<String> userIDs;
                userIDs = dbc.getUserIDs();
                ObservableList<UserTableItem> data =
                        FXCollections.observableArrayList(new UserTableItem(null, null, null));
                int dataCounter = 1;
                int size = userIDs.size();
                for(String userID:userIDs){
                    progress = ((double) dataCounter/size);
                    int indexCounter = 0;
                    ArrayList<String> userDetails = new ArrayList<>();
                    userDetails.add(dbc.getUserDetails(userID));
                    String[] userDetail = userDetails.get(indexCounter).split(",");
                    String codeToTitle = "";
                    switch (userDetail[1]){
                        case "0":
                            codeToTitle = "Setup";
                            break;
                        case "1":
                            codeToTitle = "Basic";
                            break;
                        case "2":
                            codeToTitle = "Supervisor";
                            break;
                        case "3":
                            codeToTitle = "Manager";
                    }
                    UserTableItem uti = new UserTableItem(userID, userDetail[0], codeToTitle);
                    data.add(new UserTableItem(userID, uti.getUserName(), codeToTitle));
                    updateLoaderBar(progress);
                    dataCounter++;
                }
                data.remove(0);
                tblUsers.setItems(data);
                btnCreateUser.setVisible(true);
                btnEditUser.setVisible(true);
                btnDeleteUser.setVisible(true);
                loaderBar.setProgress(0);
                loaderBar.setVisible(false);
                return null;
            }
        };
        try{
          if(!userTableLoaded){
              Thread utth = new Thread(loadUserTable);
              utth.start();
          }
          else {
              loaderBar.setVisible(false);
              hideAllButtons();
              return;
          }
        }
        catch (Exception e){
            System.out.println("Error loading User Table");
        }
        return;
        }


        //!!!!!!!!!!!!!!!!!!Stock Adjust Pane Controller!!!!!!!!!!!!!!!!!!!!!!!!

        public void handleEditDeleteTextChanged(){
            if(!txtEditDelete.getText().isEmpty()){
                btnDone.setVisible(true);
            }
            else{
                btnDone.setVisible(false);
            }
        }

        @FXML
        private void checkForItem() {
        try {
            if (!txtItemNum.getText().isEmpty()) {
                if (txtItemNum.getText().length() > 8) {
                    return;
                }
                lblVOA.setText("");
                lblQuantityAdjusting.setText("");
                txtEndSOH.setText("");
                txtQuantityAdjusting.setText("");
                DatabaseConnector dbc = new DatabaseConnector();
                int itemNumber = Integer.parseInt(txtItemNum.getText());
                boolean checkForItem = dbc.checkItemExists(itemNumber);
                if (checkForItem) {
                    String itemDetailsString = dbc.getItemDetails(itemNumber);
                    String[] itemDetails = itemDetailsString.split(",");
                    String itemDescription = itemDetails[0];
                    lblItemDescription.setText(itemDescription);
                    lblItemDescription.setDisable(false);
                    lblLabelItemDescription.setDisable(false);
                    lblLabelReason.setDisable(false);
                    cmbReason.setDisable(false);
                    radEndSOH.setDisable(false);
                    radQuantityAdjusting.setDisable(false);
                    radQuantityAdjusting.setSelected(true);
                    txtQuantityAdjusting.setDisable(false);
                    lblLabelQuantityAdjusting.setDisable(false);
                    lblLabelVOA.setDisable(false);
                    lblVOA.setDisable(false);
                    lblQuantityAdjusting.setDisable(false);
                } else {
                    resetStockAdjust();
                }
            }
        }
        catch (NumberFormatException e){
            txtItemNum.deletePreviousChar();
        }
        }

        private void resetStockAdjust(){
            lblItemDescription.setText("This will populate when item number is entered");
            lblItemDescription.setDisable(true);
            lblLabelItemDescription.setDisable(true);
            lblLabelReason.setDisable(true);
            cmbReason.setDisable(true);
            radEndSOH.setDisable(true);
            radQuantityAdjusting.setDisable(true);
            txtQuantityAdjusting.setDisable(true);
            txtEndSOH.setDisable(true);
            lblLabelQuantityAdjusting.setDisable(true);
            lblLabelVOA.setDisable(true);
            lblVOA.setDisable(true);
            lblQuantityAdjusting.setDisable(true);
            radQuantityAdjusting.setSelected(false);
            radEndSOH.setSelected(false);
            btnSaveStockAdjust.setDisable(true);
            txtEndSOH.setText("");
            txtQuantityAdjusting.setText("");
            lblVOA.setText("");
            lblQuantityAdjusting.setText("");
            cmbReason.getSelectionModel().clearSelection();
        }

        private void setAdjustType(boolean endSOHHigher, String reason){
            if(reason.equals("+Stock Correction")){
                adjustType = "plus";
                return;
            }
            if(endSOHHigher){
                adjustType = "plus";
                return;
            }
            else{
                adjustType = "minus";
            }
            return;
    }

        public void quantityAdjustTextboxChanged(){
        try {
            if (!txtQuantityAdjusting.getText().isEmpty()) {
                int itemNumber = Integer.parseInt(txtItemNum.getText());
                quantityToAdjust = Integer.parseInt(txtQuantityAdjusting.getText());
                DatabaseConnector dbc = new DatabaseConnector();
                String itemDetailString = dbc.getItemDetails(itemNumber);
                String[] itemDetails = itemDetailString.split(",");
                double VOA = Double.parseDouble(itemDetails[4]) * quantityToAdjust;
                lblQuantityAdjusting.setText(String.valueOf(quantityToAdjust));
                lblVOA.setText(String.valueOf(df.format(VOA)));
            } else {
                lblQuantityAdjusting.setText("");
                lblVOA.setText("");
            }
        }
        catch (NumberFormatException nfe){
            txtQuantityAdjusting.deletePreviousChar();
        }
        }

        public void endSOHTextboxChanged(){
        try {
            if (!txtEndSOH.getText().isEmpty()) {
                int itemNumber = Integer.parseInt(txtItemNum.getText());
                int endSOH = Integer.parseInt(txtEndSOH.getText());
                DatabaseConnector dbc = new DatabaseConnector();
                String itemDetailString = dbc.getItemDetails(itemNumber);
                String[] itemDetails = itemDetailString.split(",");
                int currentSOH = Integer.parseInt(itemDetails[9]);
                if(endSOH > currentSOH){
                    quantityToAdjust = endSOH - currentSOH;
                    setAdjustType(true, "");
                }
                else if(endSOH < currentSOH) {
                    quantityToAdjust = currentSOH - endSOH;
                }
                double VOA = Double.parseDouble(itemDetails[4]) * quantityToAdjust;
                lblQuantityAdjusting.setText(String.valueOf(quantityToAdjust));
                lblVOA.setText(String.valueOf(df.format(VOA)));
            } else {
                lblQuantityAdjusting.setText("");
                lblVOA.setText("");
            }
        }
        catch (NumberFormatException nfe){
            txtEndSOH.deletePreviousChar();
        }
        }

        public void enableSaveButton(){
            btnSaveStockAdjust.setDisable(false);
        }

        public void radEndSOHClicked(){
        if(radEndSOH.isSelected()){
            txtEndSOH.setDisable(false);
            txtQuantityAdjusting.setDisable(true);
            txtQuantityAdjusting.setText("");
        }
        }

        public void radQuantityAdjustedClicked(){
            if(radQuantityAdjusting.isSelected()){
                txtQuantityAdjusting.setDisable(false);
                txtEndSOH.setDisable(true);
                txtEndSOH.setText("");
            }
        }

        public void handleSaveStockAdjust(){
            if(txtItemNum.getText().equals("") || quantityToAdjust == 0 || cmbReason.getValue() == null){
                return;
            }
            DatabaseConnector dbc = new DatabaseConnector();
            int itemNumber = Integer.parseInt(txtItemNum.getText());
            String reason = String.valueOf(cmbReason.getValue());
            String itemDetailsString = dbc.getItemDetails(itemNumber);
            String[] itemDetails = itemDetailsString.split(",");
            int oldSOH = Integer.parseInt(itemDetails[9]);
            int newSOH = 0;
            if(radQuantityAdjusting.isSelected() && reason.equals("+ Stock Correction")){
                adjustType = "plus";
            }
            if(adjustType.equals("minus")) {
                newSOH = oldSOH - quantityToAdjust;
            }
            else if (adjustType.equals("plus")){
                newSOH = oldSOH + quantityToAdjust;
            }
            if(reason.equals("+ Stock Correction") && (newSOH < oldSOH)){
                reason = "- Stock Correction";
            }
            else if(reason.equals("- Stock Correction") && (newSOH > oldSOH)){
                reason = "+ Stock Correction";
            }
            String storePurchase = "NO";
            dbc.saveStockAdjust(itemNumber, oldSOH, quantityToAdjust, newSOH, reason, userName, storePurchase);
            txtItemNum.setText("");
            adjustType = "minus";
            resetStockAdjust();
            btnSaveStockAdjust.setDisable(true);
        }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Location Maintenance!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void handleItemListButton(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/otherForms/listForm.fxml"));
            Parent root = loader.load();
            otherForms.listFormController controller = loader.getController();
            controller.initialize("itemdesc");
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 353, 506));
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.showAndWait();
            int itemNum = controller.returnData();
            if(itemNum != 0) {
                txtItemNum.setText(String.valueOf(itemNum));
            }
            checkForItem();
        }
        catch (Exception e){
            e.printStackTrace();
            btnViewItem.setDisable(true);
        }
    }

    public void changeLocationTab(){
        btnEditLocation.setDisable(true);
        btnDeleteLocation.setDisable(true);
        if(tabLIA.isSelected()){
            locationMaintenanceTab = "LIS";
        }
        else if(tabSL.isSelected()){
            locationMaintenanceTab = "SL";
        }
    }

    public void loadLocationMaintenanceTables(){
        tblLIS.getItems().clear();
        tblSL.getItems().clear();
        locationIDCol.setCellValueFactory(new PropertyValueFactory<>("locationID"));
        locationDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("locationDescription"));
        storageLocationIDCol.setCellValueFactory(new PropertyValueFactory<>("locationID"));
        storageLocationDescCol.setCellValueFactory(new PropertyValueFactory<>("locationDescription"));
        DatabaseConnector dbc = new DatabaseConnector();
        ArrayList<String> locationIDs = dbc.getLocationIDs();
        int LISCounter = 0;
        for(String locationID : locationIDs){
            String locationDescription = dbc.getLocationInStoreDescription(locationID);
            locationInStoreMap.put(LISCounter, locationID);
            LocationTableItem lti = new LocationTableItem(locationID, locationDescription);
            tblLIS.getItems().add(lti);
            LISCounter++;
        }
        ArrayList<String> storageLocationIDs = dbc.getStorageLocationIDs();
        int SLCounter = 0;
        for(String storageLocationID : storageLocationIDs){
            String storageLocationDescription = dbc.getStorageLocationDescription(storageLocationID);
            storageLocationMap.put(SLCounter, storageLocationID);
            LocationTableItem lti = new LocationTableItem(storageLocationID, storageLocationDescription);
            tblSL.getItems().add(lti);
            SLCounter++;
        }

    }

    public void handleAddLocationButton(){
        if(locationMaintenanceTab.equals("LIS")){
            paneSLMaintenance.setVisible(false);
            paneLISMaintenance.setVisible(true);
        }
        else if(locationMaintenanceTab.equals("SL")){
            paneLISMaintenance.setVisible(false);
            paneSLMaintenance.setVisible(true);
        }
    }

    public void handleEditLocationButton(){

    }

    public void handleDeleteLocationButton(){
        DatabaseConnector dbc = new DatabaseConnector();
        String locationID = "";
        if(locationMaintenanceTab.equals("LIS")){
            locationID = String.valueOf(locationInStoreMap.get(tableIndex));
            dbc.deleteLocation("LocationInStore", "LocationID", locationID);
        }
        else if(locationMaintenanceTab.equals("SL")){
            locationID = String.valueOf(storageLocationMap.get(tableIndex));
            dbc.deleteLocation("StorageLocation", "StorageLocationID", locationID);
        }
        loadLocationMaintenanceTables();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!Location in store maintenance!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void handleSaveLISButton(){
        if(!txtLIAID.getText().isEmpty() && !txtLIADescription.getText().isEmpty()) {
            String locationID = txtLIAID.getText();
            String locationDescription = txtLIADescription.getText();
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.saveLocationInStore(locationID, locationDescription);
            txtLIAID.setText("");
            txtLIADescription.setText("");
            loadLocationMaintenanceTables();
            paneLISMaintenance.setVisible(false);
        }
    }

    public void handleLISTableClicked(){
        tableIndex = tblLIS.getSelectionModel().getFocusedIndex();
        btnEditLocation.setDisable(false);
        btnDeleteLocation.setDisable(false);
    }

    //!!!!!!!!!!!!!!!Storage Location Maintenance!!!!!!!!!!!!!!!!!!!!

    public void handleSaveSLButton(){
        if(!txtSLID.getText().isEmpty() && !txtSLDescription.getText().isEmpty()) {
            String storageLocationID = txtSLID.getText();
            String storageLocationDescription = txtSLDescription.getText();
            DatabaseConnector dbc = new DatabaseConnector();
            dbc.saveStorageLocation(storageLocationID, storageLocationDescription);
            txtSLID.setText("");
            txtSLDescription.setText("");
            loadLocationMaintenanceTables();
            paneSLMaintenance.setVisible(false);
        }
    }

    public void handleSLTableClicked(){
        tableIndex = tblSL.getSelectionModel().getFocusedIndex();
        btnEditLocation.setDisable(false);
        btnDeleteLocation.setDisable(false);
    }


}


