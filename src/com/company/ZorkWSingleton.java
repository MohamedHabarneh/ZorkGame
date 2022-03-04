package com.company;

import java.util.*;
import java.io.PrintStream;
// import static com.company.ZorkWSingleton.so;

public class ZorkWSingleton {
    static PrintStream so = System.out;

    public static void main(String[] args) {

        Maze start = new MazeGame().createMaze();
        Room r0 = start.getRoom(0);
        r0.enter();
        Scanner s = new Scanner(System.in);
        Character player = new Character();
        player.setCurrentRoom(r0);

        CommandParser command = new CommandParser(player);
        boolean flag = true;
        while (flag) {
            so.print(">");
            String input = s.nextLine();
            if (input.equals("quit") || player.getHealth() <= 0) {
                if (player.getHealth() <= 0 && player.getHealth() != -1) {
                    so.println("Game over. You have died!");
                }
                flag = false;
            }
            command.parseLine(input);
        }

    }
}

// output
//
//
// Beginning of Game
// The start of a long journey! There is a forest to the west and a house to the
// north
// There is also a mailbox here.
// There appears:
// a letter
//
// take
// Take what?
//
// take letter
// inventory
// letter
// examine letter
// examine
// Examine what?
//
// examine letter
// hello player, welcome to a game like Zork, but not Zork!” Looking North is a
// shrek house and west is a forest.
// go west
// Forest
// green grass and a lot of trees.
// There appears:
// a wooden sword
//
// go west
// forest path
// A dirt path that leads to a weird looking forest to the west and an abandoned
// house on the north side
//
// north
// South of House
// You are facing the south side of an abandoned house. There is a locked door
// and a forest path to the south.
//
// west
// West of House
// You are facing the west side of an abandoned house. To the west there is a
// path to a forest.
//
// south
// South of House
// look
// You are facing the south side of an abandoned house. There is a locked door
// and a forest path to the south.
//
// enter door
// Nothing to enter!
// go east
// East of House
// You are facing the east side of an abandoned house. There is a old broken
// wooden door and no windows.
//
// enter house
// Sorry I don't recognize that command.
// enter door
// Living room
// A messy room that has a weapon sharpener station and an obvious trap door,
// where could it lead to??
// The weapon sharpener station is useful in making weapons stronger and more
// durable.
// There appears:
// a iron sword
//
// take
// Take what?
//
// take iron sword
// inventory
// letter
// iron sword
// enter trap door
// Dungeon
// Scary and dark dungeon and it appears to contains mobs, this seems to lead to
// some where important, perhaps a boss?
//
// go west
// Trap room
// A room full of mobs and behind them is a bright red sword that appears to
// have a strong aura.
// There appears:
// a calamity sword
//
// A Stronger Goblin appears before you!!!
// A strong looking goblin with a iron coated wooden stick
// take
// Take what?
//
// take calamity sword
// inventory
// letter
// iron sword
// calamity sword
// go east
// Dungeon
// go north
// Boss room
// A scary looking monster that is 10x the size of a normal goblin and does way
// more damage, this seems like the end game.
//
// A Ogre appears before you!!!
// A strong ogre, ten times the size of a normal ogre
// attack
// attack what?
// attack ogre
// With what weapon?
// with calamity sword
// status
// Your hp is: 55
// attack
// attack what?
// attack ogre
// With what weapon?
// with calamity sword
// status
// Your hp is: 10
// attack
// attack what?
// attack ogre
// With what weapon?
// with calamity sword
// attack
// Game over. You have died!