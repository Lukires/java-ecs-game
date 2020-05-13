package ddu.game;

import com.badlogic.ashley.core.*;
import ddu.game.components.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.entities.Soldier;
import ddu.game.systems.MovementSystem;
import ddu.game.window.Window;

public class GameHandler extends PooledEngine {


    //ComponentMapper is O(1)
    //Compared to looping over all entities and checking their components which is O(logn)
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<DrawComponent>  drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

    //Families (Collections of components)
    private final static Family physicsFamily = Families.PHYSICS.getFamily();
    private Window window;


    private boolean visualize;
    public GameHandler(boolean visualize) {
        this.visualize=visualize;
    }

    public void init() {

        MovementSystem movementSystem = new MovementSystem();

        this.addSystem(movementSystem);

        //We use this.createEntity() as to avoid initializing new things.
        //for the sake of pooling
        this.addEntity(Soldier.convertEntity(this.createEntity(), this));

        if(this.visualize) {
            window = new Window();
            window.run();
        }


    }

    private void loop() {

        update(1);


    }
}
