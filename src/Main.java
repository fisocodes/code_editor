import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch( args);    
    }

    public void start(Stage mainStage) throws IOException{

        BorderPane root = FXMLLoader.load(getClass().getResource("./layout/main_layout.fxml"));
        mainStage.setTitle("Code Title");
        mainStage.setScene(new Scene(root, 800, 600));
        mainStage.show();
    }
}