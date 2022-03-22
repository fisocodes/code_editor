// Generated from Fiso.g4 by ANTLR 4.9.2
package fs_grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FisoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FisoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FisoParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(FisoParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(FisoParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(FisoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(FisoParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FisoParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printF}
	 * labeled alternative in {@link FisoParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintF(FisoParser.PrintFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExp}
	 * labeled alternative in {@link FisoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExp(FisoParser.IdExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExp}
	 * labeled alternative in {@link FisoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExp(FisoParser.MulDivExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesisExp}
	 * labeled alternative in {@link FisoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExp(FisoParser.ParenthesisExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExp}
	 * labeled alternative in {@link FisoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExp(FisoParser.AddSubExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueExp}
	 * labeled alternative in {@link FisoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueExp(FisoParser.ValueExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#ifelse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelse(FisoParser.IfelseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifNoBraces}
	 * labeled alternative in {@link FisoParser#ifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNoBraces(FisoParser.IfNoBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifBraces}
	 * labeled alternative in {@link FisoParser#ifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBraces(FisoParser.IfBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elseNoBraces}
	 * labeled alternative in {@link FisoParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseNoBraces(FisoParser.ElseNoBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elseBraces}
	 * labeled alternative in {@link FisoParser#elseblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseBraces(FisoParser.ElseBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elseifNoBraces}
	 * labeled alternative in {@link FisoParser#elseifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseifNoBraces(FisoParser.ElseifNoBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code elseifBraces}
	 * labeled alternative in {@link FisoParser#elseifblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseifBraces(FisoParser.ElseifBracesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueCond}
	 * labeled alternative in {@link FisoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueCond(FisoParser.TrueCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseCond}
	 * labeled alternative in {@link FisoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseCond(FisoParser.FalseCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expCond}
	 * labeled alternative in {@link FisoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpCond(FisoParser.ExpCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condCond}
	 * labeled alternative in {@link FisoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondCond(FisoParser.CondCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenCond}
	 * labeled alternative in {@link FisoParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenCond(FisoParser.ParenCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link FisoParser#lo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLo(FisoParser.LoContext ctx);
}