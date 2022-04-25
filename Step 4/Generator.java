import java.util.ArrayList;

public class Generator {
    static String checkString;
    static String checkFloat;
    static String checkInt;


    public static void generateCode(ASTNode root, ASTNode tree){

        //root.displayTree();
        int register = 0;
        ArrayList<String> code = new ArrayList<>();
    

        code.add(";IR code");
        parseTree(root, code, 0);
        code.add(";RET");
        code.add(";tiny code");
        for(int i = 0; i < code.size(); i++) {   
            System.out.println(code.get(i));
        }
    }
    

    public static void parseTree(ASTNode root, ArrayList<String> code, int temp) {

        ASTNode current = root;
        int tmp = temp;
        //System.out.println("Use: " + current.getUse() + "|Element: " + current.getElement() +  "|Value: " + current.getValue());
        
        if(current.getElement() != null && current.getElement().equals("main")){
            code.add(";LABEL " + current.getElement());    
            code.add(";LINK");    
        }


        if(current.getUse().equals("FLOAT"))
        {
            checkFloat = "FLOAT";
        }
        
       if(current.getUse().equals("Assign")){
           ++tmp;

           if(current.getValue() != null && !(current.getValue().contains("-")) && !(current.getValue().contains("+")) && !(current.getValue().contains("*")) && !(current.getValue().contains("/"))){
                
                if(current.getValue().contains(".")){
                    code.add(";STOREF " + current.getValue() + " $T" +tmp);
                    code.add(";STOREF $T" + tmp + " " + current.getElement());
                    
                }else{

                    code.add(";STOREI " + current.getValue() + " $T" +tmp);
                    code.add(";STOREI $T" + tmp + " " + current.getElement());
                }
            }

           if(current.getValue() != null && (current.getValue().contains(".") || (checkFloat != null && checkFloat.equals("FLOAT")))){ // float
                
                if(current.getValue().contains("*")){

                }else if(current.getValue().contains("+")){
                    
                    String[] splitString = current.getValue().split("\\+"); 
                    code.add(";ADDF " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                    code.add(";STOREF $T" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("-")){
               
                }else if(current.getValue().contains("/")){

                    String[] splitString = current.getValue().split("\\/"); 
                    if(splitString[1].contains(".")){
                        //System.out.println(current.getElement());
                        code.add(";STOREF " + splitString[1] + " $T" + tmp);
                        code.add(";DIVF " + splitString[0] + " $T" + tmp + " $T" + ++tmp);
                        code.add(";STOREF $T" + tmp + " " + current.getElement());

                    }else {
                        code.add(";DIVF " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                        code.add(";STOREF $T" + tmp + " " + current.getElement());
                    }
                }

           }else{ // int
            
                if(current.getValue().contains("*")){
                    
                    if(current.getValue().contains("(")){

                        current.value = current.getValue().replace("(", " ");
                        current.value = current.getValue().replace(")", " ");
                        current.value.trim();
                        //current.print();
                        String[] splitString = current.getValue().split("\\*"); // "*" is reserved for regex chars, cant use
                        code.add(";MULTI " + splitString[0] + " " + splitString[1] + "$T" +tmp);
                        code.add(";STOREI $T" + tmp + " " + current.getElement());

                    }else{

                        String[] splitString = current.getValue().split("\\*"); 
                        //System.out.println(splitString[0]);
                        //System.out.println(splitString[1]);
                        code.add(";MULTI " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                        code.add(";STOREI $T" + tmp + " " + current.getElement());
                    }

                }else if(current.getValue().contains("+")){

                    String[] splitString = current.getValue().split("\\+"); 
                    code.add(";ADDI " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                    code.add(";STOREI $T" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("-")){

                    String[] splitString = current.getValue().split("\\-"); 
                    code.add(";SUBI " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                    code.add(";STOREI $T" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("/")){
                    String[] splitString = current.getValue().split("\\/"); 
                    code.add(";DIVI " + splitString[0] + " " + splitString[1] + " $T" +tmp);
                    code.add(";STOREI $T" + tmp + " " + current.getElement());
                }
           }
       }


       // For READ
       if(current.getUse().equals("READ")){
            String[] splitString = current.getValue().split(",");

            for(int i = 0; i < splitString.length; i++){
                code.add(";READI " + splitString[i]);
            }
       }


       // For WRITE
       if(current.getUse().equals("WRITE")){

            String[] splitString = current.getValue().split(",");

            for(int i = 0; i < splitString.length; i++){

                //System.out.printf("|%s|\n",splitString[i]);
                if(checkFloat != null && checkFloat.equals("FLOAT") && !splitString[i].equals("newline")){
                    code.add(";WRITEF " + splitString[i]);
                }else if(splitString[i].equals("newline")){
                    code.add(";WRITES " + splitString[i]);
                }else{
                    code.add(";WRITEI " + splitString[i]);
                }
            }
       }

       // Recursive Entry
       for (int i = 0; i < current.totalChildren(); i++) {
            //this.print();
            current = current.getChild(i);
            parseTree(current, code, tmp);
        }
    }
}
