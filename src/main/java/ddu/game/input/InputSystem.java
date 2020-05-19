package ddu.game.input;

import ddu.game.GameEngine;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;

import java.util.ArrayList;

public class InputSystem implements KeyListener, MouseListener {

    public ArrayList<Integer> keysDown = new ArrayList<Integer>();

    GameEngine engine;
    public InputSystem(GameEngine engine) {
        this.engine=engine;
    }

    @Override
    public void keyPressed(int key, char c) {
        System.out.println(key);

        keysDown.add(key);

    }

    @Override
    public void keyReleased(int i, char c) {
        if (keysDown.contains(i)) {
            keysDown.remove((Integer)i);
        }
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
