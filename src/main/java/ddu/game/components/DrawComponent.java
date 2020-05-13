package ddu.game.components;

import com.badlogic.ashley.core.Component;
import ddu.game.texture.Texture;

public class DrawComponent implements Component {
    public Texture texture;
    public short zIndex = 0;
}
