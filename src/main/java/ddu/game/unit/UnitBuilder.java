package ddu.game.unit;

import com.badlogic.ashley.core.Entity;
import ddu.game.GameEngine;
import ddu.game.components.*;
import ddu.game.components.collision.CollisionComponent;
import org.joml.Vector2d;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class UnitBuilder {

    private GameEngine engine;
    public UnitBuilder(GameEngine engine) {
        this.engine=engine;
    }

    public void summon(Unit unit, int x, int y) {
        Entity entity = engine.createEntity();
        AnimationComponent animationComponent = (AnimationComponent) entity.addAndReturn(engine.createComponent(AnimationComponent.class));
        HealthComponent healthComponent = (HealthComponent) entity.addAndReturn(engine.createComponent(HealthComponent.class));
        PositionComponent positionComponent = (PositionComponent) entity.addAndReturn(engine.createComponent(PositionComponent.class));

        if(unit.team.equals(CombatComponent.Team.GOOD)) {
            SelectableComponent selectableComponent = (SelectableComponent) entity.addAndReturn(engine.createComponent(SelectableComponent.class));
        }
        VelocityComponent velocityComponent = (VelocityComponent) entity.addAndReturn(engine.createComponent(VelocityComponent.class));
        UnitComponent unitComponent = (UnitComponent) entity.addAndReturn(engine.createComponent(UnitComponent.class));
        CollisionComponent collisionComponent = (CollisionComponent) entity.addAndReturn(engine.createComponent(CollisionComponent.class));
        ActionComponent actionComponent = (ActionComponent) entity.addAndReturn(engine.createComponent(ActionComponent.class));
        CombatComponent combatComponent = (CombatComponent) entity.addAndReturn(engine.createComponent(CombatComponent.class));

        animationComponent.animation = unit.rightStanding;
        animationComponent.zIndex = 1;
        animationComponent.dx = -10;
        animationComponent.dy = -7;

        combatComponent.team = unit.team;
        combatComponent.damage = unit.damage;
        combatComponent.range = new Circle(x, y, unit.radius);

        healthComponent.health = unit.getHealth();
        positionComponent.position = new Vector2d(x, y);
        unitComponent.unit = unit;
        collisionComponent.setHitbox((Shape) new Rectangle(x, y, 16,16));


        engine.addEntity(entity);

    }

}
