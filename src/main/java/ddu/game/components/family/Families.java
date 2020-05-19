package ddu.game.components.family;

import com.badlogic.ashley.core.Family;
import ddu.game.components.*;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.components.pathfinding.PathFindingComponent;

public enum Families {
    PHYSICS(Family.all(PositionComponent.class).one(VelocityComponent.class, CollisionComponent.class).get()),
    DRAW(Family.all(PositionComponent.class).one(DrawComponent.class, AnimationComponent.class).get()),
    UNIT(Family.all(PositionComponent.class, VelocityComponent.class,
            CollisionComponent.class, HealthComponent.class, SelectableComponent.class,
            PathFindingComponent.class).one(DrawComponent.class,
            AnimationComponent.class, UnitComponent.class).get());

    private Family family;
    Families(Family family) {
        this.family=family;
    }

    public Family getFamily() {
        return this.family;
    }
}
