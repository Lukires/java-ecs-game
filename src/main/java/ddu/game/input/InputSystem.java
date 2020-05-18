package ddu.game.input;

import ddu.game.GameEngine;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

public class InputSystem implements KeyListener, MouseListener {

    GameEngine engine;
    public InputSystem(GameEngine engine) {
        this.engine=engine;
    }

    /* Arrows and their numbers
    <- 203
    ^ 200
    V 208
    205
    */

    @Override
    public void keyPressed(int key, char c) {
        System.out.println(key);


    }

    @Override
    public void keyReleased(int i, char c) {

    }

    @Override
    public void mouseWheelMoved(int i) {
        engine.camera.setScale(engine.camera.getScale()+((float)i/(float)1000));

    }

    @Override
    public void mouseClicked(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mousePressed(int i, int i1, int i2) {

    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {

    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {

    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {

    }

    @Override
    public void setInput(Input input) {

    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {

    }

    @Override
    public void inputStarted() {

    }
}
