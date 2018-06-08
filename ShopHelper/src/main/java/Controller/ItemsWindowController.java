package Controller;

import Controller.DAOEntities.ItemsController;
import Controller.DAOEntities.UserController;
import Model.Items;
import Model.Users;
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

public class ItemsWindowController implements Initializable {

    @FXML
    private TableView <Items> tableItems;

    @FXML
    private TableColumn <Items, String> nameColumn;

    @FXML
    private TableColumn <Items, String> descriptionColumn;

    @FXML
    private TableColumn <Items, Integer> quantityColumn;

    @FXML
    private TableColumn <Items, Double> priceColumn;

    private ObservableList<Items> itemsData = FXCollections.observableArrayList();

    private static TableColumn.CellEditEvent<Items,String> editEvent;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        initData();

        // устанавливаем тип и значение которое должно хранится в колонке
        nameColumn.setCellValueFactory(new PropertyValueFactory<Items, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Items, String>("description"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Items, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));

        // заполняем таблицу данными
        tableItems.setItems(itemsData);
    }

    // подготавливаем данные для таблицы
    private void initData() {

        ItemsController itemsController = new ItemsController();

        List<Items> itemsList = itemsController.getItems(LoginWindowController.getUser());
        itemsData.addAll(itemsList);
    }

    @FXML
    public void returnToMain(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/MainWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.hide();

        stage.setScene(scene);
        stage.show();
    }


    public void addItem(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddItem.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(scene);
        stage.show();


    }

    public void editItem(TableColumn.CellEditEvent<Items,String> itemsStringCellEditEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditItem.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        editEvent = itemsStringCellEditEvent;
        stage.setScene(scene);
        stage.show();
    }

    public void deleteItem(){

        Integer itemId = editEvent.getRowValue().getItemId();
        ItemsController itemsController = new ItemsController();

        itemsController.deleteItemById(itemId);
    }

    public Items getItem(){

        Integer itemId = editEvent.getRowValue().getItemId();
        ItemsController itemsController = new ItemsController();

        return itemsController.getItemById(itemId);
    }

    public void refreshWindow() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/ItemsWindow.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) editEvent.getTableView().getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }
}
