package ddu.game;

import com.badlogic.ashley.core.*;
import ddu.game.animation.Animation;
import ddu.game.components.AnimationComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.input.InputSystem;
import ddu.game.input.Keys;
import ddu.game.systems.CombatSystem;
import ddu.game.systems.MovementSystem;
import ddu.game.systems.PathFindingSystem;
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
    private static final int frameRate = 60;

    //InputSystem
    public InputSystem inputSystem;

    //Camera
    public Camera camera;

    private boolean visualize;
    public GameEngine(boolean visualize) {
        this.visualize=visualize;
    }

    public GameContainer gameContainer;

    @Override
    public void init(GameContainer ngameContainer) throws SlickException {
        gameContainer=ngameContainer;
        gameContainer.setTargetFrameRate(frameRate);

        // Systems
        MovementSystem movementSystem = new MovementSystem(10);
        this.addSystem(movementSystem);

        PathFindingSystem pathFindingSystem = new PathFindingSystem(this);
        this.addSystem(pathFindingSystem);

        CombatSystem combatSystem = new CombatSystem(this);
        this.addSystem(combatSystem);

        //We use this.createEntity() as to avoid initializing new things.
        //for the sake of pooling
        //this.addEntity(Soldier.convertEntity(this.createEntity(), this));


        /*
        We just add the entire world to the engine as entities
        This way of splitting up code is kinda anti pattern and bad but it's fine for now
         */

        gameContainer.setVSync(true);
        gameContainer.setShowFPS(false);

        this.camera=new Camera();
        inputSystem = new InputSystem(this);
        gameContainer.getInput().addKeyListener(inputSystem);
        gameContainer.getInput().addMouseListener(inputSystem);

        //Game world
        World world = new World();
        world.generateWorld(0);
        world.addWorldToEngine(this);

        //Unit builder
        UnitBuilder unitBuilder = new UnitBuilder(this);
        unitBuilder.summon(Unit.KNIGHT, 32, 32);
        unitBuilder.summon(Unit.KNIGHT, 32, 48);
        unitBuilder.summon(Unit.KNIGHT, 32, 64);
        unitBuilder.summon(Unit.KNIGHT, 32, 80);
        unitBuilder.summon(Unit.KNIGHT, 32, 96);

        unitBuilder.summon(Unit.EVIL_KNIGHT, 96, 96);
        unitBuilder.summon(Unit.EVIL_KNIGHT, 110, 130);
        unitBuilder.summon(Unit.EVIL_KNIGHT, 134, 110);
        unitBuilder.summon(Unit.EVIL_KNIGHT, 29, 220);
        unitBuilder.summon(Unit.EVIL_KNIGHT, 192, 56);
        unitBuilder.summon(Unit.EVIL_KNIGHT, 119, 192);

        renderSystem = new RenderSystem(this);

    }

    @Override
    public void update(GameContainer gameContainer, int dt) throws SlickException {

        //Update animations

        ArrayList<Animation> animationList = new ArrayList<Animation>();

        for(Entity entity : this.getEntitiesFor(Family.all(AnimationComponent.class).get())) {
            AnimationComponent animations = animationMapper.get(entity);
            if (!animationList.contains(animations.animation))
            {
                animations.animation.update(dt);
                animationList.add(animations.animation);
            }
        }

        //Entity component system update
        update((float)dt/1000f);

        // Update input
        inputSystem.update();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
            /* Arrows and their numbers
    <- 203
    ^ 200
    V 208
    205
    ESC 1
    */
        if(inputSystem.keysDown.contains(203)) {
            camera.setX(camera.getX()+5);
        }
        if(inputSystem.keysDown.contains(205)) {
            camera.setX(camera.getX()-5);
        }
        if(inputSystem.keysDown.contains(200)) {
            camera.setY(camera.getY()+5);
        }
        if(inputSystem.keysDown.contains(208)) {
            camera.setY(camera.getY()-5);
        }
        if(inputSystem.keysDown.contains(1)) {
            System.exit(0);
        }

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
