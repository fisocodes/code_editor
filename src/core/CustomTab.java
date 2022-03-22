package core;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.SVGPath;

public class CustomTab extends Tab{
    
    private boolean isSaved =  true;
    private File file;
    private String fileText;
    private TextArea textArea;
    private SVGPath dot;

    public CustomTab(File file){

        this.file = file;
        if(file != null){
            this.fileText = FileHandler.readFile(file);
        }
        setText(file != null ? file.getName(): "untitled");
        dot = new SVGPath();
        dot.setContent("M12 6c3.31 0 6 2.69 6 6s-2.69 6-6 6-6-2.69-6-6 2.69-6 6-6m0-2c-4.42 0-8 3.58-8 8s3.58 8 8 8 8-3.58 8-8-3.58-8-8-8z");
        dot.getStyleClass().add("unsaved-dot");
        
        textArea = new TextArea(fileText);
        textArea.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldString, String newString){
                isSaved = false;
                fileText = newString;
                setGraphic(dot);
            }
            
        });
        setContent(textArea);

        setOnCloseRequest(e -> {
            
            if(!isSaved){
                
                e.consume();
                Alert alert = new Alert(AlertType.WARNING, "Do you want to save changes?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                alert.showAndWait();

                if(alert.getResult() == ButtonType.YES)
                {
                    FileHandler.saveFile(file, fileText);
                    closeTab();
                }
                else if(alert.getResult() == ButtonType.NO)
                {
                    closeTab();
                }
            }
        });
    }

    private void closeTab(){
        getTabPane().getTabs().remove(this);
    }

    public File getFile(){
        return this.file;
    }

    public String getFileText(){
        return this.fileText;
    }

    public TextArea getTextArea(){
        return textArea;
    }

    public void save(){
        if(file != null){

            if(file.exists()){
                FileHandler.saveFile(file, fileText);
                isSaved = true;
                setGraphic(null);
            }else{
                file = CustomFileChooser.saveDialog(getTabPane().getScene().getWindow());
    
                if(file != null){
                    FileHandler.saveFile(file, fileText);
                    setText(file.getName());
                    isSaved = true;
                    setGraphic(null);
                }
            }
        }else{
            file = CustomFileChooser.saveDialog(getTabPane().getScene().getWindow());
    
                if(file != null){
                    FileHandler.saveFile(file, fileText);
                    setText(file.getName());
                    isSaved = true;
                    setGraphic(null);
                }
        }
    }
}
