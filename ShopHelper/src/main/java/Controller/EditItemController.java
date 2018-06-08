package Controller;

import Controller.DAOEntities.ItemsController;
import Model.Items;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditItemController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextArea descriptionField;


    public void deleteItem(ActionEvent actionEvent) throws IOException {

        ItemsWindowController itemsWindowController = new ItemsWindowController();
        itemsWindowController.deleteItem();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        itemsWindowController.refreshWindow();
    }

    public void editItem(ActionEvent actionEvent) throws IOException {

        ItemsWindowController itemsWindowController = new ItemsWindowController();
        ItemsController itemsController = new ItemsController();
        Items item = itemsWindowController.getItem();
        System.out.println(item);

        if (!nameField.getText().isEmpty())
        item.setName(nameField.getText());
        if (!priceField.getText().isEmpty())
            item.setPrice(Double.parseDouble(priceField.getText()));
        if (!quantityField.getText().isEmpty())
            item.setQuantity(Integer.parseInt(quantityField.getText()));
        if (!descriptionField.getText().isEmpty())
            item.setDescription(descriptionField.getText());

        itemsController.edit(item);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();


        itemsWindowController.refreshWindow();
    }

}
