package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Matthew Neet
 */

public class Items {
    public String itemName;
    public String serialNumber;
    public String price;

    public Items(){
        this.itemName = "";
        this.serialNumber = null;
        this.price = "";
    }

    public Items(String itemName, String serialNumber, String price){
        this.itemName = itemName;
        this.serialNumber = serialNumber;
        this.price = price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "{" + "description:" + itemName + "," + "dateDue:" + serialNumber + "," + "done:" + price + '}';
    }

    public int getValue() {
        return this.itemName.length();
    }
}
