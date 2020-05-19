package ddu.game.input;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Select;
import ddu.game.GameEngine;
import ddu.game.components.AnimationComponent;
import ddu.game.components.SelectableComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.window.Camera;
import org.joml.Vector2d;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class InputSystem implements KeyListener, MouseListener {

    public ArrayList<Integer> keysDown = new ArrayList<Integer>();

    //Actually leftmouse pressed but whatever
    public boolean mousePressed = false;

    public Vector2d mousePressStartPosition = new Vector2d(0,0);
    private ComponentMapper<SelectableComponent> selectableMapper = ComponentMapper.getFor(SelectableComponent.class);
    private ComponentMapper<CollisionComponent> componentMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);

    GameEngine engine;
    public InputSystem(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void keyPressed(int key, char c) {
        keysDown.add(key);
    }

    @Override
    public void keyReleased(int i, char c) {
        if (keysDown.contains(i)) {
            keysDown.remove((Integer)i);
        }
    }

    @Override
    public void mouseWheelMoved(int i) {
        engine.camera.setScale(engine.camera.getScale()+((float)i/(float)1000));

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(SelectableComponent.class, CollisionComponent.class, VelocityComponent.class).get());
        for(Entity entity : entities) {
            if (selectableMapper.get(entity).selected) {
                velocityMapper.get(entity).velocity.add(1,0);
            }
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        if(button==0) {
            mousePressed=true;
            mousePressStartPosition = mouseToGameCoord(engine, x,y);
        }



    }

    @Override
    public void mouseReleased(int button, int x, int y) {

        if(button==0) {
            if(mousePressed) {
                mousePressed=false;
                Vector2d mousePressEndPosition = mouseToGameCoord(engine, x,y);
                ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(SelectableComponent.class, CollisionComponent.class).get());

                float width = (float)mousePressEndPosition.x - (float)mousePressStartPosition.x;
                float height = (float)mousePressEndPosition.y - (float)mousePressStartPosition.y;
                float xRect = (float)mousePressStartPosition.x;
                float yRect = (float)mousePressStartPosition.y;

                if(width < 0) {
                    width=-width;
                    xRect-=width;
                }

                if(height < 0) {
                    height=-height;
                    yRect-=height;
                }

                Rectangle rectangle = new Rectangle(xRect, yRect, width, height);

                for(Entity entity : entities) {
                    SelectableComponent selectableComponent = selectableMapper.get(entity);
                    CollisionComponent collisionComponent = componentMapper.get(entity);

                    if(collisionComponent.collideCenter(rectangle)) {
                        selectableComponent.selected = true;
                    }else{
                        selectableComponent.selected = false;
                    }
                }
            }
        }

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }

    public static Vector2d mouseToGameCoord(GameEngine engine, float x, float y) {
        Camera camera = engine.camera;
        x=(x-camera.getX())/camera.getScale();
        y=(y-camera.getY())/camera.getScale();
        //y=(y+camera.getY())/camera.getScale()+(y/engine.gameContainer.getHeight())*(camera.getY());
        return new Vector2d(x, y);
    }
}
