package ddu.game.window;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.AnimationComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.family.Families;
import ddu.game.input.InputSystem;
import org.joml.Vector2d;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*

The difference between the renderer and the window,
is that the window is just a window. The window displays
the renderer

 */
public class RenderSystem {

    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DrawComponent> drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);

    private GameEngine engine;
    private Camera camera;

    //Used for z-sorting
    private Comparator<Entity> zComparator = new ZComparator();

    public RenderSystem(GameEngine engine) {
        this.engine=engine;
        this.camera=engine.camera;
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        graphics.clear();
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Families.DRAW.getFamily());
        List<Entity> sortedEntities = Arrays.asList(entities.toArray());

        Collections.sort(sortedEntities, zComparator);

        graphics.translate(camera.getX(), camera.getY());
        graphics.scale(camera.getScale(), camera.getScale());

        graphics.setWorldClip(-100, -100, gameContainer.getWidth()+100, gameContainer.getHeight()+100);

        PositionComponent positionComponent;
        DrawComponent drawComponent;
        AnimationComponent animationComponent;

        //Entities that we need to draw
        for(Entity entity : sortedEntities) {
            positionComponent = positionMapper.get(entity);

            //Animations
            if (animationMapper.has(entity)) {
                animationComponent = animationMapper.get(entity);

                graphics.drawImage(
                    animationComponent.animation.getCurrentFrame(),
                    (int)positionComponent.position.x+animationComponent.dx,
                    (int)positionComponent.position.y+animationComponent.dy
                );
            }else{
                drawComponent = drawMapper.get(entity);
                graphics.drawImage(drawComponent.texture.getImage(), (int)positionComponent.position.x, (int)positionComponent.position.y);
            }
        }

        if (engine.inputSystem.leftDown) {

            graphics.setLineWidth(4f);
            Vector2d mouseCoords = InputSystem.mouseToGameCoord(engine, gameContainer.getInput().getMouseX(),gameContainer.getInput().getMouseY());
            graphics.setColor(new Color(255, 255, 255, 50));

            graphics.fillRect((float)engine.inputSystem.mousePressStartPosition.x, (float) engine.inputSystem.mousePressStartPosition.y, (float) mouseCoords.x - (float) engine.inputSystem.mousePressStartPosition.x, (float) mouseCoords.y - (float) engine.inputSystem.mousePressStartPosition.y);

        }

    }


    private class ZComparator implements Comparator<Entity> {

        private ComponentMapper<DrawComponent> drawMapper = ComponentMapper.getFor(DrawComponent.class);
        private ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);

        @Override
        public int compare(Entity e1, Entity e2) {

            int z1 = 0;
            int z2 = 0;

            //Get z-index for first entity
            if (animationMapper.has(e1)) {
                z1 = e1.getComponent(AnimationComponent.class).zIndex;
            }else{
                z1 = e1.getComponent(DrawComponent.class).zIndex;
            }

            //Get z-index for second entity
            if (animationMapper.has(e2)) {
                z2 = e2.getComponent(AnimationComponent.class).zIndex;
            }else{
                z2 = e2.getComponent(DrawComponent.class).zIndex;
            }

            return (int)Math.signum(z1 - z2);
        }
    }

}
