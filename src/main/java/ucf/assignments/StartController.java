package ucf.assignments;

import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class StartController {

    public static Stage StartStage;
    private Stage stage = new Stage();
    String path = System.getProperty("user.dir") + "/Made_Lists";

    public StartController(){

    }

    public StartController(Stage stage) {
        this.stage = stage;
        StartStage = this.stage;
    }

    public String viewList() throws IOException {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListofItemsGUI.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            StartStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "I opened the main screen";
    }

    public String loadList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path));
        System.out.println("I work for loading");
        Window stage = ViewChanger.stage.getScene().getWindow();
        fileChooser.setTitle("Load Menu");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", "*.json"));

        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        //BufferedReader br = new BufferedReader(new FileReader(file.getParentFile()));

        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("Made_Lists/myList.json");
            Items[] result = gson.fromJson(reader, Items[].class);

            for(Items x : result)
                ListofItemsController.ItemList.add(x);

            ViewChanger.stage.close();
            ViewChanger.newWindow(View.MAIN);
        } catch (IOException e){
            System.out.println("I don't work");
        }
        return "I loaded a list from start";
    }

}
