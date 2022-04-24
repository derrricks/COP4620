import java.util.ArrayList;

public class Generator {

    public static void generateCode(ASTNode root, ASTNode tree){

        //root.displayTree();
        int register = 0;
        ArrayList<String> code = new ArrayList<>();

        code.add(";IR code");
        parseTree(root, code, 0);

        for(int i = 0; i < code.size(); i++) {   
            System.out.println(code.get(i));
        }
        
    }

    
    
    public static ArrayList<String> parseTree(ASTNode root, ArrayList<String> code, int temp) {

        ASTNode current = root;
        int tmp = temp;
        //System.out.println("Use: " + current.getUse() + "|Element: " + current.getElement() +  "|Value: " + current.getValue());
        
        if(current.getElement() != null && current.getElement().equals("main")){
            code.add(";LABEL " + current.getElement());    
            code.add(";LINK");    
       }

       if(current.getUse().equals("Assign")){
           ++tmp;
           
           if(current.getValue()!= null && current.getValue().contains(".")){ // float


               code.add(";STOREF " + current.getValue() + " $T" +tmp);
               code.add(";STOREF $T" + tmp + " " + current.getElement());

           }else{ // int


               code.add(";STOREI " + current.getValue() + " $T" +tmp);
               code.add(";STOREI $T" + tmp + " " + current.getElement());
           }
       }


       // For READ
       if(current.getUse().equals("READ")){
            String[] splitString = current.getValue().split(",");

            for(int i = 0; i < splitString.length; i++){
                code.add(";READI " + splitString[i]);
            }
       }


       // Recursive Entry
       for (int i = 0; i < current.totalChildren(); i++) {
            //this.print();
            current = current.getChild(i);
            parseTree(current, code, tmp);
        }
        return code;
    }

    /*
    if(current.getValue() != null && current.value.contains("*")){
               
    }
    if(current.getValue() != null && current.value.contains("+")){
        
    }
    */

}
