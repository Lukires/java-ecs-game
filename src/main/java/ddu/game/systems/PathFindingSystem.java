package ddu.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.*;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.input.InputSystem;
import ddu.game.pathfinding.Graph;
import ddu.game.pathfinding.Node;
import ddu.game.pathfinding.Path;
import ddu.game.unit.Unit;
import org.joml.Vector2d;

import javax.swing.*;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PathFindingSystem extends EntitySystem  {
    Graph graph;
    GameEngine engine;

    private final ComponentMapper<SelectableComponent> selectableMapper  = ComponentMapper.getFor(SelectableComponent.class);
    private final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private final ComponentMapper<ActionComponent> actionMapper = ComponentMapper.getFor(ActionComponent.class);
    private final ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);
    private final ComponentMapper<UnitComponent> unitMapper = ComponentMapper.getFor(UnitComponent.class);
    private static final Family family = Family.all(SelectableComponent.class, PositionComponent.class, VelocityComponent.class, ActionComponent.class, AnimationComponent.class, UnitComponent.class).get();
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);


    ImmutableArray<Entity> entities;

    static double SPEED = 16.0;
    static double CLOSE = 0.1;

    public PathFindingSystem(GameEngine engine) {
        this.engine=engine;
    }

    @Override
    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(family);
        super.addedToEngine(engine);
    }

    @Override
    public void update(float dt) {
        // Check if we right clicked
        if (engine.inputSystem.rightPressed) {
            // Pathfind selected entities

            for(Entity entity : entities) {
                if (selectableMapper.get(entity).selected) {
                    // Calculate path
                    Path path = findPath(
                        positionMapper.get(entity).position,
                        InputSystem.mouseToGameCoord(engine, (float)engine.inputSystem.mousePos.x, (float)engine.inputSystem.mousePos.y)
                    );

                    ActionComponent actionComponent = actionMapper.get(entity);
                    actionComponent.reset();

                    for(Node node : path.getNodes()) {
                        actionComponent.actions.add(new Vector2d(node.getX()*16, node.getY()*16));
                    }
                }
            }
        }

        // Update velocities for entities
        for(Entity entity : entities) {
            ActionComponent actionComponent = actionMapper.get(entity);

            while (!actionComponent.actions.isEmpty()) {
                Vector2d target = actionComponent.actions.get(0);
                Vector2d position = positionMapper.get(entity).position;

                // Check if we're close to next target
                if (position.distance(target) < CLOSE) {
                    // Action complete, remove it from queue
                    actionComponent.actions.remove(0);
                    positionMapper.get(entity).position = target;

                    // Reset animation and velocity
                    Vector2d velocity = velocityMapper.get(entity).velocity;
                    Unit unit = unitMapper.get(entity).unit;
                    AnimationComponent animationComponent = animationMapper.get(entity);
                    if (velocity.x > 0) {
                        animationComponent.animation = unit.rightStanding;
                    } else {
                        animationComponent.animation = unit.leftStanding;
                    }
                    velocity.zero();
                    // Restart while loop (it does check if actions is empty again)
                    continue;
                }

                Vector2d velocity = new Vector2d(target.x, target.y);
                velocity.sub(position);
                velocity.normalize(SPEED * dt);

                velocityMapper.get(entity).velocity = velocity;
                Unit unit = unitMapper.get(entity).unit;
                AnimationComponent animationComponent = animationMapper.get(entity);
                if (velocity.x > 0) {
                    animationComponent.animation = unit.rightWalking;
                } else {
                    animationComponent.animation = unit.leftWalking;
                }
                break;
            }
        }
    }

    public Path findPath(Vector2d from, Vector2d to) {

        Node destination = new Node(xStep(to.x),yStep(to.y),-100000);
        Node start = new Node(xStep(from.x),yStep(from.y),0);

        System.out.println(start.getX() +" "+ start.getY());
        graph = new Graph(engine,destination);

        visited = new HashMap<Integer, HashMap<Integer, Float>>();

        PriorityQueue<Path> paths = new PriorityQueue<Path>(new Path.PathComparator());
        Path startPath = new Path();
        startPath.addNode(start);
        paths.add(startPath);

        for(Entity entity : engine.getEntitiesFor(Family.all(CollisionComponent.class).get())) {
            if (collisionMapper.get(entity).getHitbox().contains((float)to.x, (float)to.y)) {
                return startPath;
            }
        }

        Path path = AStarPathFinding(paths, destination, 1000);
        if(path==null) {
            return startPath;
        }
        return path;
    }


    HashMap<Integer, HashMap<Integer, Float>> visited = new HashMap<Integer, HashMap<Integer, Float>>();

    private Path AStarPathFinding(PriorityQueue<Path> paths, Node destination, int depth) {
        Path path = paths.poll();

        if(depth <= 0) {
            return null;
        }

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

            if(visited.containsKey(x)) {
                if(visited.containsKey(y)) {
                    if(visited.get(x).get(y)!=null) {
                        if (cost>=visited.get(x).get(y)) {
                            continue;
                        }
                    }
                }
            }

            HashMap<Integer, Float> xvisited = visited.containsKey(x)?visited.get(x):new HashMap<Integer, Float>();
            xvisited.put(y, cost);
            visited.put(x,xvisited);

            Path newPath = new Path(path.getNodes());
            newPath.addNode(node);
            paths.add(newPath);
        }

        return AStarPathFinding(paths, destination, --depth);
    }

    private int xStep(double x) {
        return (int)Math.round(x)/16;
    }

    private int yStep(double y) {
        return (int)Math.round(y)/16;
    }
}
