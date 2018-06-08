package Controller;

import Controller.DAOEntities.ItemsController;
import Controller.DAOEntities.OrdersController;
import Model.Items;
import Model.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class AddOrderController {

    @FXML
    private TextField numberField;

    @FXML
    private TextField placeField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField paymentField;

    public void orderAdd(ActionEvent actionEvent) throws IOException {
        OrdersController ordersController = new OrdersController();
        Orders order = new Orders(Integer.parseInt(numberField.getText()), placeField.getText(),
                dateField.getText(), Double.parseDouble(priceField.getText()),
                paymentField.getText() , LoginWindowController.getUser());
        ordersController.insert(order);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/View/OrdersWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
