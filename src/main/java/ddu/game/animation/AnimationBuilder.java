package ddu.game.animation;

import ddu.game.texture.Texture;

import java.util.ArrayList;

public class AnimationBuilder {
    ArrayList<Texture> frames = new ArrayList<Texture>();
    ArrayList<Float> durations = new ArrayList<Float>();
    boolean loop = true;

    AnimationBuilder() {}

    void addFrame(Texture texture, float duration) {
        frames.add(texture);

        float accumulatedDuration = duration;
        if (!durations.isEmpty()) {
            accumulatedDuration += durations.get(durations.size()-1);
        }

        durations.add(accumulatedDuration);
    }

    void setLoop(boolean loop) {
        this.loop = loop;
    }

    Animation intoAnimation() {
        return new Animation(
            frames.toArray(new Texture[0]),
            durations.toArray(new Float[0]),
            loop
        );
    }
}
