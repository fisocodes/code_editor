package grammar;
import java.util.HashMap;

import javafx.scene.layout.VBox;

public class MyVisitor extends CalculatorBaseVisitor<Integer>{
    HashMap<String, Integer> memory = new HashMap<String, Integer>();
    VBox console;

    public void setConsole(VBox console){
        this.console = console;

    }

    @Override
    public Integer visitAssignment(CalculatorParser.AssignmentContext ctx) {
        String ID = ctx.ID().getText();
        int value = visit(ctx.expression());
        memory.put(ID, value);
        return value; 
    }

    @Override
    public Integer visitPrint(CalculatorParser.PrintContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Integer visitInt(CalculatorParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Integer visitId(CalculatorParser.IdContext ctx) {
        if(memory.containsKey(ctx.ID().getText()))
        {
            return memory.get(ctx.ID().getText());
        }
        return 0;
    }

    @Override
    public Integer visitAddSub(CalculatorParser.AddSubContext ctx) {

        if(ctx.op.getType() == CalculatorParser.ADD)
            return visit(ctx.expression(0)) +  visit(ctx.expression(1));
        return visit(ctx.expression(0)) -  visit(ctx.expression(1));
    }

    @Override
    public Integer visitMulDiv(CalculatorParser.MulDivContext ctx) {
        if(ctx.op.getType() == CalculatorParser.MUL){
            return visit(ctx.expression(0)) *  visit(ctx.expression(1));
        }
        else{
            if(visit(ctx.expression(1)) != 0){
                return visit(ctx.expression(0)) /  visit(ctx.expression(1));
            }
            else{
                ConsoleErrorListener error = new ConsoleErrorListener(console);
                error.printError("ERROR: Can't divide by zero");
                return 0;
            }
        }
    }

    @Override public Integer visitParenthesis(CalculatorParser.ParenthesisContext ctx) {
        return visit(ctx.expression());
    }
}
