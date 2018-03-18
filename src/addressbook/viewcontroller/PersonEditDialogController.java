package addressbook.viewcontroller;

import addressbook.helpers.DateHelper;
import addressbook.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField streetField;
    @FXML
    TextField cityField;
    @FXML
    TextField postalCodeField;
    @FXML
    TextField birthdayField;

    private Stage dialogStage;
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(this.person.getFirstName());
        lastNameField.setText(this.person.getLastName());
        streetField.setText(this.person.getStreet());
        cityField.setText(this.person.getCity());
        postalCodeField.setText(String.valueOf(this.person.getPostalCode()));
        birthdayField.setText(DateHelper.format(this.person.getBirthday()));
    }

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    private boolean isValidInput() {
        String errorInfo = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorInfo += "Błędne imię!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorInfo += "Błędne nazwisko!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorInfo += "Błędna ulica!\n";
        }
        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorInfo += "Błędne miasto!\n";
        }

        // kod pocztowy jako int
        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorInfo += "Błędny kod pocztowy!\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (Exception e) {
                errorInfo += "Błędny kod pocztowy - ma być liczbą!";
            }
        }

        // data urodzin
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorInfo += "Błędna data urodzin!\n";
        } else {
            if (!DateHelper.validDate(birthdayField.getText())) {
                errorInfo += "Błędna data urodzin!\n";
            }
        }

        if (errorInfo.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błędne dane");
            alert.setHeaderText("Podano błędne dane");
            alert.setContentText(errorInfo);

            alert.showAndWait();
            return false;
        }
    }

    private boolean okClicked = false;
    public boolean isOKClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if (isValidInput()){
            person.setFirstName(firstNameField.getText().trim());
            person.setLastName(lastNameField.getText().trim());
            person.setStreet(streetField.getText().trim());
            person.setCity(cityField.getText().toLowerCase());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setBirthday(DateHelper.parse(birthdayField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }


}
