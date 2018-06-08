package Controller;

import Controller.DAOEntities.OrdersController;
import Controller.DAOEntities.ProfileController;
import Model.Orders;
import Model.Profile;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileWindowController implements Initializable {

    private Users user = LoginWindowController.getUser();

    private Profile profile = getProfileByUser(user);

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorField;


    public void profileEdit(ActionEvent actionEvent) {

        errorField.setText("");

        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                numberField.getText().isEmpty()) {
            errorField.setText("Введите данные правильно!");
            return;
        }

        if (numberField.getText().length() != 10) {
            errorField.setText("Введите номер телефона начиная с 0");
            return;
        }

        ProfileController profileController = new ProfileController();

        profile.setName(nameField.getText());
        profile.setSurname(surnameField.getText());
        profile.setPhone_number(numberField.getText());
        user.setLogin(loginField.getText());
        user.setPassword(passwordField.getText());

        profileController.edit(profile);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    private Profile getProfileByUser(Users user) {

        ProfileController profileController = new ProfileController();

        return profileController.getProfileByUserId(user);
    }

    private void setTextToFields(){
        nameField.setText(profile.getName());
        surnameField.setText(profile.getSurname());
        numberField.setText(profile.getPhone_number());
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextToFields();
    }
}
