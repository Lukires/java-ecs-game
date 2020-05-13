package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.joml.Vector2d;

public class PositionComponent implements Component, Pool.Poolable {
    public Vector2d position = new Vector2d(0,0);

    public void reset() {
        position = new Vector2d(0,0);
    }
}
