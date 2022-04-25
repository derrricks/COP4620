import java.util.ArrayList;

import javax.swing.RootPaneContainer;
public class ASTNode {

    String use;
    String element;
    String value;
    ArrayList<ASTNode> children = new ArrayList<>();

    public ASTNode(String use, String element, String value){
        this.use = use;
        this.element = element;
        this.value = value;
    }

    public String getUse(){
        return use;
    }

    public String getElement(){
        return element;
    }

    public String getValue(){
        return value;
    }

    public void print() {
        System.out.println("Use: " + this.getUse() + "|Element: " + this.getElement() +  "|Value: " + this.getValue());
    }

    public void addChild(ASTNode child){
        this.children.add(child);
    }

    public int totalChildren(){
        return children.size();
    }

    public ASTNode getChild(int index){
        return children.get(index);
    }


    public static void displayTree(ASTNode root) {
        ASTNode current = root;
        System.out.println("Use: " + current.getUse() + "|Element: " + current.getElement() +  "|Value: " + current.getValue());
        for (int i = 0; i < current.totalChildren(); i++) {
            //this.print();
            current = current.getChild(i);
            displayTree(current);
        }
    }

    public static void searchTreeForUse(ASTNode current, ASTNode root) {

        ASTNode curr = root;
        System.out.println("Use: " + curr.getUse() + "|Element: " + curr.getElement() +  "|Value: " + curr.getValue());
        for (int i = 0; i < curr.totalChildren(); i++) {
            //this.print();

            //if()

            curr = curr.getChild(i);
            searchTreeForUse(current, curr);

        }
    }
}
