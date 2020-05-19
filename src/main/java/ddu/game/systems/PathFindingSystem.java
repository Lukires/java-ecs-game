package ddu.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import ddu.game.GameEngine;
import ddu.game.components.pathfinding.Graph;
import ddu.game.components.pathfinding.Node;
import ddu.game.components.pathfinding.Path;
import org.joml.Vector2d;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PathFindingSystem extends EntitySystem  {

    public PathFindingSystem(int priority) {
        super(priority);
    }
    Graph graph;
    GameEngine engine;

    public PathFindingSystem(GameEngine engine) {
        this.engine=engine;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

    }

    @Override
    public void update(float dt) {
        //
    }

    public Path findPath(Vector2d from, Vector2d to) {

        Node destination = new Node(xStep((int)to.x),yStep((int)to.y),-100000);
        Node start = new Node(xStep((int)to.x),yStep((int)to.y),0);
        graph = new Graph(engine,destination);

        visited=new float[10000][10000];
        for(float[] row : visited) {
            Arrays.fill(row, -1);
        }

        PriorityQueue<Path> paths = new PriorityQueue<Path>(new Path.PathComparator());
        Path startPath = new Path();
        startPath.addNode(start);
        paths.add(startPath);

        return AStarPathFinding(paths, destination);
    }


    float visited[][];
    public Path AStarPathFinding(PriorityQueue<Path> paths, Node destination) {
        Path path = paths.poll();

        if(path == null) {
            return null;
        }

        Node pathNode = path.getNodes().get(path.getNodes().size()-1);
        if (pathNode.getX() == destination.getX() && pathNode.getY() == destination.getY()) {
            return path;
        }

        float cost = path.getValue();

        for(Node node : graph.getNeighbors(pathNode)) {
            int x = node.getX();
            int y = node.getY();

            if (!(visited[x][y]<0)) {
                if(cost>=visited[x][y]) {
                    continue;
                }
            }
            visited[x][y] = cost;
            Path newPath = new Path(path.getNodes());
            newPath.addNode(node);
            paths.add(newPath);
        }

        return AStarPathFinding(paths, destination);

    }

    private int xStep(int x) {
        return x/16;
    }

    private int yStep(int y) {
        return y/16;
    }


}
