package ddu.game.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import ddu.game.GameEngine;
import ddu.game.components.*;
import ddu.game.components.collision.CollisionComponent;

public class CombatSystem extends EntitySystem {

    private ComponentMapper<CombatComponent> combatMapper = ComponentMapper.getFor(CombatComponent.class);
    private ComponentMapper<HealthComponent> healthMapper  = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<CollisionComponent> collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
    private ComponentMapper<UnitComponent> unitMapper = ComponentMapper.getFor(UnitComponent.class);
    private ComponentMapper<AnimationComponent> animationMapper = ComponentMapper.getFor(AnimationComponent.class);

    GameEngine engine;
    private ImmutableArray<Entity> entities;

    public CombatSystem(GameEngine engine) {
        this.engine=engine;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
    }


    @Override
    public void update(float deltaTime) {

        this.entities = engine.getEntitiesFor(Family.all(CombatComponent.class, HealthComponent.class, CollisionComponent.class, UnitComponent.class, AnimationComponent.class).get());


        for(int x = 0; x < entities.size(); x++) {
            Entity entityFirst = entities.get(x);
            CombatComponent combatComponent = combatMapper.get(entityFirst);
            HealthComponent healthComponent = healthMapper.get(entityFirst);
            CollisionComponent collisionComponent = collisionMapper.get(entityFirst);
            UnitComponent unitComponent = unitMapper.get(entityFirst);
            AnimationComponent animationComponent = animationMapper.get(entityFirst);
            for(int y = 0; y < entities.size(); y++) {
                Entity entitySecond = entities.get(y);
                if (entityFirst==entitySecond) {
                    continue;
                }

                CombatComponent combatComponentSecond = combatMapper.get(entitySecond);
                HealthComponent healthComponentSecond = healthMapper.get(entitySecond);
                CollisionComponent collisionComponentSecond = collisionMapper.get(entitySecond);
                UnitComponent unitComponentSecond = unitMapper.get(entitySecond);

                if(combatComponent.team == combatComponentSecond.team) {
                    continue;
                }

                if(combatComponent.range.intersects(collisionComponentSecond.getHitbox())) {
                    if(collisionComponent.getHitbox().getX()<=collisionComponentSecond.getHitbox().getX()) {
                        animationComponent.animation = unitComponent.unit.rightAttack;
                    }else{
                        animationComponent.animation = unitComponent.unit.rightAttack;
                    }
                    healthComponentSecond.health-=combatComponent.damage;
                    break;
                }
            }

            if (healthComponent.health <= 0) {
                engine.removeEntity(entityFirst);
            }

        }

        super.update(deltaTime);
    }
}
