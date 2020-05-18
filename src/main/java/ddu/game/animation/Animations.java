package ddu.game.animation;

import ddu.game.texture.Spritesheets;
import org.newdawn.slick.Image;

public enum Animations {
    KNIGHT_STANDING(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0)},
            new int[]{1000}, true)),
    KNIGHT_WALKING(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,0)},
            new int[]{200,200}, true)),
    KNIGHT_ATTACK(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(2,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(3,1)},
            new int[]{200,200,200,200}, true));
    Animation animation;
    Animations(Animation animation) {
        this.animation=animation;
    }

    public Animation getAnimation() {
        return animation;
    }
}
