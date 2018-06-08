package Controller;

import Controller.DAOEntities.OrdersController;
import Controller.DAOEntities.SuppliesController;
import Model.Orders;
import Model.Supplies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddSupplyController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField deliveryField;

    public void supplyAdd(ActionEvent actionEvent) throws IOException {

        SuppliesController suppliesController = new SuppliesController();
        Supplies supply = new Supplies(numberField.getText(), Double.parseDouble(priceField.getText()),
                dateField.getText(), deliveryField.getText() , LoginWindowController.getUser());
        suppliesController.insert(supply);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/SuppliesWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
