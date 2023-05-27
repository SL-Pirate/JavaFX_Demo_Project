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
import java.time.Period;
import java.util.ResourceBundle;

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
        Scene scene = new Scene(FXMLLoader.load(App.class.getResource("login.fxml")));
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
                showAlert(
                        Alert.AlertType.ERROR,
                        "Signing up failed",
                        "Signing up failed. Please try again"
                );
            }
            else {
                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Sign Up Successful",
                        "Successfully Signed Up. Please Log In to continue"
                );

                signIn(event);
            }
        }
    }

    private boolean allFieldsFilled () {

        if (first_name_field.getText().isEmpty()) {
            showIncompleteFormAlert("first name");
            return false;
        }
        else if (!validateName(first_name_field.getText())) {
            showFieldMismatchAlert("first name");
            return false;
        }
        if (last_name_field.getText().isEmpty()) {
            showIncompleteFormAlert("last name");
            return false;
        }
        else if (!validateName(last_name_field.getText())) {
            showFieldMismatchAlert("last name");
            return false;
        }
        if (age_field.getText().isEmpty()) {
            showIncompleteFormAlert("age");
            return false;
        }
        else {
            try {
                getAge();
            }
            catch (Exception e) {
                showAlert(
                        Alert.AlertType.ERROR,
                        "Invalid age",
                        "The age you entered is invalid. Stop messing around"
                );
                return false;
            }
        }
        if (getBirthday() == null) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid birthday format",
                    "Please try setting the birthday by using the calendar icon"
            );
            return false;
        }
        else if (!validateAgeWithBirthDay(getAge(), getBirthday())) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Age mismatch",
                    "Age does not match with the birthday. Please check again"
            );
            return false;
        }

        if (civil_status_field.getText().isEmpty()) {
            showIncompleteFormAlert("civil status");
            return false;
        }
        else if (!validateName(civil_status_field.getText())) {
            showFieldMismatchAlert("civil status");
            return false;
        }
        if (country_field.getText().isEmpty()) {
            showIncompleteFormAlert("country");
            return false;
        }
        else if (!validateName(country_field.getText())) {
            showFieldMismatchAlert("country");
            return false;
        }
        if (email_field.getText().isEmpty()) {
            showIncompleteFormAlert("email");
            return false;
        }
        else if (!validateEmail(email_field.getText())) {
            showFieldMismatchAlert("email");
            return false;
        }
        if (mobile_field.getText().isEmpty()) {
            showIncompleteFormAlert("mobile");
            return false;
        }
        else if (!validatePhone(mobile_field.getText())) {
            showFieldMismatchAlert("phone");
            return false;
        }
        if (usrname_field.getText().isEmpty()) {
            showIncompleteFormAlert("username");
            return false;
        }
        else if (!validateUsrName(usrname_field.getText())) {
            showFieldMismatchAlert("username");
            return false;
        }
        if (!pw_field.getText().isEmpty()) {
            if (!pw_field.getText().equals(confirm_pw_field.getText())) {
                pw_field.setText("");
                confirm_pw_field.setText("");

                showAlert(
                        Alert.AlertType.ERROR,
                        "Passwords mismatch",
                        "Passwords does not match. Please try again"
                );

                return false;
            }
        }
        else {
            showIncompleteFormAlert("password");
            return false;
        }

        return true;
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

    private void showAlert (Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);

        alert.showAndWait();
    }

    private void showIncompleteFormAlert (String missingEntry) {
        showAlert(
                Alert.AlertType.ERROR,
                "Form Incomplete",
                "Form incomplete. Please populate the " +
                        missingEntry +
                        " field before signing up"
        );
    }
    private void showFieldMismatchAlert (String invalidEntry) {
        showAlert(
                Alert.AlertType.ERROR,
                "Invalid value",
                "Invalid " + invalidEntry
        );
    }

    private boolean validateName (String name) {
        return name.matches("^[^0-9]*$");
    }

    private boolean validateEmail (String email) {
        return email.matches("^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$");
    }

    private boolean validatePhone (String phone){
        return phone.matches("^\\+?[\\d]+$");
    }

    private boolean validateUsrName(String usrName) {
        return usrName.matches("/^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$/");
    }

    private  boolean validateAgeWithBirthDay (int age, LocalDate birthDay) {
        return (age == Period.between(birthDay, LocalDate.now()).getYears());
    }
}
