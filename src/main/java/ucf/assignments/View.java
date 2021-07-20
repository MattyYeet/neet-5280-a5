package ucf.assignments;

public enum View {
    START("StartGUI.fxml"),
    MAIN("ListofItemsGUI.fxml"),
    NEW_EDIT_ITEM("NewItemGUI.fxml"),
    NEW_ITEM_HELP("PriceHelpGUI.fxml"),
    SN_HELP("SNHelpGUI.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
