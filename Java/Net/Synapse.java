public class Synapse implements Connection {
    private Node from, to;
    private double weight;

    public Synapse(Node from, Node to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void propagate(double value) {
        to.propagate(weight * value);
    }
}