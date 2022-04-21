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
	
        if(symtab.containsKey(name)){
            System.out.println("DECLARATION ERROR " + name);
            System.exit(0);
        }
            symbol.add(name); // record name
            symbol.add(type);
            symbol.add(value); // record null if not a string
            symtab.put(name, symbol); // enter into table
    }

    /**
     * Get table name (value) based on name passed though
     * Return null if does not exsist
     * @param tableName
     * @return values of table
     */
    public ArrayList<String> getName(String tableName){ // get table name, search for table by name
        if(search(tableName) == true){
            return symtab.get(tableName);
        }else{
            return null;
        }
    }

    /**
     * Search for table based on given name passed through
     * @param tableName
     * @return true/false
     */
    public boolean search(String tableName){
        return symtab.get(tableName) != null;
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

            String output = "name " + name + " type " + type;

            if(!symbol.get(2).equals("")){
                String value = symbol.get(2);
                output = output.concat(" value " + value);
            }
            System.out.println(output);
        }
        System.out.println();
    }
}
