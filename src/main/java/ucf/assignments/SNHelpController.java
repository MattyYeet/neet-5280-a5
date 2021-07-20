package ucf.assignments;

import java.io.IOException;

public class SNHelpController {
    public String ok() throws IOException {
        ViewChanger.newStage.close();
        ViewChanger.changeTo(View.NEW_EDIT_ITEM);
        return "The sn help menu was closed";
    }
}
