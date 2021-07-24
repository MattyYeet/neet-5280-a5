package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ListofItemsControllerTest {

    @Test
    void makeNewItem() {
        ObservableList<Items> list = FXCollections.observableArrayList();
        Items item1 = new Items();
        String equals = "fail";
        list.add(item1);
        if(list.contains(item1)){
            equals = "pass";
        }
        assertEquals(equals, "pass");
    }

    @Test
    void editItem() {
        ObservableList<Items> list = FXCollections.observableArrayList();
        Items item1 = new Items();
        item1.setPrice("9");
        item1.setItemName("ahhh");
        item1.setSerialNumber("1234567890");
        String equals = "fail";
        item1.setSerialNumber("9876543210");
        list.add(item1);
        if(item1.getSerialNumber().equals("9876543210")){
            equals = "pass";
        }
        assertEquals(equals, "pass");
    }

    @Test
    void deleteItem() {
        ObservableList<Items> list = FXCollections.observableArrayList();
        Items item1 = new Items();
        String equals = "fail";
        list.add(item1);
        list.remove(item1);
        if(list.contains(item1)){
            equals = "pass";
        }
        assertEquals(equals, "fail");
    }

    @Test
    void searchItem() {
        Items item = new Items();
        item.setSerialNumber("1234567890");
        String search = "";
        String equals = "fail";
        search = item.getSerialNumber();
        if(search.equals(item.getSerialNumber())){
            equals = "pass";
        }
        assertEquals(equals, "pass");
    }

    @Test
    void refreshList() {
        ObservableList<Items> filteredList = FXCollections.observableArrayList();
        Items item = new Items();
        filteredList.add(item);
        filteredList.clear();
        String equals = "fail";
        if(filteredList.contains(item)){
            equals = "pass";
        }
        assertEquals(equals, "fail");
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