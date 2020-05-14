package ddu.game.window;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    public long window;

    public void run() {
        System.out.println("LWJGL " + Version.getVersion());
        init();

        GL.createCapabilities();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void terminate() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        //Init
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        //Configuration
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1000, 1000, "Game", 0, 0);

        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            keyCall(window, key, scancode, action, mods);
        });

        //Resize
        glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            glViewport(0,0,width,height);
        });


        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            //Place our window in the middle of the screen
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0) / 2), (vidmode.height() - pHeight.get(0) / 2));
        }

        glfwMakeContextCurrent(window);

        //V-Sync to avoid screen tearing
        //Sync with refresh rate of video card
        glfwSwapInterval(1);


        glfwShowWindow(window);
    }

    private void keyCall(long window, int key, int scancode, int action, int mods) {
        if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
            glfwSetWindowShouldClose(window, true);
            terminate();
        }
    }

    public long getWindow() {
        return this.window;
    }


}
