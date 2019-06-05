package mainForm;

import classes.DatabaseConnector;
import classes.ProductTableItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;


public class mainFormController {

    @FXML
    private void initialize() {
        hideAllPanes();
        hideAllButtons();
    }

    boolean itemTableLoaded = false;

    //!!!!!!!!!!!!!!!!!!!! Panes !!!!!!!!!!!!!!!!!!!!!!!!!!
    @FXML
    Pane paneItemsTable = new Pane();
    Stage stage = new Stage();
    @FXML
    Pane loaderPane = new Pane();
    @FXML
    Pane paneItemManagement = new Pane();


    //!!!!!!!!!!!!!!!!!!!! Other containers !!!!!!!!!!!!!!!!!!!!!!!!!!

    @FXML
    private TableView tblItems = new TableView();
    @FXML
    private TableColumn itemNumberCol;
    @FXML
    private TableColumn itemDescriptionCol;
    @FXML
    private TableColumn itemDepartmentCol;
    @FXML
    private TableColumn itemSubDepartmentCol;
    @FXML
    private TableColumn itemBarcodeCol;
    @FXML
    private TableColumn itemPriceCol;
    @FXML
    private TableColumn itemStatusCol;

    //!!!!!!!!!!!!!!!!!!!Other Items!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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


    public void hideAllPanes() {
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

    }


    public void hideAllButtons() {
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
    }


    //!!!!!!!!!!!!!!!!!!!! Button Handlers !!!!!!!!!!!!!!!!!!!!!!!!!!

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


    public void enableViewItem() {
        btnViewItem.setDisable(false);
    }

    public void handleItemManagementButton() {
        hideAllPanes();
        hideAllButtons();
        paneItemManagement.setVisible(true);
        btnAddItem.setVisible(true);
        btnViewItem1.setVisible(true);
        btnDeleteItem.setVisible(true);
        btnEditItem.setVisible(true);
    }

    public void handleAddItemButton() {

    }

    public void handleEditItemButton() {

    }

    public void handleDeleteItemButton() {

    }

    public void handleViewItem1Button() {

    }


    //!!!!!!!!!!!!!!!!!!!! Loaders !!!!!!!!!!!!!!!!!!!!!!!!!!


    public void updateLoaderBar(double progress) {
        loaderBar.setProgress(progress);
    }

    public void loadItemTable() {
            itemNumberCol = new TableColumn("Item Number");
            itemNumberCol.setCellValueFactory(new PropertyValueFactory<>("itemNum"));
            itemDescriptionCol = new TableColumn("Item Description");
            itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            itemDepartmentCol = new TableColumn("Department");
            itemDepartmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
            itemSubDepartmentCol = new TableColumn("Sub-Dept");
            itemSubDepartmentCol.setCellValueFactory(new PropertyValueFactory<>("subDepartment"));
            itemBarcodeCol = new TableColumn("Barcode");
            itemBarcodeCol.setCellValueFactory(new PropertyValueFactory<>("barcode"));
            itemPriceCol = new TableColumn("Price");
            itemPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            itemStatusCol = new TableColumn("Sts");
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
            Task loadTable = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
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
                        itemDetails.add(dbc.getItems(itemNumber));
                        String[] productDetails = itemDetails.get(indexCounter).split(",");
                        ProductTableItem pti = new ProductTableItem(Integer.parseInt(productDetails[0]), productDetails[1],
                                productDetails[2], productDetails[3], Double.parseDouble(productDetails[4]), Double.parseDouble(productDetails[5]), productDetails[6]);
                        data.add(new ProductTableItem(pti.getItemNum(), pti.getDescription(), pti.getDepartment(), pti.getSubDepartment(), pti.getBarcode()
                                , pti.getPrice(), pti.getStatus()));
                        tblItems.getItems().add(pti);
                        updateLoaderBar(progress);
                        dataCounter++;
                    }
                    data.remove(0);
                    tblItems.setItems(data);
                    loaderBar.setProgress(0);
                    loaderBar.setVisible(false);
                    btnViewItem.setVisible(true);
                    itemTableLoaded = true;
                    return null;

                }

            };
            try {
                if(itemTableLoaded == false) {
                    Thread th = new Thread(loadTable);
                    th.start();
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
    }


