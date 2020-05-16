package ddu.game.texture;

import de.matthiasmann.twl.utils.PNGDecoder;

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

    private int id;
    private int width;
    private int height;

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Texture(String filepath) {

        try{
            PNGDecoder decoder = new PNGDecoder(this.getClass().getResourceAsStream(filepath));
            this.width=decoder.getWidth();
            this.height=decoder.getHeight();

            //Width * Height * 4 (4 for all the rgba)
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getWidth());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);

            //The buffer is ready to read
            buffer.flip();

            //create texture
            int id = glGenTextures();

            //Now we start loading the texture and giving it id and stuff
            //glBindTexture(GL_TEXTURE_2D, id);

            //select memory place
            glActiveTexture(GL_TEXTURE0);

            //bind texture in memory
            glBindTexture(GL_TEXTURE_2D, id);

            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);


            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            glGenerateMipmap(GL_TEXTURE_2D);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

}
