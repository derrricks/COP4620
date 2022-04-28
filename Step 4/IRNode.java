import java.util.ArrayList;

import javax.swing.RootPaneContainer;
public class IRNode {

    String opcode;
    String firstOp;
    String secondOp;
    String result;
    ArrayList<IRNode> children = new ArrayList<>();

    public IRNode(String opcode, String firstOp, String secondOp, String result){
        this.opcode = opcode;
        this.firstOp = firstOp;
        this.secondOp = secondOp;
        this.result = result;
    }

    public String getOpcode(){
        return opcode;
    }

    public String getFirstOp(){
        return firstOp;
    }

    public String getSecondOp(){
        return secondOp;
    }

    public String getResult(){
        return result;
    }

    public void print() {
        System.out.println("Opcode: " + this.getOpcode() + "|First OP: " + this.getFirstOp() +  "|Second Op: " + this.getSecondOp() + "Result: " + this.getResult());
    }

    public void addChild(IRNode child){
        this.children.add(child);
    }

    public int totalChildren(){
        return children.size();
    }

    public IRNode getChild(int index){
        return children.get(index);
    }


    public static void displayTreeIR(IRNode root) {
        IRNode current = root;
        System.out.println("Opcode: " + current.getOpcode() + "|First OP: " + current.getFirstOp() +  "|Second Op: " + current.getSecondOp() + "Result: " + current.getResult());
        for (int i = 0; i < current.totalChildren(); i++) {
            //this.print();
            current = current.getChild(i);
            displayTreeIR(current);
        }
    }
}
