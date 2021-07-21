package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemVerifier {
    public static String checkSN(TextField box) throws IOException {
        if (box.getText().length() != 10 || box.getText().equals("")) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("SNHelpGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Serial Number Help Menu");
            stage.show();
            return "I opened the sn help menu";
        }
        return "The sn has been checked";
    }
    public String checkPrice(TextField box) throws IOException {
        if (box.getText().length() != 10 || box.getText().equals("")) {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(ItemVerifier.class.getResource("PriceHelpGUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Price Help Menu");
            stage.show();
            return "I opened the sn help menu";
        }
        return "The price has been checked";
    }
}
