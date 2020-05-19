package ddu.game.world;

import com.badlogic.ashley.core.Entity;
import ddu.game.GameEngine;
import ddu.game.components.DrawComponent;
import ddu.game.components.PositionComponent;
import ddu.game.world.room.Room;
import ddu.game.world.room.Rooms;
import ddu.game.world.tilemap.Tile;
import org.joml.Vector2d;

import java.util.ArrayList;

public class World {

    private ArrayList<Room> rooms = new ArrayList<Room>();
    public World() {

    }

    public void generateWorld(long seed) {
        this.rooms.add(new Room(Rooms.STONE_DEBUG,0,0));
    }

    public void addWorldToEngine(GameEngine engine) {
        for(Room room : rooms) {
            int roomx = room.getX();
            int roomy = room.getY();

            Tile[][] tiles = room.getTiles();
            for(int x = 0; x < room.getWidth(); x++) {
                Tile[] tileAxis = tiles[x];

                for(int y = 0; y < room.getHeight(); y++) {
                    Tile tile = tileAxis[y];

                    Entity entity = Tile.convertEntity(engine.createEntity(), tile, engine);
                    PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
                    DrawComponent drawComponent = entity.getComponent(DrawComponent.class);
                    positionComponent.position = new Vector2d(roomx+x*tile.getWidth(), roomx+y*tile.getHeight());
                    drawComponent.texture = tile.getTexture();
                    engine.addEntity(entity);
                }
            }

        }
    }
}
