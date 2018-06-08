package Controller;

import Controller.DAOEntities.UserController;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginWindowController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorField;

    public static Users user;

    @FXML
    public void userLogin(ActionEvent actionEvent) throws IOException {

        errorField.setText("");

        if(loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            errorField.setText("Введите данные правильно!");
            return;
        }

        UserController userController = new UserController();
        user = userController.getUserByLogin(loginField.getText());

        if(user == null || user.getPassword().compareTo(passwordField.getText()) != 0) {
            errorField.setText("Неправильный логин или пароль!");
            return;
        } else{
            errorField.setText("Все верно!");

            Parent root = FXMLLoader.load(getClass().getResource("/View/MainWindow.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();

            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void createNewAccount(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("/View/RegistrationWindow.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }

    public static Users getUser() {

        return user;
    }
}
