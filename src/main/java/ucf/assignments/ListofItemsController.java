package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.Formatter;

public class ListofItemsController {

    public MenuItem Save;
    public MenuItem Load;
    @FXML public TextField nameOfItem;
    @FXML public TextField serialNumberBox;
    @FXML public TextField priceBox;
    @FXML private TextField filterSearch;
    @FXML private TableView<Items> tableView;
    @FXML private TableColumn<Items, String> NameCol;
    @FXML private TableColumn<Items, String> NumberCol;
    @FXML private TableColumn<Items, String> PriceCol;

    public ListofItemsController(){

    }

    public static ObservableList<Items> ItemList = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> lookAtItem()
        );

        try{
            NameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            NumberCol.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
            PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

            tableView.setItems(ItemList);

        } catch (Exception e){
            System.out.println("I don't work on a fundamental level.");
            e.printStackTrace();
        }
    }

    public String makeNewItem() throws IOException {

        ItemVerifier.checkSN(serialNumberBox);

        Double amount = Double.parseDouble(priceBox.getText());

        Items item = new Items();
        item.setItemName(nameOfItem.getText());
        item.setSerialNumber(serialNumberBox.getText());
        Formatter fmt = new Formatter();
        fmt.format("%.2f", amount);
        item.setPrice("$" + fmt);
        ListofItemsController.ItemList.add(item);

        return "I added an item";
    }

    public void lookAtItem(){
        Items item = tableView.getSelectionModel().getSelectedItem();
        priceBox.setText(item.getPrice());
        serialNumberBox.setText(item.getSerialNumber());
        nameOfItem.setText(item.getItemName());
    }

    public String editItem(){
        Items item = tableView.getSelectionModel().getSelectedItem();
        item.setPrice(priceBox.getText());
        item.setSerialNumber(serialNumberBox.getText());
        item.setItemName(nameOfItem.getText());

        priceBox.clear();
        serialNumberBox.clear();
        nameOfItem.clear();

        return "I edited an item";
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
