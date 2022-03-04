package com.company;

import java.util.ArrayList;
import java.util.HashMap;

import static com.company.ZorkWSingleton.so;

public abstract class Room{
        //USING FACTORY PATTERN
        //can just say player can't go that direction instead of creating walls
        String description;
        String name;
        int id;
        Monster monster;
        private boolean visited; 
        HashMap<String, com.company.Room> connections; 
        HashMap<Integer, ArrayList<Item>> itemList;
        ArrayList<Item> items;
        private ArrayList<Door> doors = new ArrayList<>();
        private ArrayList<Monster> monsterList = new ArrayList<>();

        private Room[] arr;
        //use hashmap to connect doors to room, global object
        public Room(){
            connections = new HashMap<>();
            itemList = new HashMap<>();
            items = new ArrayList<>();
            visited = false;
            arr = new com.company.Room[4];
            monster = new Monster();
        }
        String shortDesc(){
            return this.name;
        } // basic desc
        String longDesc(){ // can be used for when the player calls the Look action

            String longDesc = this.description + "\n";
            if(items.size()>0){
                longDesc+="There appears: \n";
                for(Item item : items){
                    longDesc+= item.toString() + "\n";
                }
            }
            return longDesc;
        }
        public Room getExit(String direction){
                return this.connections.get(direction.toLowerCase());
        } // get the room based on the set dir
        void updateItems(String command,Item item){
            if(command.equals("remove") && items.contains(item)){
                items.remove(item);
            }
            else if(command.equals("add")){
                items.add(item);
            }
        }
        void enter(){
            so.println(shortDesc());
            if(!this.visited){
                    so.println(longDesc());
                    this.visited = true;
            }
            if(!monsterList.isEmpty()){
                so.println("A " + this.monsterList.get(0).getName() +" appears before you!!!");
                so.println(monsterList.get(0).getDesc());
            }

        }
        Item itemInRoom(String item){ // needed??
            if(!itemList.get(id).isEmpty()){
                for(Item s : itemList.get(id)){
                    if(s.getItemName().equals(item.toLowerCase())){
                        return s;
                    }
                }
            }
            return null;
        }

//        void setConnection(String direction, Room room){
//            if(room!=null) {
//                switch (direction) {
//                    case "north" -> {
//                        connections.put("north", room);
//                        room.connections.put("south", this);
//
//                    }
//                    case "south" -> {
//                        connections.put("south", room);
//                        room.connections.put("north", this);
//
//                    }
//                    case "west" -> {
//                        connections.put("west", room);
//                        room.connections.put("east", this);
//
//                    }
//                    case "east" -> {
//                        connections.put("east", room);
//                        room.connections.put("west", this);
//
//                    }
//
//
//                }
//            }
//
//        }
        void setConnections(Room northR, Room eastR, Room southR, Room westR){
            connections.put("north",northR);
            connections.put("east",eastR);
            connections.put("south",southR);
            connections.put("west",westR);
        }
        void addItem(Item i){
            items.add(i);
        }

//        void getDoors(){ // not needed
//            for(Door door : doors){
//                System.out.println(door.desc());
//            }
//        }
        void setDoors(Door northD , Door southD, Door eastD, Door westD, Room path, Room def , Room trapdoor){
            if(northD!=null) { this.doors.add(northD);}
            if(southD!=null){this.doors.add(southD);}
            if(eastD!=null){this.doors.add(eastD);}
            if(westD!=null){this.doors.add(westD);}
            this.arr[0]=path;
            this.arr[1]=def;
            this.arr[2]=trapdoor;

        }
        void setMonster(Monster monster){
            this.monsterList.add(monster);
        }
        Monster getMonster(){
            Monster temp = this.monsterList.get(0);
            return temp;
        }
        int checkMonsterExists(){ return this.monsterList.size();}
        int getRoomNo(){ return this.id;}
        com.company.Room enterDoor(){
            return this.arr[0];
        }
        com.company.Room enterTrapDoor(){
            return this.arr[2];
        }
        com.company.Room exitDoor(){ return this.arr[1];}
        void doorConnections(){
            for(com.company.Room r : this.arr){
                so.println(r.shortDesc());
            }
        }

    }

class NoDoor extends Door {
    public NoDoor() {
        super(-1, "NoDoor", false, false, false);
    }
}
class Start extends Room{
    public Start(int id){
        this.id=id;
        name = "Beginning of Game";
        description = "The start of a long journey! There is a forest to the west and a house to the north\nThere is also a mailbox here.";
        itemList.put(this.id,items);
    }
}

class AbandonedHouse extends Room{ // needed?
    public AbandonedHouse(int id){
        this.id=id;
        name = "Abandoned house";
        description = "An abandoned house that appears to be old.";
        itemList.put(this.id,items);
    }
}

class ShrekHouse extends Room{ // needed?
    public ShrekHouse(int id){
        this.id=id;
        name = "Shrek house";
        description = "There appears to be an open front door.";
        itemList.put(this.id,items);
    }
}
class NorthShrekHouse extends Room{
    public NorthShrekHouse(int id){
        this.id=id;
        name = "North of House";
        description = "You are facing the north side of Shrek house. To the north there appears to be a dirt path to a forest.";
        itemList.put(this.id,items);
    }
}
class SouthShrekHouse extends Room{
    public SouthShrekHouse(int id){
        this.id=id;
        name = "South of House";
        description = "You are facing the south side of shrek house. There appears to be a slightly opened front door.";
        itemList.put(this.id,items);
    }
}
class WestShrekHouse extends Room{
    public WestShrekHouse(int id){
        this.id=id;
        name = "West of House";
        description = "You are facing the west side of shrek house. A path leads to an abandoned house to the west.";
        itemList.put(this.id,items);
    }
}
class EastShrekHouse extends Room{
    public EastShrekHouse(int id){
        this.id=id;
        name = "East of House";
        description = "You are facing the east side of shrek house. There are no doors or windows.";
        itemList.put(this.id,items);
    }
}

class NorthSideAbandonedHouse extends Room{
    public NorthSideAbandonedHouse(int id){
        this.id=id;
        name = "North of House";
        description = "You are facing the north side of an abandoned house. No other paths are visible.";
        itemList.put(this.id,items);
    }
}
class SouthSideAbandonedHouse extends Room{
    public SouthSideAbandonedHouse(int id){
        this.id=id;
        name = "South of House";
        description = "You are facing the south side of an abandoned house. There is a locked door and a forest path to the south.";
        itemList.put(this.id,items);
    }
}
class WestSideAbandonedHouse extends Room{
    public WestSideAbandonedHouse(int id){
        this.id=id;
        name = "West of House";
        description = "You are facing the west side of an abandoned house. To the west there is a path to a forest.";
        itemList.put(this.id,items);
    }
}
class EastSideAbandonedHouse extends Room{
    public EastSideAbandonedHouse(int id){
        this.id=id;
        name = "East of House";
        description = "You are facing the east side of an abandoned house. There is a old broken wooden door and no windows.";
        itemList.put(this.id,items);
    }
}
class Forest extends Room{
    public Forest(int id){
        this.id=id;
        name = "Forest";
        description = "green grass and a lot of trees.";
        itemList.put(this.id,items);
    }
}

class Dungeon extends Room{
    public Dungeon(int id){
        this.id=id;
        name = "Dungeon";
        description = "Scary and dark dungeon and it appears to contains mobs, this seems to lead to some where important, perhaps a boss?";
    }
}

class TrapRoom extends Room{
    public TrapRoom(int id){
        this.id=id;
        name = "Trap room";
        description ="A room full of mobs and behind them is a bright red sword that appears to have a strong aura.";
        itemList.put(this.id,items);
    }
}
class Kitchen extends Room{
    public Kitchen(int id){
        this.id=id;
        name = "the Kitchen";
        description ="A clean kitchen that has a has a table and chair. The table has food prepared on it. To the east is a basement with little light";
        itemList.put(this.id,items);
    }
}
class Basement extends Room{
    public Basement(int id){
        this.id=id;
        name = "Basement";
        description ="A spooky and dark basement that barely has any light.";
        itemList.put(this.id,items);
    }
}
class Bedroom extends Room{
    public Bedroom(int id){
        this.id=id;
        name = "Bedroom";
        description ="A bed on the floor with pile of stinking clothes. A strong unpleasant smell fills up the room.";
        itemList.put(this.id,items);
    }
}
class Bathroom extends Room{
    public Bathroom(int id){
        this.id=id;
        name = "Bathroom";
        description ="An old broken toilet with a dirty sink that has mold around it and with a cracked mirror and ear wax on walls.";
        itemList.put(this.id,items);
    }
}
class ForestPath extends Room{
    public ForestPath(int id){
        this.id=id;
        name = "forest path";
        description ="A dirt path that leads to a weird looking forest to the west and an abandoned house on the north side";
        itemList.put(this.id,items);
    }
}
class BossRoom extends Room{
    public BossRoom(int id){
        this.id=id;
        name = "Boss room";
        description ="A scary looking monster that is 10x the size of a normal goblin and does way more damage, this seems like the end game.";
        itemList.put(this.id,items);
    }
}
class TrapForest extends Room{
    public TrapForest(int id){
        this.id=id;
        name = "Trap forest";
        description ="A trap forest with a goblin and no other paths besides the forest path you came from.";
        itemList.put(this.id,items);
    }
}
class LivingRoom extends Room{
    public LivingRoom(int id){
        this.id=id;
        name = "Living room";
        description ="A messy room that has a weapon sharpener station and an obvious trap door, where could it lead to??" +
                "\nThe weapon sharpener station is useful in making weapons stronger and more durable.";
        itemList.put(this.id,items);
    }
}

//abstract class RoomType{ // similar to PizzaStore
//
//    abstract Room makeRoom(String item);
//    public Room updateRoom(String type){
//        Room room = makeRoom(type);
//        return room;
//    }
//}
class MazeGame{
    public static void main(String args[]){
        Maze m = new MazeGame().createMaze();
    }
    public Maze makeMaze(){
        return new Maze();
    }
    private Room makeRoom(String roomName, int n) {
        return switch (roomName) {
            case "Start" -> new Start(n);
            case "Abandoned House" ->
                    // change new Room to new AbandonedHouse???
                    new AbandonedHouse(n);
            case "Shrek House" -> new ShrekHouse(n);
            case "Dungeon" -> new Dungeon(n);
            case "Forest" -> new Forest(n);
            case "North of Abandoned House" -> new NorthSideAbandonedHouse(n);
            case "South of Abandoned House" -> new SouthSideAbandonedHouse(n);
            case "East of Abandoned House" -> new EastSideAbandonedHouse(n);
            case "West of Abandoned House" -> new WestSideAbandonedHouse(n);
            case "North of Shrek House" -> new NorthShrekHouse(n);
            case "South of Shrek House" -> new SouthShrekHouse(n);
            case "East of Shrek House" -> new EastShrekHouse(n);
            case "West of Shrek House" -> new WestShrekHouse(n);
            case "Trap Room" -> new TrapRoom(n);
            case "Kitchen" -> new Kitchen(n);
            case "Basement" -> new Basement(n);
            case "Trap Forest" -> new TrapForest(n);
            case "Living Room" -> new LivingRoom(n);
            case "Boss Room" -> new BossRoom(n);
            case "Bedroom" -> new Bedroom(n);
            case "Forest Path" -> new ForestPath(n);
            case "Bathroom" -> new Bathroom(n);
            default -> null;
        };
    }
    private Door makeDoor(String name,int ID){
        return new Door(ID, name,false,false,false);
    }
    public Maze createMaze() {
        Maze aMaze = makeMaze();
        Room start = makeRoom("Start", 0);
        Room southOfHouse = makeRoom("South of Shrek House", 1);
        Room westOfHouse = makeRoom("West of Shrek House", 2);
        Room eastOfHouse = makeRoom("East of Shrek House", 3);
        Room northOfHouse = makeRoom("North of Shrek House", 4);
        Room forest1 = makeRoom("Forest", 5); // bottom right forest
        Room forestPath = makeRoom("Forest Path", 6);
        Room trapForest = makeRoom("Trap Forest", 7);
        Room kitchen = makeRoom("Kitchen", 8);
        Room bedroom = makeRoom("Bedroom", 9);
        Room bathroom = makeRoom("Bathroom", 10);
        Room basement = makeRoom("Basement", 11);
        Room forest2 = makeRoom("Forest", 12); // Top right forest
        Room southOfAbandonedHouse = makeRoom("South of Abandoned House", 13);
        Room westOfAbandonedHouse = makeRoom("West of Abandoned House", 14);
        Room eastOfAbandonedHouse = makeRoom("East of Abandoned House", 15);
        Room northOfAbandonedHouse = makeRoom("North of Abandoned House", 16);
        Room livingRoom = makeRoom("Living Room", 17);
        Room forest3 = makeRoom("Forest", 18); // Top left forest
        Room dungeon = makeRoom("Dungeon", 19);
        Room trapRoom = makeRoom("Trap Room", 20);
        Room bossRoom = makeRoom("Boss Room", 21);
        Room trapDoor = makeRoom("Trap door",22);


        ArrayList<Door> tempD = new ArrayList<>();
        Door north = makeDoor("North door",1); // not ideal, but
        Door south = makeDoor("South door",2);
        Door west = makeDoor("West door",3);
        Door east = makeDoor("East door",4);
//        Door northV2 = new Door("North Door", true, true, true); // doors that require a key
        Door southV2 = new Door(5,"South Door", true, true, true);
//        Door westV2 = new Door("West Door", true, true, true);
//        Door eastV2 = new Door("East Door", true, true, true);
        NoDoor noDoor = new NoDoor();
        southOfHouse.setDoors(north, noDoor, noDoor, noDoor, kitchen, southOfHouse, null); // southside to kitchen
        kitchen.setDoors(noDoor, south, noDoor, noDoor, kitchen, southOfHouse, null); // kitchen to southside
        westOfHouse.setDoors(noDoor, noDoor, east, noDoor, bedroom, westOfHouse, null); // west side to kitchen
        bedroom.setDoors(noDoor, noDoor, noDoor, west, bedroom, westOfHouse, null); // bedroom to west side
        eastOfAbandonedHouse.setDoors(noDoor, noDoor, noDoor, west, livingRoom, eastOfAbandonedHouse, null); // east side to living room
        livingRoom.setDoors(noDoor, noDoor, noDoor, west, livingRoom, eastOfAbandonedHouse, dungeon); // living room to east side
        Item letter = new Item("letter",
                "hello player, welcome to a game like Zork, but not Zork!” Looking North is a shrek house and west is a forest.", 0);
        Item bread = new Item("bread", "A freshly baked slice of bread.", 1);
        Item woodenShield = new Item("wooden shield",
                "A wooden shield, with battle damage. This shield will help in future battles with blocking attacks.", 2);
        Item biggerWoodenShield = new Item("larger wooden shield",
                "A bigger wooden shield, with iron coating. This shield will help in future battles with blocking attacks.", 3);
        Item glassBottle = new Item("glass bottle", "An empty dirty glass bottle with nothing inside of it.", 4);
        Item key = new Item("key", "A silver key that can open a certain door.", 5);
        Item waterBottle = new Item("water bottle", "A bottle which contains water inside of it.", 6);
        Weapon woodenSword = new Weapon("wooden sword", "A slightly long wooden sword with a slightly sharp tip", 1, 15);
        Weapon dullKnife = new Weapon("dull knife", "A dull knife that doesn't have a pointy tip and is very small", 2, 10);
        Weapon ironSword = new Weapon("iron sword",
                "An Iron sword that appears to be rusty and does not have a pointy tip", 3, 25);
        Weapon calamitySword = new Weapon("calamity sword",
                "A bright red sword is seen shining with a mysterious sinister aura. Forged from darkness and deals a lot of damage", 4, 70);

        start.addItem(letter); // start
        forest1.addItem(woodenSword); //forest
        kitchen.addItem(bread); // kitchen
        kitchen.addItem(glassBottle); // kitchen
        kitchen.addItem(waterBottle); // kitchen
        kitchen.addItem(dullKnife); // kitchen
        basement.addItem(biggerWoodenShield); // basement
        basement.addItem(key); // basement
        bathroom.addItem(woodenShield); // bathroom
        livingRoom.addItem(ironSword); // living room
        trapRoom.addItem(calamitySword); // trap room

//      North East South West
        start.setConnections(southOfHouse, null, null, forest1); // start
        forest1.setConnections(null, start, null,forestPath);
        southOfHouse.setConnections(null,eastOfHouse,start,westOfHouse); // south shrek
        westOfHouse.setConnections(northOfHouse,null,southOfHouse,eastOfAbandonedHouse); // west shrek
        eastOfHouse.setConnections(northOfHouse,null,southOfHouse,null); // east shrek
        northOfHouse.setConnections(forest2,eastOfHouse,null,westOfHouse); // north shrek
        forestPath.setConnections(southOfAbandonedHouse, forest1, null, trapForest); // forest path
        trapForest.setConnections(null,forestPath,null,null);
        westOfAbandonedHouse.setConnections(northOfAbandonedHouse,null,southOfAbandonedHouse,forest3); // west abandoned
        southOfAbandonedHouse.setConnections(null,eastOfAbandonedHouse,forestPath,westOfAbandonedHouse);
        eastOfAbandonedHouse.setConnections(northOfAbandonedHouse,westOfHouse,southOfAbandonedHouse,null);
        northOfAbandonedHouse.setConnections(null,eastOfAbandonedHouse,null,westOfAbandonedHouse);
        kitchen.setConnections(bedroom,null,basement,null);
        bedroom.setConnections(null,bathroom,kitchen,null);
        bathroom.setConnections(null,null,null,bedroom);
        basement.setConnections(kitchen,null,null,null);
        forest2.setConnections(null,null,northOfHouse,forest3);
        forest3.setConnections(null,forest2,westOfAbandonedHouse,null);
        trapRoom.setConnections(null,dungeon,null,null);
        bossRoom.setConnections(null,null,null,null); // end game
        dungeon.setConnections(bossRoom,null,null,trapRoom); // dungeon


        Monster m1 = new Monster("Goblin", "A weak looking goblin with a wooden stick", 1, 50, 25);
        Monster m2 = new Monster("Stronger Goblin", "A strong looking goblin with a iron coated wooden stick", 2, 75, 30);
        Monster m3 = new Monster("Ogre", "A strong ogre, ten times the size of a normal ogre", 3, 150, 45);


        trapForest.setMonster(m1);
        trapRoom.setMonster(m2);
        bossRoom.setMonster(m3);

        aMaze.addRoom(start); aMaze.addRoom(southOfHouse); aMaze.addRoom(westOfHouse); aMaze.addRoom(eastOfHouse); aMaze.addRoom(northOfHouse);
        aMaze.addRoom(forest1); aMaze.addRoom(forestPath); aMaze.addRoom(trapForest); aMaze.addRoom(kitchen);aMaze.addRoom(bedroom);
        aMaze.addRoom(bathroom); aMaze.addRoom(basement); aMaze.addRoom(forest2); aMaze.addRoom(southOfAbandonedHouse);
        aMaze.addRoom(westOfAbandonedHouse); aMaze.addRoom(eastOfAbandonedHouse); aMaze.addRoom(northOfAbandonedHouse); aMaze.addRoom(livingRoom); aMaze.addRoom(forest3);
        aMaze.addRoom(dungeon); aMaze.addRoom(trapRoom); aMaze.addRoom(bossRoom);
        return aMaze;
    }
}
abstract class MapSite{
    public abstract void enter();
}
class Maze{
    private HashMap<Integer,Room> roomMap;

    public Maze(){
        roomMap = new HashMap<>();
    }
    void addRoom(Room room){ roomMap.put(room.getRoomNo(),room);}
    Room getRoom(int roomNo){ return roomMap.get(roomNo);}
}

class Door extends Room{
    private String name;
    private boolean key;
    private boolean locked;
    private HashMap<String,Room> connects;
    private boolean closed;
    private int ID;

    public Door(int ID, String name, boolean key, boolean locked, boolean closed){
        this.ID= ID;
        this.name = name;
        this.key = key;
        this.locked = locked;
        this.connects = new HashMap<>();
        this.closed = closed;
    }
    public String desc(){
        return this.name;
    }

}
