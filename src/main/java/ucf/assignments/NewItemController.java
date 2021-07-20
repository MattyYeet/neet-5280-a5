package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Formatter;

public class NewItemController {
    @FXML public static TextField nameOfItem;
    @FXML public static TextField serialNumberBox;
    @FXML public static TextField priceBox;

    public String saveButton() throws IOException {
        /*if(nameOfItem.getText().length() < 2 || nameOfItem.getText().equals("")){
            ViewChanger.newWindow(View.NEW_ITEM_HELP);
            return "I opened the name help menu";
        }*/

        Double amount = Double.parseDouble(priceBox.getText());

        Items item = new Items();
        item.setItemName(nameOfItem.getText());
        checkSN();
        item.setSerialNumber(serialNumberBox.getText());
        Formatter fmt = new Formatter();
        fmt.format("%.2f", amount);
        item.setPrice("$" + fmt);
        ListofItemsController.ItemList.add(item);

        ViewChanger.stage.close();
        ViewChanger.newWindow(View.MAIN);

        return "I added an item";
    }
    public String checkSN() throws IOException {
        Items item = ListofItemsController.tableView.getSelectionModel().getSelectedItem();

        if(serialNumberBox.getText().length() != 10 || item.getSerialNumber().length() != 10){
            ViewChanger.newWindow(View.SN_HELP);
            return "I opened the sn help menu";
        }
        return "The sn is good";
    }
}
