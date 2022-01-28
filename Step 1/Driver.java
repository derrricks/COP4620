/**
 * COP 4620 - Construction of Language Translators (Compilers)
 * Step 1
 * @author <Derrick Palma & Bradley Crutchfield>
 * @version <January 30, 2022>
 */
import org.antlr.v4.runtime.*;
import java.io.*;

public class Driver {
    public static void main(String[] args) throws Exception {
       
        //System.out.println("Parsing: " + args[0]); // Parsing: (name of file being parsed.micro)

        InputStream in = System.in;
        ANTLRInputStream input = new ANTLRInputStream(in);
        Little lexer = new Little(input); // this must match antlr_grammar.g4 file name
        Token token = lexer.nextToken();
	    Vocabulary token2 = lexer.getVocabulary();

        while(token.getType() != token.EOF){ // while not end of .g4 file -> get token type
            //System.out.println("\t" + getTokenType(token.getType()) + "\t\t" + token.getText());
	        System.out.println("Token Type: " + token2.getSymbolicName(token.getType()) + "\nValue: " + token.getText());
            token = lexer.nextToken();
        }
    }
}

