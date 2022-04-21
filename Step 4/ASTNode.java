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
        System.out.println("Elements: " + this.getElement());
    }

    public void addChild(ASTNode child){
        this.children.add(child);
    }

}
