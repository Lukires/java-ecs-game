package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class HealthComponent implements Component, Pool.Poolable{

    public float health = 100;

    @Override
    public void reset() {
        this.health=100;
    }
}
