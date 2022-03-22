package grammar;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ConsoleErrorListener extends BaseErrorListener{

    private VBox console;

    public ConsoleErrorListener(VBox console){
        this.console = console;

    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        Label error = new Label("ERROR [" + line + "," + charPositionInLine + "]: " + msg);
        error.getStyleClass().add("error-message");
        console.getChildren().add(error);
    }

    public void printError(String message){
        Label error = new Label("ERROR: " + message);
        error.getStyleClass().add("error-message");
        console.getChildren().add(error);
    }
    
}
