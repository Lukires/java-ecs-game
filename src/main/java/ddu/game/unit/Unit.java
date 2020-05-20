package ddu.game.unit;

import ddu.game.animation.Animation;
import ddu.game.animation.Animations;

public enum Unit {
    KNIGHT(100f,
            Animations.KNIGHT_STANDING_RIGHT.getAnimation(),
            Animations.KNIGHT_STANDING_LEFT.getAnimation(),
            Animations.KNIGHT_WALKING_RIGHT.getAnimation(),
            Animations.KNIGHT_WALKING_LEFT.getAnimation(),
            Animations.KNIGHT_ATTACK_RIGHT.getAnimation(),
            Animations.KNIGHT_ATTACK_LEFT.getAnimation()
    );

    public Animation rightStanding;
    public Animation leftStanding;

    public Animation rightWalking;
    public Animation leftWalking;

    public Animation rightAttack;
    public Animation leftAttack;
    float health;

    Unit(float health,
         Animation rightStanding,
         Animation leftStanding,
         Animation rightWalking,
         Animation leftWalking,
         Animation rightAttack,
         Animation leftAttack
    ) {
        this.health = health;

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
