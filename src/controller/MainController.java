package controller;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import core.CTranslator;
import core.CustomFileChooser;
import core.CustomTab;
import core.FileHandler;
import fs_grammar.CustomFisoVisitor;
import fs_grammar.FisoLexer;
import fs_grammar.FisoParser;
import grammar.ConsoleErrorListener;
import javafx.collections.ListChangeListener.Change;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML TabPane tabPane;
    @FXML ScrollPane consoleContainer;
    @FXML VBox console;
    @FXML VBox center;
    @FXML HBox centerButtons;

    @FXML
    public void initialize(){

        center.getChildren().remove(tabPane);
        tabPane.getTabs().addListener((Change <? extends Tab> c) -> {
            if(tabPane.getTabs().size() == 0){
                center.getChildren().removeAll(tabPane, centerButtons, consoleContainer);
                center.getChildren().add(centerButtons);
                center.getChildren().add(consoleContainer);
            }
            else
            {
                center.getChildren().removeAll(tabPane, centerButtons, consoleContainer);
                center.getChildren().add(tabPane);
                center.getChildren().add(consoleContainer);
            }
        });

        console.heightProperty().addListener((c, pv, nv) -> {
            consoleContainer.setVvalue(1);
        });
    }

    @FXML
    private void handleNewMenuItem(){
        tabPane.getTabs().add(new CustomTab(null));
    }

    @FXML
    private void handleNewAsMenuItem(){
        tabPane.getTabs().add(new CustomTab(new File("untitled.txt")));
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).save();
    }

    @FXML
    private void handleOpenMenuItem(){
        File file = CustomFileChooser.openDialog(center.getScene().getWindow());

        if(file != null){
            tabPane.getTabs().add(new CustomTab(file));
            tabPane.getSelectionModel().selectLast();
        }
    }

    @FXML
    private void handleSaveMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).save();
    }

    @FXML
    private void handleSaveAsMenuItem(){
        System.out.println("Save as...");
    }

    @FXML
    private void handleExitMenuItem(){
        System.exit(0);
    }

    @FXML
    private void handleUndoMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().undo();
    }

    @FXML
    private void handleRedoMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().redo();
    }

    @FXML
    private void handleSelectAllMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().selectAll();
    }

    @FXML
    private void handleDeselectMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().deselect();
    }

    @FXML
    private void handleCutMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().cut();
    }

    @FXML
    private void handleCopyMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().copy();
    }

    @FXML
    private void handleCopyAllMenuItem(){
        CustomTab tab = (CustomTab) tabPane.getSelectionModel().getSelectedItem();
        tab.getTextArea().selectAll();
        tab.getTextArea().copy();
        tab.getTextArea().deselect();
    }

    @FXML
    private void handlePasteMenuItem(){
        ((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getTextArea().paste();
    }

    @FXML
    private void handleRunMenuItem(){
        if(tabPane.getSelectionModel().getSelectedItem() != null)
            run((CustomTab) tabPane.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleClearMenuItem(){
        console.getChildren().removeAll(console.getChildren());
    }

    @FXML
    private void handleSaveAndRunNewMenuItem(){
        CustomTab tab = (CustomTab)tabPane.getSelectionModel().getSelectedItem();

        if(tab != null){
            tab.save();;
            run(tab);
        }
    }

    @FXML
    private void handleGuideMenuItem(){
        System.out.println("Guide");
    }

    @FXML
    private void handleAboutMenuItem(){
        System.out.println("About");
    }

    @FXML
    private void handleTranslateButton(){
        CTranslator translator = new CTranslator(console);
        String translatedCode;
        if(tabPane.getSelectionModel().getSelectedItem() != null){
            if(((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getFile().exists()){
                translatedCode = translator.translateC(((CustomTab) tabPane.getSelectionModel().getSelectedItem()).getFile());
                System.out.println(translatedCode);


                File file = CustomFileChooser.saveDialog(tabPane.getScene().getWindow());
                FileHandler.saveFile(file, translatedCode);
                tabPane.getTabs().add(new CustomTab(file));
                tabPane.getSelectionModel().selectLast();
            }
        }

    }

    private void run(CustomTab tab){
        if(tab.getFile().exists()){
            try {
                CharStream input = CharStreams.fromFileName(tab.getFile().getAbsolutePath());
                FisoLexer lexer = new FisoLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                FisoParser parser = new FisoParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(new ConsoleErrorListener(console));
                ParseTree tree = parser.program();
        
                CustomFisoVisitor visitor = new CustomFisoVisitor(console);
                visitor.visit(tree);
    
                consoleContainer.setVvalue(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}