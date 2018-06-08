package Controller;

import Controller.DAOEntities.OrdersController;
import Controller.DAOEntities.SuppliesController;
import Model.Orders;
import Model.Supplies;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditSupplyController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField deliveryField;

    public void editSupply(ActionEvent actionEvent) throws IOException {

        SuppliesWindowController suppliesWindowController = new SuppliesWindowController();
        SuppliesController suppliesController = new SuppliesController();
        Supplies supply = suppliesWindowController.getSupply();

        if (!numberField.getText().isEmpty())
            supply.setNumber(numberField.getText());
        if (!priceField.getText().isEmpty())
            supply.setPrice(Double.parseDouble(priceField.getText()));
        if (!dateField.getText().isEmpty())
            supply.setDate(dateField.getText());
        if (!deliveryField.getText().isEmpty())
            supply.setDelivery(deliveryField.getText());

        suppliesController.edit(supply);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        suppliesWindowController.refreshWindow();
    }

    public void deleteSupply(ActionEvent actionEvent) throws IOException {

        SuppliesWindowController suppliesWindowController = new SuppliesWindowController();
        suppliesWindowController.deleteSupply();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        suppliesWindowController.refreshWindow();
    }
}
