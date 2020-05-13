package ddu.game.entities;

import com.badlogic.ashley.core.Entity;
import ddu.game.components.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;

public class Soldier extends Entity {
    public Soldier() {
        add(new PositionComponent());
        add(new VelocityComponent());
        add(new DrawComponent());
        add(new CollisionComponent());
    }
}
