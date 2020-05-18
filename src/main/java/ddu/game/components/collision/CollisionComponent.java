package ddu.game.components.collision;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.newdawn.slick.geom.Shape;

public class CollisionComponent implements Component, Pool.Poolable {

    private boolean collidable = true;
    private Shape hitbox;

    @Override
    public void reset() {
        collidable = true;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable=collidable;
    }

    public boolean getCollideable(){
        return this.collidable;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }


}
