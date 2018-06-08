package Controller;

import Controller.DAOEntities.ItemsController;
import Controller.DAOEntities.OrdersController;
import Model.Items;
import Model.Orders;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class OrdersWindowController implements Initializable{

    @FXML
    private TableView <Orders> tableOrders;

    @FXML
    private TableColumn <Orders, Integer> numberColumn;

    @FXML
    private TableColumn <Orders, String> placeColumn;

    @FXML
    private TableColumn <Orders, String> dateColumn;

    @FXML TableColumn <Orders, String> paymentColumn;

    @FXML
    private TableColumn <Items, Double> priceColumn;

    private ObservableList<Orders> ordersData = FXCollections.observableArrayList();

    private static TableColumn.CellEditEvent<Orders,Integer> editEvent;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        numberColumn.setCellValueFactory(new PropertyValueFactory<Orders, Integer>("number"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("payment"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("delivering_place"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("sending_date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Items, Double>("items_price"));

        // заполняем таблицу данными
        tableOrders.setItems(ordersData);
    }

    // подготавливаем данные для таблицы
    private void initData() {

        OrdersController ordersController = new OrdersController();

        List<Orders> ordersList = ordersController.getOrders(LoginWindowController.getUser());
        ordersData.addAll(ordersList);
    }

    public void addItem(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddOrder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }

    public void returnToMain(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/MainWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }

    public void editOrder(TableColumn.CellEditEvent<Orders,Integer> ordersIntegerCellEditEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditOrder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        editEvent = ordersIntegerCellEditEvent;
        stage.setScene(scene);
        stage.show();
    }

    public Orders getOrder() {

        Integer orderId = editEvent.getRowValue().getOrderId();
        OrdersController ordersController = new OrdersController();

        return ordersController.getOrderById(orderId);
    }

    public void deleteItem() {
        Integer orderId = editEvent.getRowValue().getOrderId();
        OrdersController ordersController = new OrdersController();

        ordersController.deleteItemById(orderId);
    }

    public void refreshWindow() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/OrdersWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) editEvent.getTableView().getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}
