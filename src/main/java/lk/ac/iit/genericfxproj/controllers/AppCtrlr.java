package lk.ac.iit.genericfxproj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ac.iit.genericfxproj.Launch;
import lk.ac.iit.genericfxproj.db.DB;
import java.io.IOException;
import java.util.Objects;

public class AppCtrlr {

    @FXML
    private PasswordField pw_field;

    @FXML
    private TextField uname_field;

    @FXML
    void signUp(ActionEvent event) throws IOException {
        Scene scene = new Scene(
                FXMLLoader.load(
                        Objects.requireNonNull(
                                Launch.class.getResource("signup.fxml")
                        )
                )
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
    }

    @FXML
    void authenticate(ActionEvent event) {
        boolean uname_ok = !uname_field.getText().isEmpty();
        boolean pw_ok = !pw_field.getText().isEmpty();
        boolean credentials_match = DB.getInstance().validateCredentials(
                uname_field.getText(),
                pw_field.getText()
        );
        if (uname_ok & pw_ok & credentials_match) {
            pw_field.setText("");
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Login Successful",
                    "Succesfully Logged in. Now go make lemon juice with it :)"
            );
        }
        else {
            pw_field.setText("");
            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Failed",
                    "Login Failed!\n" +
                            "Credentials does not match. " +
                            "Try again or\nJust give up already"
            );
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String msg) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
