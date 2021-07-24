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
        //Checks if the SN is viable
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

        //Checks if the SN already exits
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
            //Makes a value that is the price converted to a double
            Double amount = Double.parseDouble(box);

            //Checks if the double and what was entered are the same
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
            //if it cant be converted to a number aka not one
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
        //Checks if the name is long enough
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

        //Checks if the name already exists
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
