package Controller;

import Controller.DAOEntities.ItemsController;
import Controller.DAOEntities.OrdersController;
import Model.Items;
import Model.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EditOrderController{

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

    public void editOrder(ActionEvent actionEvent) throws IOException {

        OrdersWindowController ordersWindowController = new OrdersWindowController();
        OrdersController ordersController = new OrdersController();
        Orders order = ordersWindowController.getOrder();

        if (!numberField.getText().isEmpty())
            order.setNumber(Integer.parseInt(numberField.getText()));
        if (!priceField.getText().isEmpty())
            order.setItems_price(Double.parseDouble(priceField.getText()));
        if (!placeField.getText().isEmpty())
            order.setDelivering_place((placeField.getText()));
        if (!dateField.getText().isEmpty())
            order.setSending_date(dateField.getText());
        if (!paymentField.getText().isEmpty())
            order.setPayment(paymentField.getText());

        ordersController.edit(order);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        ordersWindowController.refreshWindow();
    }

    public void deleteOrder(ActionEvent actionEvent) throws IOException {
        OrdersWindowController ordersWindowController = new OrdersWindowController();
        ordersWindowController.deleteItem();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
        ordersWindowController.refreshWindow();
    }
}
