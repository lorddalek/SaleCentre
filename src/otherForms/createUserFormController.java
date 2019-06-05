package otherForms;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createUserFormController {

    @FXML
    private TextField txtUserID = new TextField();
    @FXML
    private TextField txtUserName = new TextField();
    @FXML
    private PasswordField txtPassword = new PasswordField();
    @FXML
    private ComboBox cmbUserType = new ComboBox();
    @FXML
    private Button btnSave = new Button();

    @FXML
    private void initialize(){
        btnSave.setDisable(true);
    }

    @FXML
    private void handleCloseButton(ActionEvent event) {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void handleSaveButton(){
        String ID = txtUserID.getText();
        String UserName = txtUserName.getText();
        String Password;
        if(!txtPassword.getText().isEmpty()){
            Password = txtPassword.getText();
        }
        else{
            Password = "abcd1234";
        }
        String userType = cmbUserType.getValue().toString();
        byte Access = 0;
        switch (userType){
            case "Basic":
                Access = 1;
                break;
            case "Supervisor":
                Access = 2;
                break;
            case "Manager":
                Access = 3;
                break;
        }
        User newUser = new User(ID, UserName, Password, Access);
        newUser.createUser();
    }

    @FXML
    public void boxChanged(){
        if(txtUserID.getText().isEmpty() || txtUserName.getText().isEmpty() || cmbUserType.getValue() == null){
            btnSave.setDisable(true);
        }
        else {
            btnSave.setDisable(false);
        }
    }
}
