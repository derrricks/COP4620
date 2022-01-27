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

        System.out.println("Parsing: " + args[0]); // Parsing: (name of file being parsed.micro)

        FileInputStream fInStrm = new FileInputStream(new File(args[0]));
        ANTLRInputStream input = new ANTLRInputStream(fInStrm);
        Little lexer = new Little(input); // this must match antlr_grammar.g4 file name
        Token token = lexer.nextToken();

        while(token.getType() != Little.EOF){ // while not end of .g4 file -> get token type
            System.out.println("\t" + getTokenType(token.getType()) + "\t\t" + token.getText());
            token = lexer.nextToken();
        }
    }
    private static String getTokenType(int tokenType) {
        switch (tokenType) {
            case Little.IDENTIFIER:
                return "IDENTIFIER";
            case Little.INTLITERAL:
                return "INTLITERAL";
            case Little.FLOATLITERAL:
                return "FLOATLITERAL";
            case Little.STRINGLITERAL:
                return "STRINGLITERAL";
            case Little.COMMENT:
                return "COMMENT";
            case Little.KEYWORD:
                return "KEYWORD";
            case Little.OPERATORS: // OPERATOR is not recognized (throws error), OPERATORS is
                return "OPERATORS";
            default:
                return "OTHER";
        }
    }
}

