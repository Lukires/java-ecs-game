package ddu.game.animation;

import ddu.game.texture.Texture;

public class AnimationState {
    private final Animation animation;
    private float timer;

    AnimationState(Animation animation, float timer) {
        this.animation = animation;
        this.timer = timer;
    }

    void update(float dt) {
        timer += dt;
    }

    Texture getCurrentFrame() {
        for (int i = 0; i < animation.durations.length; i++) {
            if (timer < animation.durations[i]) {
                return animation.frames[i];
            }
        }

        // Our timer is beyond all frames.
        if (animation.loop) {
            // Loop to the beginning;
            timer -= animation.totalDuration();
            return animation.frames[0];
        } else {
            // If the animation doesn't loop, we stay on the last frame indefinitely
            return animation.frames[animation.frames.length-1];
        }
    }
}
