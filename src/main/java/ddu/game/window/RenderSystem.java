package ddu.game.window;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.AnimationComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.family.Families;
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

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DrawComponent> drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);

    private GameEngine engine;
    Camera camera;

    //Used for z-sorting
    Comparator<Entity> zComparator = new ZComparator();

    public RenderSystem(GameEngine engine) {
        this.engine=engine;
        this.camera=engine.camera;
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        this.entities = engine.getEntitiesFor(Families.DRAW.getFamily());
        List<Entity> sortedEntities = Arrays.asList(this.entities.toArray());

        Collections.sort(sortedEntities, zComparator);

        graphics.translate(camera.getX(), camera.getY());
        graphics.scale(camera.getScale(), camera.getScale());

        graphics.setWorldClip(camera.getX(), camera.getY(), gameContainer.getWidth(), gameContainer.getHeight());

        PositionComponent positionComponent;
        DrawComponent drawComponent;
        AnimationComponent animationComponent;

        //Entities that we need to draw
        for(Entity entity : sortedEntities) {
            positionComponent = positionMapper.get(entity);

            //Animations
            if (animationMapper.has(entity)) {
                animationComponent = animationMapper.get(entity);

                graphics.drawImage(animationComponent.animation.getCurrentFrame(), (int)positionComponent.position.x, (int)positionComponent.position.y);
            }else{
                drawComponent = drawMapper.get(entity);
                graphics.drawImage(drawComponent.texture.getImage(), (int)positionComponent.position.x, (int)positionComponent.position.y);
            }
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
