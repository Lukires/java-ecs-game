package ddu.game.unit;

import ddu.game.animation.Animation;
import ddu.game.animation.Animations;
import ddu.game.components.CombatComponent;

public enum Unit {
    KNIGHT(CombatComponent.Team.GOOD, 100f, 10f, 32,
            Animations.KNIGHT_STANDING_RIGHT.getAnimation(),
            Animations.KNIGHT_STANDING_LEFT.getAnimation(),
            Animations.KNIGHT_WALKING_RIGHT.getAnimation(),
            Animations.KNIGHT_WALKING_LEFT.getAnimation(),
            Animations.KNIGHT_ATTACK_RIGHT.getAnimation(),
            Animations.KNIGHT_ATTACK_LEFT.getAnimation()
    ),
    EVIL_KNIGHT(CombatComponent.Team.EVIL, 100f, 2f, 32,
            Animations.EVIL_KNIGHT_STANDING_RIGHT.getAnimation(),
            Animations.EVIL_KNIGHT_STANDING_LEFT.getAnimation(),
            Animations.EVIL_KNIGHT_WALKING_RIGHT.getAnimation(),
            Animations.EVIL_KNIGHT_WALKING_LEFT.getAnimation(),
            Animations.EVIL_KNIGHT_ATTACK_RIGHT.getAnimation(),
            Animations.EVIL_KNIGHT_ATTACK_LEFT.getAnimation()
    );


    public Animation rightStanding;
    public Animation leftStanding;

    public Animation rightWalking;
    public Animation leftWalking;

    public Animation rightAttack;
    public Animation leftAttack;
    float health, damage, radius;
    CombatComponent.Team team;

    Unit(CombatComponent.Team team, float health, float damage, float radius,
         Animation rightStanding,
         Animation leftStanding,
         Animation rightWalking,
         Animation leftWalking,
         Animation rightAttack,
         Animation leftAttack
    ) {
        this.health = health;
        this.damage=damage;
        this.radius=radius;
        this.team=team;

        this.rightStanding = rightStanding;
        this.leftStanding = leftStanding;
        this.rightWalking = rightWalking;
        this.leftWalking = leftWalking;
        this.rightAttack = rightAttack;
        this.leftAttack = leftAttack;
    }


    public float getHealth() {
        return health;
    }

}
