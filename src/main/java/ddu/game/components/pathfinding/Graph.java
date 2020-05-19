package ddu.game.components.pathfinding;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.collision.CollisionComponent;

import java.util.ArrayList;

public class Graph {

    GameEngine engine;
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ImmutableArray<Entity> entities;
    Node destination;

    public Graph(GameEngine engine, Node destination) {
        this.engine=engine;
        this.entities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
        this.destination=destination;
    }

    public ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<Node>();

        int x = node.getX();
        int y = node.getY();

        for (Entity entity : entities) {
            CollisionComponent collisionComponent = collisionMapper.get(entity);
            if(!collisionComponent.getHitbox().contains((x+1)*16, (y)*16)) {
                neighbors.add(new Node(x+1,y,(float)1+(float)Math.sqrt(Math.pow(x+1-destination.getX(),2)+Math.pow(y-destination.getY(),2))));
            }
            if(!collisionComponent.getHitbox().contains((x-1)*16, (y)*16)) {
                neighbors.add(new Node(x+1,y,(float)1+(float)Math.sqrt(Math.pow(x-1-destination.getX(),2)+Math.pow(y-destination.getY(),2))));
            }
            if(!collisionComponent.getHitbox().contains((x)*16, (y+1)*16)) {
                neighbors.add(new Node(x+1,y,(float)1+(float)Math.sqrt(Math.pow(x-destination.getX(),2)+Math.pow(y+1-destination.getY(),2))));
            }
            if(!collisionComponent.getHitbox().contains((x)*16, (y-1)*16)) {
                neighbors.add(new Node(x+1,y,(float)1+(float)Math.sqrt(Math.pow(x-destination.getX(),2)+Math.pow(y-1-destination.getY(),2))));
            }
        }
        return neighbors;
    }
}
