package ddu.game.pathfinding;

import java.util.ArrayList;
import java.util.Comparator;

public class Path {

    ArrayList<Node> nodes = new ArrayList<Node>();
    public Path() {

    }

    public Path(ArrayList<Node> nodes) {
        this.nodes=nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>(nodes);
    }

    public float getValue() {
        float cost = 0;
        for(Node node : nodes) {
            cost+=node.getCost();
        }
        return cost;
    }

    public static class PathComparator implements Comparator<Path> {
        public int compare(Path to, Path from) {
            return to.getValue() < from.getValue()? -1: to.getValue() == from.getValue()?0:1;
        }
    }

}
