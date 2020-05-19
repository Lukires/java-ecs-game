package ddu.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.components.PositionComponent;
import ddu.game.components.VelocityComponent;
import ddu.game.components.collision.CollisionComponent;
import ddu.game.components.family.Families;

public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper  = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private static final Family physicsFamily = Families.PHYSICS.getFamily();

    public MovementSystem(int priority) {
        super(priority);
    }

    public void addedToEngine(Engine engine) {
        this.entities = engine.getEntitiesFor(physicsFamily);
    }

    public void update(float dt) {

        PositionComponent positionComponent;
        VelocityComponent velocityComponent;

        for(Entity entity : entities) {
            positionComponent = positionMapper.get(entity);

            if(velocityMapper.has(entity)) {
                velocityComponent = velocityMapper.get(entity);
                positionComponent.position.add(velocityComponent.velocity);
            }

            if(collisionMapper.has(entity)) {
                CollisionComponent collisionComponent = collisionMapper.get(entity);
                collisionComponent.getHitbox().setX((float)positionComponent.position.x);
                collisionComponent.getHitbox().setY((float)positionComponent.position.y);
            }

        }
    }

}
