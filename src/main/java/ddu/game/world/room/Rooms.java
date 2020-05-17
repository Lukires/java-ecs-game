package ddu.game.world.room;

import ddu.game.util.JSON;

public enum Rooms {
    STONE_DEBUG(new RoomIdentity(JSON.getJson("src/main/resources/rooms/easy/0.json")));

    RoomIdentity roomIdentity;
    Rooms(RoomIdentity roomIdentity) {
        this.roomIdentity = roomIdentity;
    }

    public RoomIdentity getRoomIdentity() {
        return this.roomIdentity;
    }
}
