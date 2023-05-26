package lk.ac.iit.genericfxproj;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
//import javafx.scene.control.ToggleGroup;

public class SignUpCtrlr implements Initializable {

    @FXML
    private TextField age_field;

    @FXML
    private DatePicker birthday_field;

    @FXML
    private TextField civil_status_field;

    @FXML
    private TextField country_field;

    @FXML
    private TextField email_field;

    @FXML
    private TextField mobile_field;

    @FXML
    private RadioButton female_btn;

    @FXML
    private TextField first_name_field;

//    @FXML
//    private ToggleGroup gender;

    @FXML
    private TextField last_name_field;

    @FXML
    private RadioButton male_btn;

    @FXML
    private PasswordField pw_field;

    @FXML
    private PasswordField confirm_pw_field;

    @FXML
    private TextField usrname_field;

    @FXML
    private TitledPane first_pane;

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        Platform.runLater(() -> first_pane.setExpanded(true));
    }

    @FXML
    void ClearForm(ActionEvent event) {
        age_field.setText("");
        birthday_field.setValue(null);
        civil_status_field.setText("");
        confirm_pw_field.setText("");
        country_field.setText("");
        email_field.setText("");
        first_name_field.setText("");
        last_name_field.setText("");
        pw_field.setText("");
        usrname_field.setText("");

        male_btn.setSelected(false);
        female_btn.setSelected(false);
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(HelloApplication.class.getResource("login.fxml")));
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(scene);
    }

    @FXML
    void SignUp(ActionEvent event) throws IOException {
        if (allFieldsFilled()) {
            if (
                !DB.getInstance().addUser(
                        new User(
                                first_name_field.getText(),
                                last_name_field.getText(),
                                getAge(),
                                getBirthday().toString(),
                                getGender(),
                                civil_status_field.getText(),
                                country_field.getText(),
                                email_field.getText(),
                                mobile_field.getText(),
                                usrname_field.getText(),
                                pw_field.getText()
                        )
                )
            ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Signing up failed!");
                alert.setContentText("Sign Up failed. Please try again");

                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Signe Up Successful!");
                alert.setContentText("Successfully Signed Up. Please Log In");

                alert.showAndWait();

                signIn(event);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Form incomplete");
            alert.setContentText("Please fill all fields");

            alert.showAndWait();
        }
    }

    private boolean allFieldsFilled () {
        boolean allFilled = true;

        if (age_field.getText().isEmpty()) {
            allFilled = false;
        }
        else {
            try {
                getAge();
            }
            catch (Exception e) {
                allFilled = false;
//                e.printStackTrace();
            }
        }
        if (getBirthday() == null) {
            allFilled = false;
        }
        if (civil_status_field.getText().isEmpty()) {
            allFilled = false;
        }
//        if (confirm_pw_field.getText().isEmpty()) {
//            allFilled = false;
//        }
        if (country_field.getText().isEmpty()) {
            allFilled = false;
        }
        if (email_field.getText().isEmpty()) {
            allFilled = false;
        }
        if (mobile_field.getText().isEmpty()) {
            allFilled = false;
        }
        if (first_name_field.getText().isEmpty()) {
            allFilled = false;
        }
        if (last_name_field.getText().isEmpty()) {
            allFilled = false;
        }
        if (!pw_field.getText().isEmpty()) {
            if (!pw_field.getText().equals(confirm_pw_field.getText())) {
                allFilled = false;
                pw_field.setText("");
                confirm_pw_field.setText("");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password does not match!");
                alert.setContentText("Password does not match. Please try again");

                alert.showAndWait();
            }
        }
        else {
            allFilled = false;
        }
        if (usrname_field.getText().isEmpty()) {
            allFilled = false;
        }

        // allowing option to have unspecified gender
//        if (!male_btn.isSelected() && !female_btn.isSelected()) {
//            allFilled = false;
//        }

        return allFilled;
    }

    private int getAge() {
        return Integer.parseInt(age_field.getText());
    }

    private Gender getGender() {
        if (male_btn.isSelected()) {
            return Gender.Male;
        }
        else if (female_btn.isSelected()) {
            return Gender.Female;
        }
        else{
            return Gender.Unspecified;
        }
    }

    LocalDate getBirthday () {
        return birthday_field.getValue();
    }
}
