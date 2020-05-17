package ddu.game.animation;

import ddu.game.texture.Texture;

public class Animation {
    public final Texture[] frames;
    // These are the start time in seconds for each frame. Has to be ascending.
    public final Float[] durations;
    public final boolean loop;

    Animation(Texture[] frames, Float[] durations, boolean loop) {
        this.frames = frames;
        this.durations = durations;
        this.loop = loop;
    }

    float totalDuration() {
        return durations[durations.length-1];
    }
}