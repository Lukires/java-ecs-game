package ddu.game.pathfinding;

public class Node {

    private int x, y;
    private float cost;
    public Node(int x, int y, float cost) {
        this.x=x;
        this.y=y;
        this.cost=cost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getCost() {
        return cost;
    }

}
