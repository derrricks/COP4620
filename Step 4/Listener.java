import java.util.Stack;
import java.util.ArrayList;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Listener extends LittleBaseListener{
	public Stack<SymTab> s;
	SymTab cst;
	ArrayList<SymTab> arLst;
	int count = 0;

	ASTNode root;
	ASTNode ASTTree;


	public Listener(){
		this.s = new Stack<SymTab>();
		this.cst = null;
		arLst = new ArrayList<SymTab>();
		//System.out.println("Initialized");
	}

	// ASTNode(String use, String element, String value)
	public void addASTTreeNode(String use, String element, String value){
		ASTTree.addChild(new ASTNode(use, element, value));
		ASTTree = ASTTree.getChild(ASTTree.totalChildren() - 1); // keep track of last node added to tree
		root.addChild(new ASTNode(use, element, value));
	}


	@Override public void enterProgram(LittleParser.ProgramContext ctx) {
		String scope = "GLOBAL";
		// System.out.println(ctx.getText());
		this.s.push(new SymTab(scope));
		this.cst = this.s.peek();

		ASTTree = root = new ASTNode(scope, null, null);
		// root.print();
		// System.out.println(this.cst);
	}


	@Override public void exitProgram(LittleParser.ProgramContext ctx) {

		while(!s.isEmpty()){
			this.arLst.add(this.s.pop());
		}

		/*
		for(int i = arLst.size() - 1; i >= 0; i--){
			arLst.get(i).print();
		}
		*/
		//root.displayTree(root);
		Generator.generateCode(root, ASTTree);
		//System.out.println();
		buildTiny.buildCode(root, ASTTree);
	}

	@Override public void enterString_decl(LittleParser.String_declContext ctx) {
		//addASTTreeNode(ctx.str().STRINGLITERAL().getText());
		addASTTreeNode("str", ctx.id().IDENTIFIER().getText(), ctx.str().STRINGLITERAL().getText());
		this.cst.insertToTable(ctx.id().IDENTIFIER().getText(), "STRING", ctx.str().STRINGLITERAL().getText());
	}


	@Override public void enterVar_decl(LittleParser.Var_declContext ctx) {
		String test = ctx.id_list().getText();
		//System.out.println("Test:: " + test);
		String arr[] = test.split(",");
		// int count = 0;
		// System.out.println("TESTING 2:: " + arr[arr.length - 1]);

		for(int i = 0; i < arr.length; i++){
			//addASTTreeNode(arr[i]);
			addASTTreeNode(ctx.var_type().getText(), arr[i], null);
			//System.out.println(ctx.var_type().getText());
			this.cst.insertToTable(arr[i], ctx.var_type().getText(), "");
		}
		// this.cst.insertToTable(ctx.id_list().id().IDENTIFIER().getText(), ctx.var_type().getText(), "");
	}
	

	@Override public void enterParam_decl(LittleParser.Param_declContext ctx) { 
		this.cst.insertToTable(ctx.id().getText(), ctx.var_type().getText(), "");
	}


	@Override public void enterFunc_decl(LittleParser.Func_declContext ctx) { 

		// System.out.println("Printing Child 1: " +ctx.getChild(1).getText()); = 'VOID'
		// System.out.println("Printing Child 2: " +ctx.getChild(2).getText()); = 'main'
		addASTTreeNode(ctx.getChild(1).getText(), ctx.getChild(2).getText(), null);

		// System.out.println(ctx.id().getText());
		// System.out.println(ctx.getText());
		this.s.push(new SymTab(ctx.id().getText())); // = main 
		this.cst = this.s.peek();
	}


	@Override public void enterIf_stmt(LittleParser.If_stmtContext ctx){
		// System.out.println("IF :: " + ctx.getText());
		count++;
		this.s.push(new SymTab("BLOCK " + count));
		this.cst = this.s.peek();
	}


	@Override public void enterElse_part(LittleParser.Else_partContext ctx) { 
		// System.out.println("ELSE :: " + ctx.getText());
		if(!ctx.getText().equals("")){
			// System.out.println("YOU HOULDN'T BE HERE!");
			count++;
			this.s.push(new SymTab("BLOCK " + count));
			this.cst = this.s.peek();
		}
		// count++;
		// this.s.push(new SymTab("BLOCK " + count));
	}


	@Override public void enterWhile_stmt(LittleParser.While_stmtContext ctx) { 
		// System.out.println("WHILE :: " + ctx.getText());
		count++;
		this.s.push(new SymTab("BLOCK " + count));
		this.cst = this.s.peek();
	}

	@Override public void enterAssign_expr(LittleParser.Assign_exprContext ctx) {
		addASTTreeNode("Assign",ctx.getChild(0).getText(), ctx.getChild(2).getText());
		//System.out.println("Printing Assign_expr Child 0: " + ctx.getChild(0).getText());
		//System.out.println("Printing Assign_expr Child 1: " + ctx.getChild(1).getText());
		//System.out.println("Printing Assign_expr Child 2: " + ctx.getChild(2).getText());
	} 


	@Override public void enterRead_stmt(LittleParser.Read_stmtContext ctx) {
		addASTTreeNode(ctx.getChild(0).getText(), null, ctx.getChild(2).getText());
		//System.out.println("Printing Read 1: " + ctx.getChild(0).getText());
		//System.out.println("Printing Read 2: " + ctx.getChild(1).getText());
		//System.out.println("Printing Read 3: " + ctx.getChild(2).getText());
		//System.out.println("Printing Read 4: " + ctx.getChild(3).getText());
	 }

	@Override public void enterWrite_stmt(LittleParser.Write_stmtContext ctx) {
		addASTTreeNode(ctx.getChild(0).getText(), null, ctx.getChild(2).getText());
		//System.out.println("Printing Write 1: " + ctx.getChild(0).getText());
		//System.out.println("Printing Write 2: " + ctx.getChild(1).getText());
		//System.out.println("Printing Write 3: " + ctx.getChild(2).getText());
		//System.out.println("Printing Write 4: " + ctx.getChild(3).getText());
	 }
}