package ddu.game.unit;

import ddu.game.animation.Animation;
import ddu.game.animation.Animations;

public enum Unit {
    KNIGHT(100f, Animations.KNIGHT_ATTACK.getAnimation());

    Animation animation;
    float health;
    Unit(float health, Animation animation) {
        this.health=health;
        this.animation = animation;
    }

    public float getHealth() {
        return health;
    }

    public Animation getAnimation() {
        return animation;
    }
}
