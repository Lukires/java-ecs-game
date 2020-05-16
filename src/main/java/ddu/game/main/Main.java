package ddu.game.main;


import ddu.game.GameEngine;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        try{
            AppGameContainer game = new AppGameContainer(new GameEngine(true));
            game.setDisplayMode(640, 480, false);
            game.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }


    }

}
