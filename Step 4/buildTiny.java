import java.util.ArrayList;

public class buildTiny {

    private static String checkFloat;
    static ArrayList<String> tiny = new ArrayList<>();

    public static void buildCode(ASTNode root, ASTNode tree){

        //root.displayTree();
        int register = 0;
    
        tiny.add(";tiny code");
        parseTreeForTiny(root,0);
        tiny.add("sys halt");

        for(int i = 0; i < tiny.size(); i++) {   
            System.out.println(tiny.get(i));
        }
    }

    public static void parseTreeForTiny(ASTNode root, int temp) {

        ASTNode current = root;
        int tmp = temp;

        if(current.getUse().equals("FLOAT")) {
            checkFloat = "FLOAT";
            tiny.add("var " + current.getElement());
        }else if(current.getUse().equals("INT")){
            tiny.add("var " + current.getElement());
        }else if(current.getUse().equals("str")){
            tiny.add("str " + current.getElement() + " " + current.getValue());
        }


        if(current.getUse().equals("Assign")){

            if(current.getValue() != null && !(current.getValue().contains("-")) && !(current.getValue().contains("+")) && !(current.getValue().contains("*")) && !(current.getValue().contains("/"))){

                tiny.add("move " + current.getValue() + " " + "r" +tmp);
                tiny.add("move " + "r" +tmp + " " + current.getElement());
            }

            if(current.getValue() != null && (current.getValue().contains(".") || (checkFloat != null && checkFloat.equals("FLOAT")))){ // float
                
                if(current.getValue().contains("*")){
                    
                    String[] splitString = current.getValue().split("\\*"); 
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("mulr " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("+")){

                    String[] splitString = current.getValue().split("\\+"); 
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("addr " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("-")){

                    String[] splitString = current.getValue().split("\\-"); 
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("subr " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("/")){

                    String[] splitString = current.getValue().split("\\/"); 
                    if(splitString[1].contains(".")){
                        //System.out.println(current.getElement());
                        tiny.add("move " + splitString[1] + " r" + tmp);
                        tmp = tmp + 1;
                        tiny.add("move " + splitString[0] + " r" + tmp);
                        tiny.add("divr " + "r" + --tmp + " r" + ++tmp);
                        tiny.add("move " + "r" + tmp + " " + current.getElement());
                    }else {
                        tiny.add("move " + splitString[0] + " r" + tmp);
                        tiny.add("divr " + splitString[1] + " r" + tmp);
                        tiny.add("move " + "r" + tmp + " " + splitString[1]);
                    }
                }

           }else{ // int
            
                if(current.getValue().contains("*")){
                    
                    String[] splitString = current.getValue().split("\\*"); 
                    for(int i = 0; i < splitString.length; i++){
                        splitString[i] = splitString[i].trim();
                    }
                    //System.out.println(splitString[0]);
                    //System.out.println(splitString[1]);
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("muli " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());
                
                }else if(current.getValue().contains("+")){

                    String[] splitString = current.getValue().split("\\+"); 
                    //System.out.println(splitString[0]);
                    //System.out.println(splitString[1]);
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("addi " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("-")){

                    String[] splitString = current.getValue().split("\\-"); 
                    //System.out.println(splitString[0]);
                    //System.out.println(splitString[1]);
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("subi " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());

                }else if(current.getValue().contains("/")){
                    String[] splitString = current.getValue().split("\\/"); 
                    //System.out.println(splitString[0]);
                    //System.out.println(splitString[1]);
                    tiny.add("move " + splitString[0] + " r" + tmp);
                    tiny.add("divi " + splitString[1] + " r" + tmp);
                    tiny.add("move " + "r" + tmp + " " + current.getElement());
                }
           }
            tmp++;
        }


        // for READ
        if(current.getUse().equals("READ")){
            String[] splitString = current.getValue().split(",");

            for(int i = 0; i < splitString.length; i++){
                tiny.add("sys readi " + splitString[i]);
            }
        }

        // For WRITE
       if(current.getUse().equals("WRITE")){

            String[] splitString = current.getValue().split(",");
            for(int i = 0; i < splitString.length; i++){

                //System.out.printf("|%s|\n",splitString[i]);
                if(checkFloat != null && checkFloat.equals("FLOAT") && !splitString[i].equals("newline")){
                    tiny.add("sys writer " + splitString[i]);
                }else if(splitString[i].equals("newline")){
                    tiny.add("sys writes " + splitString[i]);
                }else{
                    tiny.add("sys writei " + splitString[i]);
                }
            }
        }

       // Recursive Entry
       for (int i = 0; i < current.totalChildren(); i++) {
            //this.print();
            current = current.getChild(i);
            parseTreeForTiny(current, tmp);
        }
    }
}
