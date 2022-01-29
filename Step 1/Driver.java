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
		
        InputStream in = System.in; // The InputStream represents an input stream of bytes. Can be used to read data.
	ANTLRInputStream input = new ANTLRInputStream(in); // reads any given file (input.micro files)
        Little lexer = new Little(input); // this must match antlr_grammar.g4 file name, will use g4 grammar
        Token token = lexer.nextToken();
        Vocabulary vocab = lexer.getVocabulary();

        while(token.getType() != token.EOF){ // while not end of file -> get token type
            System.out.println("Token Type: " + vocab.getSymbolicName(token.getType()) + "\nValue: " + token.getText());
            token = lexer.nextToken();
        }
    }
}

