package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import ddu.game.animation.Animation;

public class AnimationComponent implements Component, Pool.Poolable {

    public Animation animation;
    public short zIndex = 0;
    public int dx = 0;
    public int dy = 0;

    public void reset() {
        animation = null;
        zIndex = 0;
        dx = 0;
        dy = 0;
    }
}
