package loginForm;


import classes.DatabaseConnector;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class loginFormController {




    @FXML
    private TextField loginID;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button btnLogin;



    @FXML
    public void handleLoginButton() throws Exception{
        //To be removed
        loginID.setText("setup");
        loginPassword.setText("password");
        //End of to be removed
        String retrieveID = loginID.getText();
        String passwordstring = loginPassword.getText();
        DatabaseConnector dbc = new DatabaseConnector();
        boolean userExists = dbc.getUser(retrieveID, passwordstring);
        if(userExists == true){
            dbc.close();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/mainForm/mainForm.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Sale Centre Professional");
            newStage.setScene(new Scene(root, 1238, 802));
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
        }
        else{
            Parent root = FXMLLoader.load(getClass().getResource("/otherForms/messageFormNoUser.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("User not found");
            newStage.setScene(new Scene(root, 610, 175));
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
            return;
        }
    }


    @FXML
    public void handleExitButton(){
        Platform.exit();
    }
}
