package ddu.game.texture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Texture {
    private Image image;

    public Texture(String filepath) {
        try {
            this.image = new Image(filepath);
            this.image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public Texture(Image image) {
        this.image=image;
    }

    public Image getImage() {
        return this.image;
    }
}
