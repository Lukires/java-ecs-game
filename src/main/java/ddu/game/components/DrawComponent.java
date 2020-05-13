package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import ddu.game.texture.Texture;

public class DrawComponent implements Component, Pool.Poolable {
    public Texture texture;
    public short zIndex = 0;

    public void reset() {
        zIndex = 0;
    }
}
