package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.newdawn.slick.geom.Shape;

public class CombatComponent implements Component, Pool.Poolable {

    public Team team;
    public Shape range;
    public float damage;


    @Override
    public void reset() {

    }

    public enum Team {
        GOOD, EVIL;
    }
}
