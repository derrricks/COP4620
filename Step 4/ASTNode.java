import java.util.ArrayList;
public class ASTNode {

    String element;
    ArrayList<ASTNode> children = new ArrayList<>();

    public ASTNode(String element){
        this.element = element;
    }

    public String getElement(){
        return element;
    }

    public void print() {
        System.out.println("Element: " + this.getElement());
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
        System.out.println("Element: " + this.getElement());
        for (int i = 0; i < children.size(); i++) {
            this.getChild(i).displayTree();
        }
    }


}
