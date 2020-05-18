package ddu.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import ddu.game.unit.Unit;

public class UnitComponent implements Component, Pool.Poolable{
    public Unit unit;

    @Override
    public void reset() {

    }
}
