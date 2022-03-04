package com.company;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static com.company.ZorkWSingleton.so;

public class CommandParser {
    Character character;
    // private Room currentRoom;
    String firstWord;
    String secondWord;
    String command;
    String[] verbs = { "go", "take", "examine", "open", "enter", "sharpen", "check" };
    String[] oneWordCommands = { "up", "down", "north", "south", "west", "east", "look", "n", "e", "s", "w", "door",
            "take", "exit", "inventory", "examine", "drop", "attack", "status", "sharpen", "trap" };
    String[] possibleSecondWords = { "up", "down", "north", "south", "west", "east", "n", "e", "s", "w", "letter",
            "bread", "wooden", "shield", "large", "wooden", "shield", "glass", "bottle", "water", "bottle", "key",
            "wooden", "sword", "dull", "knife", "iron", "sword", "calamity", "sword", "goblin", "stronger", "goblin",
            "ogre", "door", "attack", "trap", "exit" };

    // add an all keyword for a special case?
    String[] items = { "letter", "bread", "bread", "woodenShield", "largeWoodenShield", "glassBottle",
            "glassBottleOfWater", "key", "woodenSword", "dullKnife", "ironSword", "calamitySword" };
    String[] monsters = { "goblin", "strongerGoblin", "ogre" };

    public Scanner reader = new Scanner(System.in);

    public String getLine() {
        System.out.print("> ");
        return reader.nextLine().toLowerCase();
    }

    public void parseLine(String line) {
        if (validateLine(line)) {
            line = line.trim();
            executeLine(line);
        } else if (line.equals("enter trap door")) {
            executeLine("trap");
        } else {
            System.out.println("Sorry I don't recognize that command.");
        }
    }

    public void executeLine(String line) {
        command = line;
        String[] lineArray = command.toLowerCase().split(" ");
        // for(String s : lineArray) {so.println(s);}
        if (lineArray.length == 1) {
            boolean flag = false;
            for (String word : oneWordCommands) {
                if (lineArray[0].equals(word)) {
                    switch (word) {
                        case "north", "n", "up" -> {
                            Room temp = this.character.getCurrentRoom();
                            this.character.setCurrentRoom(this.character.getExit("north"));
                            if (this.character.getCurrentRoom() != null) {
                                this.character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Can't go there");
                                this.character.setCurrentRoom(temp);
                            }
                        }
                        case "south", "s", "down" -> {
                            Room temp = this.character.getCurrentRoom();
                            this.character.setCurrentRoom(this.character.getExit("south"));
                            if (this.character.getCurrentRoom() != null) {
                                this.character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Can't go there");
                                this.character.setCurrentRoom(temp);
                            }
                        }
                        case "east", "e" -> {
                            Room temp = this.character.getCurrentRoom();
                            this.character.setCurrentRoom(this.character.getExit("east"));
                            if (this.character.getCurrentRoom() != null) {
                                this.character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Can't go there");
                                this.character.setCurrentRoom(temp);
                            }
                        }
                        case "west", "w" -> {
                            Room temp = this.character.getCurrentRoom();
                            this.character.setCurrentRoom(this.character.getExit("west"));
                            if (this.character.getCurrentRoom() != null) {
                                this.character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Can't go there");
                                this.character.setCurrentRoom(temp);
                            }
                        }
                        case "look", "l" -> {
                            so.println(this.character.getCurrentRoom().longDesc());
                        }
                        case "inventory" -> {
                            this.character.printInventory();
                        }
                        case "take" -> {
                            Room temp = this.character.getCurrentRoom();
                            so.println("Take what?\n");
                            String[] itemTemp = reader.nextLine().toLowerCase().split(" ");
                            if (itemTemp.length == 2) {
                                this.character.addItem(temp.itemInRoom(itemTemp[1]));
                                this.character.getCurrentRoom().updateItems("remove", temp.itemInRoom(itemTemp[1]));
                            } else if (itemTemp.length == 3) {
                                this.character.addItem(temp.itemInRoom(itemTemp[1] + " " + itemTemp[2]));
                                this.character.getCurrentRoom().updateItems("remove",
                                        temp.itemInRoom(itemTemp[1] + " " + itemTemp[2]));
                            }
                        }
                        case "drop" -> {
                            Room temp = this.character.getCurrentRoom();
                            so.println("Drop what?\n");
                            String[] itemTemp = reader.nextLine().toLowerCase().split(" ");
                            if (itemTemp.length == 2) {
                                Item item = this.character.getItem(itemTemp[1]);
                                this.character.removeItem(itemTemp[1]);
                                this.character.getCurrentRoom().updateItems("add", item);
                            } else if (itemTemp.length == 3) {
                                Item item = this.character.getItem(itemTemp[1] + " " + itemTemp[2]);
                                this.character.removeItem(itemTemp[1] + " " + itemTemp[2]);
                                this.character.getCurrentRoom().updateItems("add", item);
                            }
                        }
                        case "door", "enter" -> {
                            if (character.enterDoor() != null) {
                                character.setCurrentRoom(character.enterDoor());
                                character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Nothing to enter!");
                            }
                        }
                        case "trap", "trapdoor" -> {
                            if (character.enterTrapDoor() != null) {
                                character.setCurrentRoom(character.enterTrapDoor());
                                character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Nothing to enter!");
                            }
                        }
                        case "examine" -> {
                            so.println("Examine what?\n");
                            String[] itemTemp = reader.nextLine().toLowerCase().split(" ");
                            so.println(this.character.getItem(itemTemp[1]).getDesc());
                        }
                        case "exit" -> {
                            if (character.exitDoor() != null) {
                                character.setCurrentRoom(character.exitDoor());
                                character.getCurrentRoom().enter();
                                flag = true;
                            } else {
                                so.println("Nothing to exit!");
                            }
                        }
                        case "attack" -> {
                            so.println("attack what?");
                            String[] attackInput = reader.nextLine().split(" ");
                            so.println("With what weapon?");
                            String[] weaponInput = reader.nextLine().split(" ");
                            if (attackInput[1].toLowerCase().equals("goblin")
                                    || attackInput[1].toLowerCase().equals("ogre")) {
                                Monster monsterTemp = this.character.getCurrentRoom().getMonster();
                                if (monsterTemp != null && monsterTemp.getHp() > 0) {
                                    Weapon weaponTemp = this.character.getWeapon(weaponInput[1] + " " + weaponInput[2]);

                                    if (weaponTemp != null) {
                                        weaponTemp.use(monsterTemp);

                                        if (attackInput[1].toLowerCase().equals("ogre") && monsterTemp.getHp() <= 0) {
                                            so.println("Game over, you defeated the hardest boss in this game!!!");
                                            this.character.setHealth(-1);
                                        } else {
                                            this.character.takedamage(monsterTemp.getDmg());
                                        }
                                    } else {
                                        so.println("You don't have a(n) " + weaponInput[1] + " " + weaponInput[2]);
                                        this.character.takedamage(monsterTemp.getDmg());
                                    }
                                } else {
                                    so.println("NICE JOB YOU DEFEATED THE MONSTER!");
                                }
                            }

                        }
                        case "status" -> {
                            so.println("Your hp is: " + this.character.getHealth());
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
        } else if (lineArray.length == 2) {
            if (lineArray[0].equals("exit")) {
                parseLine(lineArray[0]);
            } else {
                parseLine(lineArray[1]);
            }
        }

    }

    public boolean validateLine(String line) {
        if (line.equals("")) {
            return false;
        }
        line = line.trim();
        String[] lines = line.split("\\s+");
        if (lines.length == 0) {
            return false;
        } else if (lines.length == 1) {
            for (String command : oneWordCommands) {
                if (line.equals(command)) {
                    return true;
                }
            }
            return true;
        } else if (lines.length == 2) {
            for (String verb : verbs) {
                if (lines[0].equals(verb)) {
                    for (String word : possibleSecondWords) {
                        if (lines[1].equals(word)) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } else if (lines.length == 3) {
            for (String verb : verbs) {
                if (lines[0].equals(verb)) {
                    for (int i = 0; i < possibleSecondWords.length; i++) {
                        if (lines[1].equals(possibleSecondWords[i])) {
                            // if(lines[1].equals(possibleSecondWords[i + 1])) {
                            // return true;
                            // }
                            return true;
                        }
                    }
                }
            }
            return false;
        } else if (lines.length == 4) {
            for (String verb : verbs) {
                if (lines[0].equals(verb)) {
                    for (int i = 0; i < possibleSecondWords.length; i++) {
                        if (lines[1].equals(possibleSecondWords[i])) {
                            if (lines[2].equals(possibleSecondWords[i + 1])) {
                                if (lines[3].equals(possibleSecondWords[i + 2])) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public CommandParser(Character character) {
        this.character = character;
    }

}

class Command {

}