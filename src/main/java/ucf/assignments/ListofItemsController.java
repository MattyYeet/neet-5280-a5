package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Formatter;

public class ListofItemsController {

    public MenuItem Save;
    public MenuItem Load;
    @FXML public TextField nameOfItem = new TextField();
    @FXML public TextField serialNumberBox = new TextField();
    @FXML public TextField priceBox = new TextField();
    @FXML private TextField filterSearch;
    @FXML public TableView<Items> tableView;
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

        if(ItemVerifier.checkSN(serialNumberBox.getText()).equals("I opened the sn help menu")) {
            return "SN broke so no item added";
        } else if(ItemVerifier.checkPrice(priceBox.getText()).equals("I opened the price help menu")){
            return "Price broke so no item added";
        } else if(ItemVerifier.checkName(nameOfItem.getText()).equals("I opened the name help menu")){
            return "Name broke so no item added";
        } else {
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
    }

    public void lookAtItem(){
        Items item = tableView.getSelectionModel().getSelectedItem();
        priceBox.setText(item.getPrice());
        serialNumberBox.setText(item.getSerialNumber());
        nameOfItem.setText(item.getItemName());
    }

    public String editItem() throws IOException {
        Items item = tableView.getSelectionModel().getSelectedItem();
        if(ItemVerifier.checkSN(serialNumberBox.getText()).equals("I opened the sn help menu")) {
            return "SN broke so no item added";
        } else if(ItemVerifier.checkPrice(priceBox.getText()).equals("I opened the price help menu")){
            return "Price broke so no item added";
        } else if(ItemVerifier.checkName(nameOfItem.getText()).equals("I opened the name help menu")){
            return "Name broke so no item added";
        } else {
            item.setPrice(priceBox.getText());
            item.setSerialNumber(serialNumberBox.getText());
            item.setItemName(nameOfItem.getText());

            priceBox.clear();
            serialNumberBox.clear();
            nameOfItem.clear();

            tableView.refresh();

            return "I edited an item";
        }
    }

    public String deleteItem(){
        ObservableList<Items> itemSelected, allItems;
        allItems = tableView.getItems();
        itemSelected = tableView.getSelectionModel().getSelectedItems();
        itemSelected.forEach(allItems::remove);
        return "I deleted an item";
    }

    public String searchItem(){
        ObservableList<Items> filterList = FXCollections.observableArrayList();
        for(Items item : ItemList){
            tableView.setItems(filterList);
            if(item.getItemName().contains(filterSearch.getText().toLowerCase()) || item.getSerialNumber().contains(filterSearch.getText())){
                filterList.add(item);
            }
        }
        tableView.refresh();
        return "I searched for an item";
    }

    public String refreshList(){
        filterSearch.clear();
        priceBox.clear();
        serialNumberBox.clear();
        nameOfItem.clear();
        tableView.setItems(ItemList);
        tableView.refresh();
        return "The table has been refreshed.";
    }

    public String saveList() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        System.out.println("I work for saving");
        Window stage = tableView.getScene().getWindow();
        fileChooser.setTitle("Save Menu");
        fileChooser.setInitialFileName("myList");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", "*.json"),
                new FileChooser.ExtensionFilter(".tsv", "*.tsv"),
                new FileChooser.ExtensionFilter(".html", "*.html"));
        File file = fileChooser.showSaveDialog(stage);
        if(file == null){
            return "A file was not selected";
        }
        fileChooser.setInitialDirectory(file.getParentFile());

        if(file.toString().endsWith(".json")) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                Gson gson = new Gson();

                gson.toJson(tableView.getItems(), writer);

                writer.close();
            } catch (IOException e) {
                System.out.println("Saving json doesn't work");
            }
            return "I saved a json";
        }

        if(file.toString().endsWith(".tsv")){
            //Saves it as TSV
            PrintWriter writer = new PrintWriter(file);

            for (Items item : ItemList) {
                String text = item.getPrice() + "\t" + item.getSerialNumber() + "\t" + item.getItemName() + "\n";

                writer.write(text);
            }
            writer.close();
            return "I saved a tsv";
        }

        if(file.toString().endsWith(".html")){
            //Saves it as HTML
            PrintWriter writer = new PrintWriter(file);
            writer.write("""
                    <!DOCTYPE html>
                    <html>
                    <head>
                    <style>
                    table {
                      font-family: arial, sans-serif;
                      border-collapse: collapse;
                      width: 100%;
                    }

                    td, th {
                      border: 1px solid #dddddd;
                      text-align: left;
                      padding: 8px;
                    }

                    tr:nth-child(even) {
                      background-color: #dddddd;
                    }
                    </style>
                    </head>
                    <body>

                    <h2>Item List</h2>

                    <table>
                      <tr>
                        <th>Price</th>
                        <th>Serial Number</th>
                        <th>Item Name</th>
                      </tr>
                    """);
            for(Items item : ItemList){
                writer.write("  <tr>\n" + "\t<td>" + item.getPrice() + "</td>\n" +
                        "\t<td>" + item.getSerialNumber() + "</td>\n" +
                        "\t<td>" + item.getItemName() + "</td>\n" + "</tr>\n");
            }
            writer.write("""
                    </table>

                    </body>
                    </html>""");
            writer.close();
            return "I saved a html";
        }

        tableView.refresh();

        return "I saved a list";
    }

    public String loadList() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        System.out.println("I work for loading");
        Window stage = tableView.getScene().getWindow();
        fileChooser.setTitle("Load Menu");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", "*.json"),
                new FileChooser.ExtensionFilter(".tsv", "*.tsv"),
                new FileChooser.ExtensionFilter(".html", "*.html"));

        ItemList.clear();
        File file = fileChooser.showOpenDialog(stage);
        if(file == null){
            return "A file was not selected";
        }
        fileChooser.setInitialDirectory(file.getParentFile());

        if(file.toString().endsWith(".json")){
            Gson gson = new Gson();
            try {
                Reader reader = new FileReader(file);
                Items[] result = gson.fromJson(reader, Items[].class);

                for (Items x : result)
                    ItemList.add(x);

            } catch (IOException e) {
                System.out.println("Loading json doesn't work");
            }
            return "I loaded a json";
        }

        if(file.toString().endsWith(".tsv")){
            //Loads a TSV
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while((text = reader.readLine()) != null){
                String[] textList = text.split("\t");
                //Price, SN, Name
                ItemVerifier.checkPrice(textList[0]);
                ItemVerifier.checkSN(textList[1]);
                ItemVerifier.checkName(textList[2]);

                Items item = new Items();
                item.setPrice(textList[0]);
                item.setSerialNumber(textList[1]);
                item.setItemName(textList[2]);

                ItemList.add(item);
            }
            return "I loaded a tsv";
        }

        if(file.toString().endsWith(".html")){
            //Loads a HTML
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder textBuild = new StringBuilder();
            String text;
            while((text = reader.readLine()) != null){
                if(text.contains("<td>")){
                    //text = text.replace(" ", "");
                    text = text.replace("<td>", "");
                    text = text.replace("</td>", "");
                    text = text.replace("\n", ",");
                    textBuild.append(text).append("\n");
                }

            }
            reader.close();

                String[] info = textBuild.toString().split(",");
                System.out.println(textBuild);
                System.out.println(Arrays.toString(info));

                ItemVerifier.checkPrice(info[0]);
                ItemVerifier.checkSN(info[1]);
                ItemVerifier.checkName(info[2]);
                Items item = new Items();
                item.setPrice(info[0]);
                item.setSerialNumber(info[1]);
                item.setItemName(info[2]);
                ItemList.add(item);

            return "I loaded a html";
        }

        return "I loaded a list";
    }
}