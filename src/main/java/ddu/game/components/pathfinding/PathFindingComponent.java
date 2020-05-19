package ddu.game.components.pathfinding;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.joml.Vector2d;

public class PathFindingComponent implements Component, Pool.Poolable{

    public boolean travel = false;
    public Vector2d destination = new Vector2d(0,0);

    @Override
    public void reset() {
        this.travel = false;
        this.destination = new Vector2d(0,0);
    }
}
