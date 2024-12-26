package org.uob.a1;

public class Map {
    private char[][] mapArray;
    private int width;
    private int height;
    private final char EMPTY = '.';
    private Room[] rooms;
    private int roomCount;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        mapArray = new char[height][width];
        rooms = new Room[10];
        roomCount = 0;


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mapArray[i][j] = EMPTY;
            }
        }
    }

    public void placeRoom(Position pos, char symbol) {
        if (pos.x >= 0 && pos.x < width && pos.y >= 0 && pos.y < height) {
            mapArray[pos.y][pos.x] = symbol;
        }
    }

    public void addRoom(Room room) {
        if (roomCount < rooms.length) {
            rooms[roomCount] = room;
            roomCount++;
            placeRoom(room.getPosition(), room.getSymbol());
        }
    }

    public boolean isRoomAtPosition(Position pos) {
        return getRoomAtPosition(pos) != null;
    }

    public Room getRoomAtPosition(Position pos) {
        for (int i = 0; i < roomCount; i++) {
            if (rooms[i].getPosition().equals(pos)) {
                return rooms[i];
            }
        }
        return null;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(mapArray[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
