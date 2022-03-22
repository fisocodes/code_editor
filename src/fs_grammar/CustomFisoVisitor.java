package fs_grammar;


import java.util.HashMap;

import fs_grammar.FisoParser.AddSubExpContext;
import fs_grammar.FisoParser.AssignmentContext;
import fs_grammar.FisoParser.CondCondContext;
import fs_grammar.FisoParser.DeclarationContext;
import fs_grammar.FisoParser.ElseBracesContext;
import fs_grammar.FisoParser.ElseNoBracesContext;
import fs_grammar.FisoParser.ElseifBracesContext;
import fs_grammar.FisoParser.ElseifNoBracesContext;
import fs_grammar.FisoParser.ExpCondContext;
import fs_grammar.FisoParser.FalseCondContext;
import fs_grammar.FisoParser.IdExpContext;
import fs_grammar.FisoParser.IfBracesContext;
import fs_grammar.FisoParser.IfNoBracesContext;
import fs_grammar.FisoParser.IfelseContext;
import fs_grammar.FisoParser.MulDivExpContext;
import fs_grammar.FisoParser.ParenCondContext;
import fs_grammar.FisoParser.ParenthesisExpContext;
import fs_grammar.FisoParser.PrintFContext;
import fs_grammar.FisoParser.ProgramContext;
import fs_grammar.FisoParser.StatementContext;
import fs_grammar.FisoParser.TrueCondContext;
import fs_grammar.FisoParser.ValueExpContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CustomFisoVisitor extends FisoBaseVisitor<Object>{

    private VBox console;
    private HashMap<String, Object> variables = new HashMap<String, Object>();

    public CustomFisoVisitor(VBox console){
        this.console = console;
    }

    @Override
    public Object visitProgram(FisoParser.ProgramContext ctx)
    { 
        for (FisoParser.DeclarationContext declaration : ctx.declaration()) {
            variables.put(declaration.ID().getText(), null);
        }

        for (FisoParser.AssignmentContext assignment : ctx.assignment()) {
            setVariable(assignment);
        }

        visit(ctx.function(0));

        return null;
    }

    @Override
    public String visitPrintF(FisoParser.PrintFContext ctx)
    {
        Label line = new Label("> " + visit(ctx.expression()).toString());
        console.getChildren().add(line);

        return visit(ctx.expression()).toString();
    }

    @Override
    public Object visitDeclaration(FisoParser.DeclarationContext ctx)
    {
        variables.put(ctx.ID().getText(), null);
        return null;
    }

    @Override
    public Number visitAssignment(FisoParser.AssignmentContext ctx)
    {
        setVariable(ctx);

        return null;
    }

    @Override
    public Number visitMulDivExp(FisoParser.MulDivExpContext ctx)
    {
        if(visit(ctx.expression(0)) instanceof Double && visit(ctx.expression(1)) instanceof Double){
            if(ctx.operator.getType() == FisoParser.MUL)
                return  ((Number) visit(ctx.expression(0))).doubleValue() * ((Number) visit(ctx.expression(1))).doubleValue();
            return ((Number) visit(ctx.expression(0))).doubleValue() / ((Number) visit(ctx.expression(1))).doubleValue();
        }
        else if(visit(ctx.expression(0)) instanceof Double && visit(ctx.expression(1)) instanceof Integer)
        {
            if(ctx.operator.getType() == FisoParser.MUL)
                return  ((Number) visit(ctx.expression(0))).doubleValue() * ((Number) visit(ctx.expression(1))).intValue();
            return ((Number) visit(ctx.expression(0))).doubleValue() / ((Number) visit(ctx.expression(1))).intValue();
        }
        else if(visit(ctx.expression(0)) instanceof Integer && visit(ctx.expression(1)) instanceof Double)
        {
            if(ctx.operator.getType() == FisoParser.MUL)
                return  ((Number) visit(ctx.expression(0))).intValue() * ((Number) visit(ctx.expression(1))).doubleValue();
            return ((Number) visit(ctx.expression(0))).intValue() / ((Number) visit(ctx.expression(1))).doubleValue();
        }
        else{
            if(ctx.operator.getType() == FisoParser.MUL)
                return  ((Number) visit(ctx.expression(0))).intValue() * ((Number) visit(ctx.expression(1))).intValue();
            return ((Number) visit(ctx.expression(0))).intValue() / ((Number) visit(ctx.expression(1))).intValue();
        }
    }

    @Override
    public Number visitAddSubExp(FisoParser.AddSubExpContext ctx)
    {
        if(visit(ctx.expression(0)) instanceof Double && visit(ctx.expression(1)) instanceof Double){
            if(ctx.operator.getType() == FisoParser.ADD)
                return  ((Number) visit(ctx.expression(0))).doubleValue() + ((Number) visit(ctx.expression(1))).doubleValue();
            return ((Number) visit(ctx.expression(0))).doubleValue() - ((Number) visit(ctx.expression(1))).doubleValue();
        }
        else if(visit(ctx.expression(0)) instanceof Double && visit(ctx.expression(1)) instanceof Integer)
        {
            if(ctx.operator.getType() == FisoParser.ADD)
                return  ((Number) visit(ctx.expression(0))).doubleValue() + ((Number) visit(ctx.expression(1))).intValue();
            return ((Number) visit(ctx.expression(0))).doubleValue() - ((Number) visit(ctx.expression(1))).intValue();
        }
        else if(visit(ctx.expression(0)) instanceof Integer && visit(ctx.expression(1)) instanceof Double)
        {
            if(ctx.operator.getType() == FisoParser.ADD)
                return  ((Number) visit(ctx.expression(0))).intValue() + ((Number) visit(ctx.expression(1))).doubleValue();
            return ((Number) visit(ctx.expression(0))).intValue() - ((Number) visit(ctx.expression(1))).doubleValue();
        }
        else{
            if(ctx.operator.getType() == FisoParser.ADD)
                return  ((Number) visit(ctx.expression(0))).intValue() + ((Number) visit(ctx.expression(1))).intValue();
            return ((Number) visit(ctx.expression(0))).intValue() - ((Number) visit(ctx.expression(1))).intValue();
        }
    }

    @Override
    public Number visitValueExp(FisoParser.ValueExpContext ctx)
    {
        return toNumber(ctx.VALUE().getText());
    }

    @Override
    public Number visitIdExp(FisoParser.IdExpContext ctx)
    {
        if(variables.containsKey(ctx.ID().getText()))
        {
            return toNumber(variables.get(ctx.ID().getText()).toString());
        }
        return 0;
    }

    @Override
    public Number visitParenthesisExp(FisoParser.ParenthesisExpContext ctx)
    {
        return toNumber(visit(ctx.expression()).toString());
    }

    public HashMap<String, Object> getVariables()
    {
        return variables;
    }

    private Number toNumber(String number)
    {
        try
        {
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e)
        {
            return Double.parseDouble(number);
        }
    }

    private Object setVariable(FisoParser.AssignmentContext assignment)
    {
        try{
            if(variables.containsKey(assignment.ID().getText())){

                Number value = toNumber(visit(assignment.expression()).toString());
                variables.put(assignment.ID().getText(), value);
                return null;
            }

            throw new Exception("ERROR: Variable '" + assignment.ID().getText() + "' is not declared.");
        }catch(Exception e)
        {
            console.getChildren().add(new Label(e.getMessage()));
        }

        return null;
    }

    @Override
    public Boolean visitTrueCond(FisoParser.TrueCondContext ctx) {
        return true;
    }

    @Override
    public Boolean visitFalseCond(FisoParser.FalseCondContext ctx)
    {
        return false;
    }

    @Override
    public Boolean visitExpCond(FisoParser.ExpCondContext ctx)
    {
        try{
            switch(ctx.lo().getStart().getText()){
                case ">":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) > Double.parseDouble(visit(ctx.expression(1)).toString());
                case "<":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) < Double.parseDouble(visit(ctx.expression(1)).toString());
                case ">=":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) >= Double.parseDouble(visit(ctx.expression(1)).toString());
                case "<=":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) <= Double.parseDouble(visit(ctx.expression(1)).toString());
                case "==":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) == Double.parseDouble(visit(ctx.expression(1)).toString());
                case "!=":
                    return Double.parseDouble(visit(ctx.expression(0)).toString()) != Double.parseDouble(visit(ctx.expression(1)).toString());
                default:
                    throw new Exception("No valid logic operation");
            }
        }
        catch(Exception e){
            console.getChildren().add(new Label(e.getMessage()));
        }
        return null;
    }

    @Override
    public Boolean visitParenCond(FisoParser.ParenCondContext ctx) 
    { 
        return (Boolean) visit(ctx.condition());
    }

    @Override
    public Boolean visitCondCond(FisoParser.CondCondContext ctx)
    {
        try{
            switch(ctx.lo().getStart().getText()){
                case "||":
                    return (Boolean) visit(ctx.getChild(0)) || (Boolean) visit(ctx.getChild(2));
                case "&&":
                    return (Boolean) visit(ctx.getChild(0)) && (Boolean) visit(ctx.getChild(2));
                default:
                    throw new Exception("No valid logic operation for '||' or '&&'.");
            }
        }catch(Exception e){
            console.getChildren().add(new Label(e.getMessage()));
        }

        return null;
    }

    @Override
    public Object visitIfelse(FisoParser.IfelseContext ctx) {
        
        for(int i = 0; i < ctx.children.size() - 1; i++){
            if((Boolean) visit(ctx.getChild(i))){
                return null;
            }
        }
        
        return visit(ctx.getChild(ctx.children.size() - 1)); 
    }

    @Override
    public Boolean visitIfNoBraces(FisoParser.IfNoBracesContext ctx)
    {
        if((Boolean) visit(ctx.condition())){
            if(ctx.statement() != null)
                visit(ctx.statement());
            return true;
        }

        return false;
    }

    @Override 
    public Boolean visitIfBraces(FisoParser.IfBracesContext ctx)
    {
        if((Boolean) visit(ctx.condition())){
            for (FisoParser.StatementContext statement : ctx.statement()) {
                visit(statement);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean visitElseifNoBraces(FisoParser.ElseifNoBracesContext ctx)
    {
        
        if((Boolean) visit(ctx.condition())){
            if(ctx.statement() != null)
                visit(ctx.statement());
            return true;
        }
        return false;
    }

    @Override
    public Boolean visitElseifBraces(FisoParser.ElseifBracesContext ctx)
    {
        if((Boolean) visit(ctx.condition())){
            for (FisoParser.StatementContext statement : ctx.statement()) {
                visit(statement);
            }
            return true;
        }
        return false; 
    }

    @Override
    public Object visitElseBraces(FisoParser.ElseBracesContext ctx)
    {
        for (FisoParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }
        return null;
    }

    @Override
    public Object visitElseNoBraces(FisoParser.ElseNoBracesContext ctx)
    { 
        if(ctx.statement() != null)
                visit(ctx.statement());
        return null;
    }
}
