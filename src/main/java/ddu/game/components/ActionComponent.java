package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import org.joml.Vector2d;

import java.util.ArrayList;

public class ActionComponent implements Component, Pool.Poolable {
    public ArrayList<Vector2d> actions = new ArrayList<Vector2d>();

    public void reset() {
        actions.clear();
    }
}