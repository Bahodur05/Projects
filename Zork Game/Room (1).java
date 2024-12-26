package org.uob.a1;

public class Room {
    private String name;
    private String description;
    private char symbol;
    private Position position;
    private String[] items;
    private int itemCount;

    public Room(String name, String description, char symbol, Position position) {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
        this.items = new String[5];
        this.itemCount = 0;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public char getSymbol() {
        return symbol;
    }

    public Position getPosition() {
        return position;
    }

    public void addItem(String item) {
        if (itemCount < items.length) {
            items[itemCount] = item;
            itemCount++;
        }
    }

    public boolean hasItem(String item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                items[i] = items[itemCount - 1];
                items[itemCount - 1] = null;
                itemCount--;
                break;
            }
        }
    }

    public String[] getItems() {
        String[] currentItems = new String[itemCount];
        System.arraycopy(items, 0, currentItems, 0, itemCount);
        return currentItems;
    }
}
