package otherForms;

import classes.DatabaseConnector;
import classes.ListTableItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listFormController {



    private String listContent = "";
    private final Map list = new HashMap<>();
    private int returnedItemNum = 0;
    private int index = 999;
    private ArrayList<Integer> itemNumbers = new ArrayList<>();




    @FXML
    private TableView tblList = new TableView();
    @FXML
    private TableColumn identifierCol = new TableColumn();
    @FXML
    private TableColumn descriptionCol = new TableColumn();
    @FXML
    private Button btnClose;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField txtSearch = new TextField();

    public void initialize(String listContent){
        identifierCol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.listContent = listContent;
        populateList();
    }



    private void populateList(){
        DatabaseConnector dbc = new DatabaseConnector();
        int counter = 0;
        if(listContent.equals("itemdesc")){
            itemNumbers = dbc.getItemNumbers();
            for(int itemNumber: itemNumbers){
                String itemDescription = dbc.getItemDescription(itemNumber);
                list.put(counter, itemNumber);
                ListTableItem lti = new ListTableItem(itemNumber, itemDescription);
                tblList.getItems().add(lti);
                counter++;
            }
        }
    }

    private void populateListBySearch(String search){
        DatabaseConnector dbc = new DatabaseConnector();
        int counter = 0;
        if(listContent.equals("itemdesc")) {
            itemNumbers = dbc.getItemNumByDescription(search);
            for (int itemNumber : itemNumbers) {
                String itemDescription = dbc.getItemDescription(itemNumber);
                list.put(counter, itemNumber);
                ListTableItem lti = new ListTableItem(itemNumber, itemDescription);
                tblList.getItems().add(lti);
                counter++;
            }
        }
    }

    public int returnData(){
        return returnedItemNum;
    }

    public void getIndex(){
        index = tblList.getSelectionModel().getFocusedIndex();
    }

    public void handleSubmitButton(){
        if(index != 999) {
            returnedItemNum = (Integer) list.get(index);
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }
        else{
            return;
        }
    }

    public void handleCancelButton(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void performSearch(){
        itemNumbers.clear();
        tblList.getItems().clear();
        String search = txtSearch.getText();
        populateListBySearch(search);
    }

}
