package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SelectableComponent implements Component, Pool.Poolable {

    public boolean selected = false;

    @Override
    public void reset() {
        this.selected=false;
    }
}
