package ucf.assignments;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Objects;

public class ListofItemsController {

    public MenuItem Save;
    public MenuItem Load;
    @FXML
    private TextField filterSearch;
    @FXML public static TableView<Items> tableView;
    @FXML private TableColumn<Items, String> NameCol;
    @FXML private TableColumn<Items, String> NumberCol;
    @FXML private TableColumn<Items, String> PriceCol;

    public static ObservableList<Items> ItemList = FXCollections.observableArrayList();

    public ListofItemsController(){

    }

    @FXML
    public void initialize(){
        try{

            NameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
            PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            Items dummy = new Items("dummy", "1234567890", "9");


            tableView.setItems(ItemList);
            ItemList.add(dummy);

        } catch (Exception e){
            System.out.println("I don't work on a fundamental level.");
            e.printStackTrace();
        }
    }

    public String makeNewItem(){
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewItemGUI.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("New/Edit Item");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "I opened the screen for New Item";
    }

    public String editItem() throws IOException {
        Items item = tableView.getSelectionModel().getSelectedItem();
        NewItemController.nameOfItem.setText(item.getItemName());
        NewItemController.serialNumberBox.setText(item.getSerialNumber());
        NewItemController.priceBox.setText(item.getPrice());

        return "I opened the screen for Edit Item";
    }

    public String deleteItem(){
        ObservableList<Items> itemSelected, allItems;
        allItems = tableView.getItems();
        itemSelected = tableView.getSelectionModel().getSelectedItems();
        itemSelected.forEach(allItems::remove);
        return "I deleted an item";
    }

    String path = System.getProperty("user.dir") + "/Made_Lists";

    public String saveList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));

        System.out.println("I work for saving");
        Window stage = tableView.getScene().getWindow();
        fileChooser.setTitle("Save Menu");
        fileChooser.setInitialFileName("myList");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".json", "*.json"));

        try{
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            Gson gson = new Gson();

            gson.toJson(tableView.getItems(), writer);

            writer.close();
        } catch (IOException e){
            System.out.println("Saving doesn't work");
        }
        return "I saved a list";
    }

    public String loadList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));
        System.out.println("I work for loading");
        Window stage = tableView.getScene().getWindow();
        fileChooser.setTitle("Load Menu");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", "*.json"));

        ItemList.clear();
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());

        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("Made_Lists/myList.json");
            Items[] result = gson.fromJson(reader, Items[].class);

            for(Items x : result)
                ItemList.add(x);

        } catch (IOException e){
            System.out.println("Loading doesn't work");
        }
        return "I loaded a list from main";
    }
}
