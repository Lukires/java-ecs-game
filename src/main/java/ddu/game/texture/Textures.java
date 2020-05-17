package ddu.game.texture;

import org.newdawn.slick.Image;

public enum Textures {

    STONE_TILE(new Texture("./sprites/tiles/floor.png"));

    Texture texture;

    Textures(Texture texture) {
        this.texture=texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Image getImage() {
        return texture.getImage();
    }

}
