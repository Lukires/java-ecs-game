package ddu.game.animation;

import ddu.game.texture.Spritesheets;
import ddu.game.texture.Textures;
import org.newdawn.slick.Image;

public enum Animations {
    KNIGHT_STANDING_RIGHT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0)},
            new int[]{1000}, true)),
    KNIGHT_STANDING_LEFT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0)
                .getFlippedCopy(true, false)},
            new int[]{1000}, true)),
    KNIGHT_WALKING_RIGHT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,0)},
            new int[]{200,200}, true)),
    KNIGHT_WALKING_LEFT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,0)
                    .getFlippedCopy(true, false),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,0)
                    .getFlippedCopy(true, false)},
            new int[]{200,200}, true)),
    KNIGHT_ATTACK_RIGHT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(2,1),
            Spritesheets.KNIGHT_ANIMATION.getImage(3,1)},
            new int[]{200,200,200,200}, true)),
    KNIGHT_ATTACK_LEFT(new Animation(new Image[]{
            Spritesheets.KNIGHT_ANIMATION.getImage(0,1)
                    .getFlippedCopy(true, false),
            Spritesheets.KNIGHT_ANIMATION.getImage(1,1)
                .getFlippedCopy(true, false),
            Spritesheets.KNIGHT_ANIMATION.getImage(2,1)
                .getFlippedCopy(true, false),
            Spritesheets.KNIGHT_ANIMATION.getImage(3,1)
                .getFlippedCopy(true, false)},
            new int[]{200,200,200,200}, true)),

    EVIL_KNIGHT_STANDING_RIGHT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,0)},
            new int[]{1000}, true)),
    EVIL_KNIGHT_STANDING_LEFT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,0)
                    .getFlippedCopy(true, false)},
            new int[]{1000}, true)),
    EVIL_KNIGHT_WALKING_RIGHT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,0),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(1,0)},
            new int[]{200,200}, true)),
    EVIL_KNIGHT_WALKING_LEFT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,0)
                    .getFlippedCopy(true, false),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(1,0)
                    .getFlippedCopy(true, false)},
            new int[]{200,200}, true)),
    EVIL_KNIGHT_ATTACK_RIGHT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,1),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(1,1),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(2,1),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(3,1)},
            new int[]{200,200,200,200}, true)),
    EVIL_KNIGHT_ATTACK_LEFT(new Animation(new Image[]{
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(0,1)
                    .getFlippedCopy(true, false),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(1,1)
                    .getFlippedCopy(true, false),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(2,1)
                    .getFlippedCopy(true, false),
            Spritesheets.EVIL_KNIGHT_ANIMATION.getImage(3,1)
                    .getFlippedCopy(true, false)},
            new int[]{200,200,200,200}, true));

    Animation animation;
    Animations(Animation animation) {
        this.animation=animation;
    }

    public Animation getAnimation() {
        return animation;
    }
}
