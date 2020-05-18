package ddu.game.window;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.family.Families;
import ddu.game.texture.Textures;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/*

The difference between the renderer and the window,
is that the window is just a window. The window displays
the renderer

 */
public class RenderSystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<DrawComponent> drawMapper = ComponentMapper.getFor(DrawComponent.class);
    private GameEngine engine;
    Camera camera;

    public RenderSystem(GameEngine engine) {
        this.engine=engine;
        this.camera=engine.camera;
    }

    public void render(GameContainer gameContainer, Graphics graphics) {
        this.entities = engine.getEntitiesFor(Families.DRAW.getFamily());

        graphics.translate(camera.getX(), camera.getY());
        graphics.scale(camera.getScale(), camera.getScale());

        graphics.setWorldClip(camera.getX(), camera.getY(), gameContainer.getWidth(), gameContainer.getHeight());

        PositionComponent positionComponent;
        DrawComponent drawComponent;

        //Entities that we need to draw
        for(Entity entity : entities) {
            positionComponent = positionMapper.get(entity);
            drawComponent = drawMapper.get(entity);

            //drawComponent.texture.getImage().draw((int)positionComponent.position.x, (int)positionComponent.position.y, camera.getScale());

            graphics.drawImage(drawComponent.texture.getImage(), (int)positionComponent.position.x, (int)positionComponent.position.y);
        }

        //graphics.drawImage(Textures.STONE_TILE.getImage(), 100, 100);



    }
}
