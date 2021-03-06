package ddu.game.texture;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public enum Spritesheets {
    KNIGHT_ANIMATION("./sprites/sheets/knight.png", 28, 20),
    EVIL_KNIGHT_ANIMATION("./sprites/sheets/evil_knight.png", 28, 20),
    DUNGEON_TILES("./sprites/sheets/dungeontiles.png", 16, 16);

    SpriteSheet spriteSheet;
    Spritesheets(String path, int spriteWidth, int spriteHeight) {
        this.spriteSheet = new SpriteSheet(new Texture(path).getImage(),spriteWidth,spriteHeight);
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public Image getImage(int x, int y) {
        return spriteSheet.getSubImage(x,y);
    }
}
