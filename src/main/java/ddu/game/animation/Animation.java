package ddu.game.animation;

import org.newdawn.slick.Image;

public class Animation extends org.newdawn.slick.Animation {

    //time is in miliseconds
    public Animation(Image[] frames, int[] time, boolean looping) {
        super(frames, time);
        setLooping(looping);
    }
}
