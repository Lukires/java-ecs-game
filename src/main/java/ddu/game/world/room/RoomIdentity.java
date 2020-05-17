package ddu.game.world.room;

import ddu.game.world.structures.Structure;
import ddu.game.world.tilemap.Tile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RoomIdentity {

    private int height;
    private int width;
    private Tile[][] tiles;
    private Structure structre;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Structure getStructre() {
        return structre;
    }

    public RoomIdentity(JSONObject json) {

        height=Integer.parseInt(json.get("height").toString());
        width=Integer.parseInt(json.get("width").toString());

        JSONArray array = (JSONArray) json.get("tiles");
        tiles = new Tile[width][height];

        for(int x = 0; x < array.size(); x++) {
            JSONArray xaxis = (JSONArray) array.get(x);
            for(int y = 0; y < xaxis.size(); y++) {
                String tile = (String) xaxis.get(y);
                tiles[x][y] = Tile.valueOf(tile);
            }

        }
    }

}
