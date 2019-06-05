package otherForms;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class messageFormController {

    @FXML
    private Button btnOK;

    @FXML
    private void handleOKButton(){
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
    }

}
