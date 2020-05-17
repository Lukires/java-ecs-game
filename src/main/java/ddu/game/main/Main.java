package ddu.game.main;


import ddu.game.GameEngine;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Finding natives based on operating system

        File JGLLib;
        switch(LWJGLUtil.getPlatform())
        {
            case LWJGLUtil.PLATFORM_WINDOWS:
            {
                JGLLib = new File("./libs/windows/");
            }
            break;

            case LWJGLUtil.PLATFORM_LINUX:
            {
                JGLLib = new File("./libs/linux/");
            }
            break;

            case LWJGLUtil.PLATFORM_MACOSX:
            {
                JGLLib = new File("./libs/macosx/");
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + LWJGLUtil.getPlatform());
        }

        System.setProperty("org.lwjgl.librarypath", JGLLib.getAbsolutePath());


        try{
            AppGameContainer game = new AppGameContainer(new GameEngine(true));
            game.setDisplayMode(640, 480, false);
            game.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }


    }

}
