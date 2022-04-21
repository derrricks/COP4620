import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class Driver{
    public static void main(String[] args) throws Exception {

		InputStream in = System.in; // The InputStream represents an input stream of bytes. Can be used to read data.
		ANTLRInputStream input = new ANTLRInputStream(in); // Vacuum all input from a Reader/InputStream and then treat it like a char[] buffer
		LittleLexer lexer = new LittleLexer(input); // this must match antlr_grammar.g4 file name, will use g4 grammar
		CommonTokenStream cts = new CommonTokenStream(lexer);
		LittleParser parser = new LittleParser(cts);

		
		ParseTree tree = parser.program();
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(new Listener(), tree);
		System.out.println();

		if(parser.getNumberOfSyntaxErrors() == 0){
			// System.out.println("Accepted");
		}
		else{
			System.out.println("Not accepted");
		}
    }
}