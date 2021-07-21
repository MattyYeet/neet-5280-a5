package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ItemOrganizerApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            String path = "src/resources/ucf/assignments/";
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListofItemsGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

             /*ViewChanger.setScene(scene);
            ViewChanger.changeTo(View.START);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
