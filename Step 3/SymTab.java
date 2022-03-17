/**
 *
 * Need to only worry about variable declaration related functions
 * •Your task in this step of the project is to construct symbol tables for
 * each scope (i.e., there are multiple scopes) in your program.
 *
 * •For each scope, construct a symbol table, then add entries to that
 * symbol table as you see declarations.
 *
 * •The declarations you have to handle are integer/ float declarations,
 * which should record the name and type of the variable, and string
 * declarations, which should additionally record the value of the string.
 *
 * https://www.tutorialcup.com/java/linkedhashmap-in-java.htm#:~:text=LinkedHashMap%20class%20in%20Java%20is%20a%20LinkedList%20implementation,iterate%20through%20all%20the%20nodes%20in%20the%20LinkedHashMap.
 * https://java2blog.com/print-arraylist-java/
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SymTab { // symbol table class used to represent each table as needed in step 3
    private String tableName; // name used for each table
    private LinkedHashMap<String, ArrayList<String>> symtab = new LinkedHashMap<>(); // will hold each name, type, and value as needed

    public SymTab(String tableName) { // Symbol Table constuctor, pass through name of table to call
        this.tableName = tableName; //  may be multiple tables generated
    }

    public void insertToTable(String name, String type, String value) { // method to insert into table
        ArrayList<String> symbol = new ArrayList<>(); // used to insert into symtab
        symbol.add(name); // record name
        symbol.add(type);
        symbol.add(value); // record null if not a string
        symtab.put(name, symbol); // enter into table
    }

    /**
     * At the end of the parsing phase, you should print out the symbols you
     * found. For each symbol table in your program:
     * Symbol table <scope_name>
     * name <var_name> type <type_name>
     * name <var_name> type <type_name> value <string_value>;
     * ...
     */
    public void print() { // print table
        System.out.println("Symbol table " + tableName);

        for (ArrayList<String> symbol : symtab.values()) { // iterate through symtab
            String name = symbol.get(0); // get name value for current symbol
            String type = symbol.get(1);

            String output = "name" + name + "type" + type;

            if(symbol.get(2) != null){
                String value = symbol.get(2);
                output = output.concat("value" + value);
            }
            System.out.println(output);
        }
        System.out.println();
    }
}