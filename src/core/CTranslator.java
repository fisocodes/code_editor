package core;

import java.io.File;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import c_grammar.CCustomVisitor;
import c_grammar.CLexer;
import c_grammar.CParser;
import grammar.ConsoleErrorListener;
import javafx.scene.layout.VBox;

public class CTranslator {

    private VBox console;
    private CharStream input;
    private CLexer lexer;
    private CommonTokenStream tokens;
    private CParser parser;
    private ParseTree tree;
    private CCustomVisitor visitor;

    public CTranslator(VBox console){
        this.console = console;
    }

    public String translateC(File file){

        try{
            input = CharStreams.fromFileName(file.getAbsolutePath());
            lexer = new CLexer(input);
            tokens = new CommonTokenStream(lexer);
            parser = new CParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new ConsoleErrorListener(console));
            tree = parser.program();
            visitor = new CCustomVisitor();
            return visitor.visit(tree);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    
    
    
}
