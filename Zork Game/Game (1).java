package org.uob.a1;
import java.util.Scanner;

public class Game {
    private static boolean hasMetMagician = false;
    private static boolean hasFoundKey = false;

    public static void main(String[] args) {
        Map map = new Map(6, 2);
        Inventory inventory = new Inventory();
        Score score = new Score(100);
        Scanner scanner = new Scanner(System.in);

        Room startingRoom = new Room("Shipwreck Beach", "You've washed ashore after a storm. Find a way back home. There is a path to the East leading to the Dense Jungle", 'S', new Position(0, 0));
        Room jungle = new Room("Dense Jungle", "A thick jungle with rustling sounds.  There is a path to East leading to the Sky Cliffs. Type in'take mystic-water' to collect it.", 'J', new Position(1, 0));
        Room cliffs = new Room("Sky Cliffs", "High cliffs with breathtaking views. The magician awaits here. There is a small path to East leading to the Ancient Temple", 'M', new Position(2, 0));
        Room temple = new Room("Ancient Temple", "An abandoned temple. Rumor has it that a key lies hidden here. Find the key and follow the path to East leading to the Fire Island", 'T', new Position(3, 0));
        Room volcano = new Room("Fire Island", "A dangerous volcanic island. Handle with caution. There is a small path to East leading to the Seaside Ruins. Pour the water into the hot lava by typing 'use mystic-water' to access safely the next island", 'V', new Position(4, 0));
        Room ruins = new Room("Seaside Ruins", "The ruins of an old civilization. Perhaps there's a useful item here. There is a small path to South leading to the Whispering Woods (Hint: type in 'take coin' to add the coin to your inventory)", 'R', new Position(5, 0));
        Room forest = new Room("Whispering Woods", "A forest where the trees seem to whisper secrets to those who listen (Hint:type in 'take compass' to add compass to your inventory). There is a path to West leading to the Shimmering Lagoon", 'W', new Position(5, 1));
        Room lagoon = new Room("Shimmering Lagoon", "A beautiful lagoon, reflecting the sky like a mirror. There is a path to West leading to the Crystal Cave", 'L', new Position(4, 1));
        Room cave = new Room("Crystal Cave", "Glowing crystals light up the cave. You feel the presence of an ancient spirit. There is a path to West leading to the final: Rescue Island.Collect the last item 'Crystal'(hint: type in 'take crystal') and Type in 'use ancient-key' to access last island", 'C', new Position(3, 1));
        Room finalIsland = new Room("Rescue Island", "The final island. A satellite phone is located here.", 'P', new Position(2, 1));

        map.addRoom(startingRoom);
        map.addRoom(jungle);
        map.addRoom(cave);
        map.addRoom(temple);
        map.addRoom(volcano);
        map.addRoom(ruins);
        map.addRoom(forest);
        map.addRoom(lagoon);
        map.addRoom(cliffs);
        map.addRoom(finalIsland);

        jungle.addItem("mystic-water");
        cave.addItem("crystal");
        temple.addItem("ancient-key");
        ruins.addItem("coin");
        forest.addItem("compass");

        Position playerPosition = startingRoom.getPosition();
        Room currentRoom = startingRoom;
        score.visitRoom();

        System.out.println("Welcome to Echoes of the Enchanted Isles!");
        System.out.println("You have been shipwrecked on a mysterious island chain. Find the Ancient Key, visit the Magician, and call for help using the Satellite Phone on the final island.");
        System.out.println("Type 'help' for a list of commands.");

        boolean isRunning = true;

        while (isRunning) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().toLowerCase();

            if (command.equals("help")) {
                System.out.println("Available commands: look, look <feature>, look <item>, move <direction>, inventory, use <item>, take <item>,map,score, call emergency (available only in final island), quit.");
            } else if (command.equals("map")) {
                System.out.println("Map:");
                System.out.println(map.display());
            } else if (command.equals("inventory")) {
                System.out.println("Inventory: "+inventory.displayInventory());
            } else if (command.equals("look")) {
                System.out.println("You are in the " + currentRoom.getName() + ". " + currentRoom.getDescription()); 
            } 
            else if (command.split(" ")[0].equals("move")) {
                String direction = command.split(" ")[1];
                Position newPosition = movePlayer(playerPosition, direction);

                if (map.isRoomAtPosition(newPosition)) {
                    playerPosition = newPosition;
                    currentRoom = map.getRoomAtPosition(newPosition);
                    System.out.println("You moved " + direction + " to the " + currentRoom.getName() + ".");
                    score.visitRoom();
                    if (currentRoom.getName().equals("Sky Cliffs") && !hasMetMagician) {
                        System.out.println("There is a magician in this Island, if you approach him he might provide you with a clue (type in 'look magician' to ask him for a hint )");
                        if (command.equals("look magician")){
                            System.out.println("The Magician greets you and provides a hint: 'The Ancient Key lies within the Ancient Temple. Seek it to unlock your path home.");
                            hasMetMagician = true;
                        } 
                    }
                   
                    else if (currentRoom.getName().equals("Rescue Island")) {
                        System.out.println("You found a satellite phone. Type in 'call emergency' to contact help.");
                    }
                } else {
                    System.out.println("You can't move " + direction + " from here.");
                }
            } else if (command.split(" ")[0].equals("take")) {
                String item = command.split(" ")[1];
                if (currentRoom.hasItem(item)) {
                    inventory.addItem(item);
                    currentRoom.removeItem(item);
                    System.out.println("You picked up the " + item + ".");
                    if (item.equals("ancient-key")) {
                        hasFoundKey = true;
                    }
                } else {
                    System.out.println("There is no " + item + " here.");
                }
            } 
            else if (command.startsWith("use")) {
                String item = command.substring(4);

                if (item.equals("ancient-key") && hasFoundKey && currentRoom == cave) {
                    System.out.println("You used the ancient-key to unlock a hidden passage. The path to the final island is now accessible!");
                    hasFoundKey = true;
                    score.solvePuzzle();
                    inventory.removeItem("ancient-key");
                } else if (item.equals("mystic-water") && currentRoom == volcano) {
                    System.out.println("You poured the Mystic Water to cool the lava, revealing a safe path!");
                    score.solvePuzzle();
                    inventory.removeItem("mystic-water");
                } else {
                    System.out.println("You can't use " + item + " here.");
                }
            } else if (command.equals("look magician")) {
                if (currentRoom.getName().equals("Sky Cliffs") && !hasMetMagician) {
                    System.out.println("The Magician tells you: 'Seek the Ancient Key in the Temple. It is the key to your way home.'");
                    hasMetMagician = true;
                } else {
                    System.out.println("There is no one here to look at to.");
                }
            } else if (command.equals("call emergency")) {
                if (currentRoom.getName().equals("Rescue Island")) {
                    System.out.println("You used the satellite phone to call for help. Congratulations, you have been rescued and returned to civilization!");
                    System.out.println("Final Score: " + score.getScore());
                    isRunning = false;
                } else {
                    System.out.println("You are not on the final island");
                }
            } else if (command.equals("quit")) {
                System.out.println("Thanks for playing Echoes of the Enchanted Isles. Goodbye!");
                isRunning = false;
            } else if (command.equals("look crystal")){
                System.out.println(" A shimmering, translucent crystal that pulses with a faint, ethereal glow. Its surface is cool to the touch, yet it seems to radiate an inner warmth that fills the holder with a sense of calm and purpose. Legends say the crystal was born from the tears of ancient spirits who watched over these islands, infusing it with their wisdom and strength. Though its power is a mystery, those who carry it are guided by an unseen force toward their destiny. The Spirit Crystal is a beacon, a silent guardian on the journey home—a symbol of hope and resilience that lights the path to the final island, where fate awaits. ");
            } else if (command.equals("look mystic-water")){
                System.out.println("A small vial filled with a shimmering, silvery liquid that seems almost alive, swirling gently as though stirred by an unseen breeze. This is no ordinary water—its origin is said to be from a hidden spring at the heart of the island, blessed by ancient spirits who guard the secrets of the land. The water is cool to the touch and radiates a calming energy, yet it holds a powerful force within. Legends speak of its ability to tame the fiercest of flames and cool the molten rivers of Fire Island. Only those deemed worthy can carry it, for it holds the power to transform danger into safety, opening paths where none existed before. To pour the Mystic Water over the lava is to unlock a bridge to the unknown—a step closer to the journey’s end.");
            } else if (command.equals("look ancient-key")){
                System.out.println("A key forged from an unknown metal, dark as midnight yet gleaming with a soft, otherworldly glow. Its intricate design is adorned with strange, twisting symbols that seem to shift when observed closely, as if alive with ancient magic. The key feels warm in your hand, almost as if it remembers the countless hands that held it before, each one driven by the same desire: to escape these islands and return home. Legend claims that this key was created by the island’s first inhabitants, a gift from the spirits, meant to unlock the hidden paths to freedom. It is said to open only one door—the way to the final island, where a lone satellite phone awaits, the player’s last chance to call for rescue. Holding it, you feel a pull toward destiny, as if the key itself is guiding you to fulfill its purpose.");
            } else if (command.equals("look coin")){
                System.out.println("An old, weathered coin etched with strange symbols and figures, worn smooth by time. Its surface shimmers faintly, capturing light in a way that makes it seem alive. The coin is said to belong to an ancient civilization lost to the sea, and legend whispers that it holds the memory of those who walked these islands before. When held, the coin feels heavier than it should, as though it carries the weight of forgotten stories and destinies unfulfilled. Some say the coin can reveal hidden paths to those who listen closely to its silent tales—a token of fate, guiding those it deems worthy.");
            } 
            else if (command.equals("score")){
                System.out.println("Score: "+score.getScore());
            }
            else if (command.equals("look compass")){
                System.out.println("A beautifully crafted compass with a golden needle that glows softly against a darkened dial. Unlike any other, this compass does not merely point north; instead, it responds to the intentions of its bearer, shifting its needle toward places of destiny and secrets yet uncovered. The markings around the dial are ancient and indecipherable, almost as if written in the language of the wind and stars. As you hold it, you feel a tug, a gentle urging forward, as if the compass knows where you need to go—even when you do not. It’s a guide and protector, always true to the path, even in the wildest of storms.");
            }
             else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }

        scanner.close();
    }

    private static Position movePlayer(Position pos, String direction) {
        switch (direction) {
            case "north": return new Position(pos.x, pos.y - 1);
            case "south": return new Position(pos.x, pos.y + 1);
            case "east":  return new Position(pos.x + 1, pos.y);
            case "west":  return new Position(pos.x - 1, pos.y);
            default: return pos;
        }
    }
}