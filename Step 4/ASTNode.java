import java.util.ArrayList;
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

    public void displayTree() {
        System.out.println("Use: " + this.getUse() + "|Element: " + this.getElement() +  "|Value: " + this.getValue());
        for (int i = 0; i <= children.size() - 1; i++) {
            //this.print();
            this.getChild(i).displayTree();
        }
        
    }
}
