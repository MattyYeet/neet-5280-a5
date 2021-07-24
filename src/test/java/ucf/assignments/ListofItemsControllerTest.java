package ucf.assignments;

import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ListofItemsControllerTest {

    @Test
    void makeNewItem() {
        Items item1 = new Items();
        TextField priceBoxTest = new TextField();
        TextField snBoxTest = new TextField();
        TextField nameBoxTest = new TextField();
        priceBoxTest.setText("9.00");
        snBoxTest.setText("1234567890");
        nameBoxTest.setText("reee");
    }

    @Test
    void editItem() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void searchItem() {
    }

    @Test
    void refreshList() {
    }

    @Test
    void saveListJSON() {
        File file = new File("test list.json");
        if(!file.toString().endsWith(".json")){
            fail();
        }
    }

    @Test
    void loadListJSON() {
        File file = new File("test list.json");
        if(!file.toString().endsWith(".json")){
            fail();
        }
    }

    @Test
    void saveListTSV() {
        File file = new File("test list.tsv");
        if(!file.toString().endsWith(".tsv")){
            fail();
        }
    }

    @Test
    void loadListTSV() {
        File file = new File("test list.tsv");
        if(!file.toString().endsWith(".tsv")){
            fail();
        }
    }

    @Test
    void saveListHTML() {
        File file = new File("test list.html");
        if(!file.toString().endsWith(".html")){
            fail();
        }
    }

    @Test
    void loadListHTML() {
        File file = new File("test list.html");
        if(!file.toString().endsWith(".html")){
            fail();
        }
    }
}