package org.uob.a1;


public class Inventory {
    private final int MAX_ITEMS = 10;
    private String[] items;
    private int itemCount;

    public Inventory(){
        itemCount = 0;
        items = new String[MAX_ITEMS];
    }

    public void addItem(String item) {
        if (itemCount < MAX_ITEMS) {
            items[itemCount] = item;
            itemCount++;
            System.out.println(item + " has been added to your inventory.");
        } else {
            System.out.println("Inventory is full. Cannot add " + item + ".");
        }
    }

    public int hasItem(String item) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void removeItem(String item) {
        int index = hasItem(item);
        if (index != -1) {
            items[index] = items[itemCount - 1];
            items[itemCount - 1] = null;
            itemCount--;
            System.out.println(item + " has been removed from your inventory.");
        } else {
            System.out.println("Item " + item + " not found in inventory.");
        }
    }

    public String displayInventory() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < itemCount; i++) {
            sb.append(items[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
}
