package ddu.game.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameHandler;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.family.Families;
import ddu.game.window.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/*

The difference between the renderer and the window,
is that the window is just a window. The window displays
the renderer

 */
public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DrawComponent> drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private Window windowHandler;
    private GameHandler gameHandler;

    public RenderSystem(int priority, GameHandler gameHandler) {
        super(priority);
        this.gameHandler=gameHandler;
        this.windowHandler=gameHandler.getWindow();
    }

    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(Families.DRAW.getFamily());
    }

    public void update(float dt) {

        if(glfwWindowShouldClose(windowHandler.window)) {
            gameHandler.removeSystem(this);
            return;
        }

        //clear the buffer buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(windowHandler.window); //swap color buffers
        glfwPollEvents(); //poll events

        PositionComponent positionComponent;
        DrawComponent drawComponent;

        //Entities that we need to draw
        for(Entity entity : entities) {
            positionComponent = positionMapper.get(entity);
            drawComponent = drawMapper.get(entity);
        }
    }

}
