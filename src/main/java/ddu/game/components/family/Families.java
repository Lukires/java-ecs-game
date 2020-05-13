package ddu.game.components.family;

import com.badlogic.ashley.core.Family;
import ddu.game.components.CollisionComponent;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;

public enum Families {
    PHYSICS(Family.all(PositionComponent.class, VelocityComponent.class, CollisionComponent.class).get()),
    DRAW(Family.all(PositionComponent.class, DrawComponent.class).get());

    private Family family;
    Families(Family family) {
        this.family=family;
    }

    public Family getFamily() {
        return this.family;
    }
}
