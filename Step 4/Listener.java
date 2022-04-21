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
		System.out.println("Initialized");
	}

	@Override public void enterProgram(LittleParser.ProgramContext ctx) {
		//System.out.println(ctx.getText());
		this.s.push(new SymTab("GLOBAL"));
		this.cst = this.s.peek();
	}

	@Override public void exitProgram(LittleParser.ProgramContext ctx) {
		// System.out.println("GLOBAL");
		// this.cst.print();
		
		while(!s.isEmpty()){
			this.arLst.add(this.s.pop());
		}

		for(int i = arLst.size() - 1; i >= 0; i--){
			arLst.get(i).print();
		}
	}

	@Override public void enterString_decl(LittleParser.String_declContext ctx) {
		this.cst.insertToTable(ctx.id().IDENTIFIER().getText(), "STRING", ctx.str().STRINGLITERAL().getText());
	}

	@Override public void enterVar_decl(LittleParser.Var_declContext ctx) {
		String test = ctx.id_list().getText();
		//System.out.println("Test:: " + test);
		String arr[] = test.split(",");
		// int count = 0;
		// System.out.println("TESTING 2:: " + arr[arr.length - 1]);

		for(int i = 0; i < arr.length; i++){
			this.cst.insertToTable(arr[i], ctx.var_type().getText(), "");
		}
		// this.cst.insertToTable(ctx.id_list().id().IDENTIFIER().getText(), ctx.var_type().getText(), "");
	}
	
	@Override public void enterParam_decl(LittleParser.Param_declContext ctx) { 
		// System.out.println(ctx.getText());
		this.cst.insertToTable(ctx.id().getText(), ctx.var_type().getText(), "");
	}

	@Override public void enterFunc_decl(LittleParser.Func_declContext ctx) { 
		// System.out.println(ctx.id().getText());
		// this.cst.insertToTable(ctx.id().getText(), "", "");
		// this.cst.print();
		// System.out.println(ctx.getText());
		this.s.push(new SymTab(ctx.id().getText()));
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
}