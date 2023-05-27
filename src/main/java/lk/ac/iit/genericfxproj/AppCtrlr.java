package lk.ac.iit.genericfxproj;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppCtrlr {

    @FXML
    private PasswordField pw_field;

    @FXML
    private TextField uname_field;

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(App.class.getResource("signup.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
    }

    @FXML
    void authenticate(ActionEvent event) {
        if (!uname_field.getText().isEmpty() & !pw_field.getText().isEmpty() & DB.getInstance().validateCredentials(uname_field.getText(), pw_field.getText())) {
            pw_field.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setContentText("Successfully Logged in. Now go make lemon juice with it :)");

            alert.showAndWait();
        }
        else {
            pw_field.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed!");
            alert.setContentText("Login Failed!\nCredentials does not match. Try again or\nJust give up already");
            alert.showAndWait();
        }
    }

}
