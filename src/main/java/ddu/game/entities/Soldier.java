package ddu.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
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

    public static Entity convertEntity(Entity entity, PooledEngine engine) {
        entity.add(engine.createComponent(PositionComponent.class));
        entity.add(engine.createComponent(VelocityComponent.class));
        entity.add(engine.createComponent(DrawComponent.class));
        entity.add(engine.createComponent(CollisionComponent.class));
        return entity;
    }
}
