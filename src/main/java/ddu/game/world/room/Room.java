package ddu.game.world.room;

import ddu.game.world.tilemap.Tile;

public class Room {

    RoomIdentity roomIdentity;
    int x;
    int y;

    public Room(Rooms rooms, int x, int y) {
        this(rooms.getRoomIdentity(), x, y);
    }

    public Room(RoomIdentity roomIdentity, int x, int y) {
        this.roomIdentity=roomIdentity;
        this.x=x;
        this.y=y;
    }

    public int getWidth() {
        return this.roomIdentity.getWidth();
    }

    public int getHeight() {
        return this.roomIdentity.getWidth();
    }

    public Tile[][] getTiles() {
        return roomIdentity.getTiles();
    }

    public RoomIdentity getRoomIdentity() {
        return roomIdentity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
