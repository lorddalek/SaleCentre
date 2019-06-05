package otherForms;

import classes.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class itemMaintenanceFormController {

    @FXML
    private Button btnClose = new Button();
    @FXML
    private ComboBox cmbDepartment = new ComboBox();
    @FXML
    private ComboBox cmbSubDepartment = new ComboBox();

    public void initialize(){
        loadDepartmentComboBoxValues();
    }

    //!!!!!!!!!!!!!!!!!!!! Button Handlers !!!!!!!!!!!!!!!!!!!!!!!!!!
    public void handleCloseButton(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    //!!!!!!!!!!!!!!!!!!!! Other Handlers !!!!!!!!!!!!!!!!!!!!!!!!!!

    public void handleDepartmentComboBoxChange(){
        String departmentName = cmbDepartment.getValue().toString();
        loadSubDepartmentComboBoxValues(departmentName);
        cmbSubDepartment.setDisable(false);
        cmbSubDepartment.promptTextProperty().setValue("Select Sub Department");
    }

    //!!!!!!!!!!!!!!!!!!!! Loaders !!!!!!!!!!!!!!!!!!!!!!!!!!
    private void loadDepartmentComboBoxValues(){
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.open();
        ResultSet departmentsRS = dbc.getDepartments();
        ArrayList<String> departmentNames = new ArrayList<>();
        try {
            while (departmentsRS.next()) {
                departmentNames.add(departmentsRS.getString(1));
            }
        }
        catch (SQLException e){
            System.out.println("Error with ResultSet: " + e.getMessage());
        }
        dbc.close();
        cmbDepartment.getItems().addAll(departmentNames);
    }

    private void loadSubDepartmentComboBoxValues(String departmentName){
        cmbSubDepartment.getItems().clear();
        DatabaseConnector dbc = new DatabaseConnector();
        dbc.open();
        ResultSet subDepartmentRS = dbc.getSubDepartments(departmentName);
        ArrayList<String> subDepartmentNames = new ArrayList<>();
        try {
            while (subDepartmentRS.next()) {
                subDepartmentNames.add(subDepartmentRS.getString(1));
            }
        }
        catch (SQLException e){
            System.out.println("Error with ResultSet: " + e.getMessage());
        }
        dbc.close();
        cmbSubDepartment.getItems().addAll(subDepartmentNames);
    }
}
