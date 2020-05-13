package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.joml.Vector2d;

public class VelocityComponent implements Component, Pool.Poolable {
    public Vector2d velocity = new Vector2d(0,0);

    public void reset() {
        velocity = new Vector2d(0,0);
    }
}
