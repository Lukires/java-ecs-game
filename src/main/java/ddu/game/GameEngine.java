package ddu.game;

import com.badlogic.ashley.core.*;
import ddu.game.components.AnimationComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.input.InputSystem;
import ddu.game.input.Keys;
import ddu.game.systems.MovementSystem;
import ddu.game.unit.Unit;
import ddu.game.unit.UnitBuilder;
import ddu.game.window.Camera;
import ddu.game.window.RenderSystem;
import ddu.game.world.World;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class GameEngine extends PooledEngine implements Runnable, Game {


    //ComponentMapper is O(1)
    //Compared to looping over all entities and checking their components which is O(logn)
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<DrawComponent>  drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);

    //Families (Collections of components)
    private final static Family physicsFamily = Families.PHYSICS.getFamily();

    // JLWGL rendering and windowing
    private RenderSystem renderSystem;

    private boolean gameRunning = true;

    public ArrayList<Keys> keys = new ArrayList<Keys>();

    // Fixed time step variable
    public static final int frameRate = 60;
    public static final float interval = 1f / frameRate;
    public float accumulator = 0f;
    public long lastTime;

    //Game world
    public World world;

    //Unit builder
    public UnitBuilder unitBuilder;

    //Camera
    public Camera camera;

    private boolean visualize;
    public GameEngine(boolean visualize) {
        this.visualize=visualize;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        gameContainer.setTargetFrameRate(frameRate);
        MovementSystem movementSystem = new MovementSystem(10);

        this.addSystem(movementSystem);

        //We use this.createEntity() as to avoid initializing new things.
        //for the sake of pooling
        //this.addEntity(Soldier.convertEntity(this.createEntity(), this));


        /*
        We just add the entire world to the engine as entities
        This way of splitting up code is kinda anti pattern and bad but it's fine for now
         */

        this.camera=new Camera();
        InputSystem inputSystem = new InputSystem(this);
        gameContainer.getInput().addKeyListener(inputSystem);
        gameContainer.getInput().addMouseListener(inputSystem);

        world = new World();
        world.generateWorld(0);
        world.addWorldToEngine(this);

        unitBuilder = new UnitBuilder(this);
        unitBuilder.summon(Unit.KNIGHT, 100, 100);

        renderSystem = new RenderSystem(this);
    }

    @Override
    public void update(GameContainer gameContainer, int dt) throws SlickException {

        //Update animations
        for(Entity entity : this.getEntitiesFor(Family.all(AnimationComponent.class).get())) {
            AnimationComponent animations = animationMapper.get(entity);
            animations.animation.update(dt);
        }

        update((float)dt/1000f);
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
