package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ItemVerifier {
    public static String checkSN(String box) throws IOException {
        if (box.length() != 10 || box.equals("") ) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("SNHelpGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Serial Number Help Menu");
            stage.show();
            return "I opened the sn help menu";
        }
        for(Items item : ListofItemsController.ItemList){
            if(box.equals(item.getSerialNumber())){
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("SNHelpGUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Serial Number Help Menu");
                stage.show();
                return "I opened the sn help menu";
            }
        }
        return "The sn has been checked";
    }
    public static String checkPrice(String box) throws IOException {
        try {
            Double amount = Double.parseDouble(box);
            if (box.equals("") || box.equals(amount.toString())) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("PriceHelpGUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Price Help Menu");
                stage.show();
                return "I opened the price help menu";
            }
        } catch (NumberFormatException e){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("PriceHelpGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Price Help Menu");
            stage.show();
            return "I opened the price help menu";
        }
        return "The price has been checked";
    }
    public static String checkName(String box) throws IOException {
        if(box.length() < 2 || box.length() > 256){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("NameHelpGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Price Help Menu");
            stage.show();
            return "I opened the name help menu";
        }
        for(Items item : ListofItemsController.ItemList){
            if(box.equals(item.getItemName())){
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("NameHelpGUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Price Help Menu");
                stage.show();
                return "I opened the name help menu";
            }
        }
        return "The name has been checked";
    }
}
