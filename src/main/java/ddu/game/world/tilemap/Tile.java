package ddu.game.world.tilemap;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.texture.Texture;
import ddu.game.texture.Textures;

public enum Tile {
    STONE(Textures.STONE_TILE.getTexture(), 16, 16, false, 1.0);

    Texture texture;
    int width;
    int height;
    boolean collidable;
    double speed;

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

    Tile(Texture texture, int width, int height, boolean collidable, double speed) {
        this.texture=texture;
        this.width=width;
        this.height=height;
        this.collidable=collidable;
        this.speed=speed;
    }

    public static Entity convertEntity(Entity entity, Tile type, PooledEngine engine) {
        entity.add(engine.createComponent(DrawComponent.class));
        entity.add(engine.createComponent(PositionComponent.class));

        if (type.collidable) {
            entity.add(engine.createComponent(CollisionComponent.class));
        }

        return entity;
    }
}
