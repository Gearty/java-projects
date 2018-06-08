package Controller;

import Controller.DAOEntities.ItemsController;
import Model.Items;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class AddItemController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextArea descriptionField;


    public void itemAdd(ActionEvent actionEvent) throws IOException {

        ItemsController itemsController = new ItemsController();
        Items items = new Items(nameField.getText(), descriptionField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(quantityField.getText()), LoginWindowController.getUser());
        itemsController.insert(items);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/ItemsWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
