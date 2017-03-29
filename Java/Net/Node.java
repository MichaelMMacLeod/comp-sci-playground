import java.util.ArrayList;

public class Node implements Connection {
    private ArrayList<Synapse> parents;
    private ArrayList<Synapse> children;

    double value;

    public Node() {
        parents = new ArrayList<>();
        children = new ArrayList<>();
    }

    public double getValue() {
        return value;
    }
    
    public void connectToChild(Node child) {
        Synapse connection = new Synapse(this, child, Math.random());
        child.connectToParent(connection);
        children.add(connection);
    }

    public void connectToParent(Synapse parent) {
        parents.add(parent);
    }

    @Override
    public void propagate(double value) {
        this.value += value;
        for (Synapse s : children)
            s.propagate(this.value);
    }
}