package Controller;

import Controller.DAOEntities.OrdersController;
import Controller.DAOEntities.SuppliesController;
import Model.Items;
import Model.Orders;
import Model.Supplies;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class SuppliesWindowController implements Initializable {

    @FXML
    private TableView<Supplies> tableSupplies;

    @FXML
    private TableColumn <Supplies, String> numberColumn;

    @FXML
    private TableColumn <Supplies, Double> priceColumn;

    @FXML
    private TableColumn <Supplies, String> dateColumn;

    @FXML
    private TableColumn <Supplies, String> deliveryColumn;

    private ObservableList<Supplies> suppliesData = FXCollections.observableArrayList();

    private static TableColumn.CellEditEvent<Supplies,String> editEvent;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        numberColumn.setCellValueFactory(new PropertyValueFactory<Supplies, String>("number"));
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<Supplies, String>("delivery"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Supplies, String>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Supplies, Double>("price"));

        // заполняем таблицу данными
        tableSupplies.setItems(suppliesData);
    }

    // подготавливаем данные для таблицы
    private void initData() {

        SuppliesController suppliesController = new SuppliesController();

        List<Supplies> suppliesList = suppliesController.getSupplies(LoginWindowController.getUser());
        suppliesData.addAll(suppliesList);
    }

    public void editSupply(TableColumn.CellEditEvent<Supplies,String> suppliesStringCellEditEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/EditSupply.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        editEvent = suppliesStringCellEditEvent;
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

    public void addSupply(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddSupply.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();
    }

    public Supplies getSupply() {

        Integer supplyId = editEvent.getRowValue().getSupplyId();
        SuppliesController suppliesController = new SuppliesController();

        return suppliesController.getSupplyById(supplyId);
    }

    public void refreshWindow() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/SuppliesWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) editEvent.getTableView().getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void deleteSupply() {

        Integer supplyId = editEvent.getRowValue().getSupplyId();
        SuppliesController suppliesController = new SuppliesController();

        suppliesController.deleteItemById(supplyId);
    }
}
