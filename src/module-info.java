module SaleCentre {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires java.sql;

    opens loginForm;
    opens mainForm;
    opens otherForms;
    opens classes;
}