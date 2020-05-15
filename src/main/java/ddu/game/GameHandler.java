package ddu.game;

import com.badlogic.ashley.core.*;
import ddu.game.components.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.entities.Soldier;
import ddu.game.systems.MovementSystem;
import ddu.game.window.RenderSystem;
import ddu.game.window.Window;

public class GameHandler extends PooledEngine implements Runnable {


    //ComponentMapper is O(1)
    //Compared to looping over all entities and checking their components which is O(logn)
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<DrawComponent>  drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

    //Families (Collections of components)
    private final static Family physicsFamily = Families.PHYSICS.getFamily();

    // JLWGL rendering and windowing
    private Window window;
    private RenderSystem renderSystem;

    private Thread loopThread;
    private boolean gameRunning = true;

    // Fixed time step variable
    public static final long frameRate = 60;
    public static final float interval = 1f / frameRate;
    public float accumulator = 0f;
    public long lastTime;

    private boolean visualize;
    public GameHandler(boolean visualize) {
        this.visualize=visualize;
    }

    public void init() {

        MovementSystem movementSystem = new MovementSystem(10);

        this.addSystem(movementSystem);

        //We use this.createEntity() as to avoid initializing new things.
        //for the sake of pooling
        this.addEntity(Soldier.convertEntity(this.createEntity(), this));

        loop();
    }

    public Window getWindow() {
        return window;
    }

    private void loop() {
        loopThread = new Thread(this, "GAME_LOOP");
        loopThread.start();
    }


    @Override
    public void run() {

        if(this.visualize) {
            window = new Window();
            window.run();

            renderSystem = new RenderSystem(this);
            lastTime = System.nanoTime();
        }

        while(gameRunning) {
            // Accumulate time since last frame
            long now = System.nanoTime();
            accumulator += (now - lastTime)/1_000_000_000f;
            lastTime = now;

            // Advance simulation
            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            // Render
            renderSystem.render(interval);

            // TODO Check if we need to quit
        }
    }
}
