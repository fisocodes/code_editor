package core;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Window;

public class CustomFileChooser{

    public static File openDialog(Window parent){

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open source code");
        return chooser.showOpenDialog(parent);
    }

    public static File saveDialog(Window parent){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save source code");
        return chooser.showSaveDialog(parent);
    }
    
}
