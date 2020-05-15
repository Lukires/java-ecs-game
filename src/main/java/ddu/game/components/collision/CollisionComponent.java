package ddu.game.components.collision;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class CollisionComponent implements Component, Pool.Poolable {

    private boolean collidable = true;

    @Override
    public void reset() {
        collidable = true;
    }

    public void setCollidable(boolean collidable) {
        this.collidable=collidable;
    }

    public boolean getCollideable(){
        return this.collidable;
    }
}
