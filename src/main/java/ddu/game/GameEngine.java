package ddu.game;

import com.badlogic.ashley.core.*;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.entities.Soldier;
import ddu.game.systems.MovementSystem;
import ddu.game.window.RenderSystem;
import org.newdawn.slick.*;

public class GameEngine extends PooledEngine implements Runnable, Game {


    //ComponentMapper is O(1)
    //Compared to looping over all entities and checking their components which is O(logn)
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<DrawComponent>  drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

    //Families (Collections of components)
    private final static Family physicsFamily = Families.PHYSICS.getFamily();

    // JLWGL rendering and windowing
    private RenderSystem renderSystem;

    private boolean gameRunning = true;

    // Fixed time step variable
    public static final long frameRate = 60;
    public static final float interval = 1f / frameRate;
    public float accumulator = 0f;
    public long lastTime;

    private boolean visualize;
    public GameEngine(boolean visualize) {
        this.visualize=visualize;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        MovementSystem movementSystem = new MovementSystem(10);

        this.addSystem(movementSystem);

        //We use this.createEntity() as to avoid initializing new things.
        //for the sake of pooling
        this.addEntity(Soldier.convertEntity(this.createEntity(), this));
        renderSystem = new RenderSystem(this);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        update((float)i/1000f);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(visualize) {
            renderSystem.render(gameContainer, graphics);
        }
    }

    @Override
    public boolean closeRequested() {
        System.exit(0);
        return false;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void run() {
        System.out.print(1);

    }
}
