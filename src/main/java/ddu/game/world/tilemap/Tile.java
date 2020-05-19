package ddu.game.world.tilemap;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.texture.Spritesheets;
import ddu.game.texture.Texture;
import ddu.game.texture.Textures;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.w3c.dom.css.Rect;

public enum Tile {
    STONE(Textures.STONE_TILE.getTexture(), 16, 16, false, 1.0),
    WALL(new Texture(Spritesheets.DUNGEON_TILES.getImage(1,2)), 16, 16, true, 1.0);

    Texture texture;
    int width;
    int height;
    boolean collidable;
    double speed;
    Shape hitbox;

    public Texture getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public double getSpeed() {
        return speed;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    Tile(Texture texture, int width, int height, boolean collidable, double speed) {
        this.texture=texture;
        this.width=width;
        this.height=height;
        this.collidable=collidable;
        this.speed=speed;
        this.hitbox = new Rectangle(0, 0, width, height);
    }

    public static Entity convertEntity(Entity entity, Tile type, PooledEngine engine) {
        entity.add(engine.createComponent(DrawComponent.class));
        entity.add(engine.createComponent(PositionComponent.class));

        if (type.collidable) {
            CollisionComponent collisionComponent = (CollisionComponent) entity.addAndReturn(engine.createComponent(CollisionComponent.class));
            collisionComponent.setHitbox(new Rectangle(0,0, type.getWidth(), type.getHeight()));
        }

        return entity;
    }
}
