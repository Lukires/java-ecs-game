package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import ddu.game.animation.Animation;

public class AnimationComponent implements Component, Pool.Poolable {

    public int selected = 0;
    public Animation animation;
    public short zIndex = 0;
    public int dx = 0;
    public int dy = 0;

    public void reset() {
        zIndex = 0;
        selected = 0;
    }
}
