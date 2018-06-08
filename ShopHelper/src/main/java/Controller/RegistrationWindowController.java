package Controller;

import Controller.DAOEntities.ProfileController;
import Controller.DAOEntities.UserController;
import Model.Profile;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationWindowController {

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

    @FXML
    public void userRegister(ActionEvent actionEvent) throws IOException {

        errorField.setText("");

        if(loginField.getText().isEmpty() || passwordField.getText().isEmpty() ||
                nameField.getText().isEmpty() || surnameField.getText().isEmpty() ||
                numberField.getText().isEmpty()) {
            errorField.setText("Введите данные правильно!");
            return;
        }

        if (numberField.getText().length() != 10 ){
            errorField.setText("Введите номер телефона начиная с 0");
            return;
        }

        ProfileController profileController = new ProfileController();
        UserController userController = new UserController();
        Users user = new Users(loginField.getText(), passwordField.getText());
        userController.insert(user);
        profileController.insert(new Profile(nameField.getText(), surnameField.getText(), numberField.getText(), user));

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }
}
