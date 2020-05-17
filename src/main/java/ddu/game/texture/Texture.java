package ddu.game.texture;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture {

    String path;
    Image image;
    public Texture(String filepath) {
        this.path=filepath;
        try {
            this.image=new Image(filepath);
            this.image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return this.image;
    }


}
