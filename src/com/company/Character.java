package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.ZorkWSingleton.so;

public class Character {
    // add hp for character
    // take dmg? maybe

    private List<Item> inventory; // change the type of list
    private int healthbar;

    public Character(){
        inventory = new ArrayList<>();
        healthbar = 100;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    Room getExit(String direction){
            return this.currentRoom.getExit(direction.toLowerCase());
    }

    Room enterDoor(){
        return this.currentRoom.enterDoor();
    }
    Room enterTrapDoor(){
        return this.currentRoom.enterTrapDoor();
    }
    Room exitDoor(){
        return this.currentRoom.exitDoor();
    }
    private Room currentRoom;

    public void printInventory(){
        if(inventory.isEmpty()) so.println("Inventory empty!");
        else if(inventory.size()>0){
            for (Item item : inventory) {
                System.out.println(item.getItemName());
            }
        }
    }

    public void takedamage(int damageTaken){
        this.healthbar -= damageTaken;
    }
    public void addItem(Item item){
        if(inventory.size()<=6) {
            inventory.add(item);
        }
        else{
            so.println("Inventory Full!!! Drop item to add another item");
        }
    }

    public void removeItem(String item){
        if(!this.inventory.isEmpty()) {
            for (Item it : this.inventory) {
                if (it.getItemName().equals(item)) {
                    this.inventory.remove(it);
                    break;
                } else {
                    so.println("Item is not in your inventory.");
                }
            }
        }
        else{
            so.println("Inventory is empty!");
        }
    }
    public Weapon getWeapon(String itemName){
        for(Item key : inventory){
            if(key.getItemName().equals(itemName)){
                  return (Weapon)key;
//                return new Weapon(itemName,"A bright red sword is seen shining with a mysterious sinister aura. Forged from darkness and deals a lot of damage.",4,70);
            }
        }
        return null;
    }
    public Item getItem(String itemName){
        for(Item key : inventory){
            if(key.getItemName().equals(itemName)){
                return key;
            }
        }
        return null;
    }
    public int getHealth(){ return this.healthbar;}
    public void setHealth(int health){ this.healthbar = health;}
}

class Monster{ // add goblin and boss that is 3* normal monster hp , dmg method and hp method
    private int healthBar;
    private int damage;
    private String name;
    private String monsterDesc;
    private int ID;
    public Monster(){this("","",-1,0,0);}
    public Monster( String name, String monsterDesc, int ID, int healthBar, int damage){
        this.healthBar = healthBar;
        this.monsterDesc = monsterDesc;
        this.ID = ID;
        this.damage = damage;
        this.name = name;
    }

    public void takeDamage(int damageTaken){
        this.healthBar -= damageTaken;
    }
    public String getName(){ return this.name;}
//    public Monster createGoblin(String name, String desc, int healthBar, int damage){// is this really needed?
//        return new Monster(name, desc, healthBar, damage);
//    }
//    public Monster createOgre(String name, String desc, int healthBar, int damage){
//        return new Monster(name, desc, healthBar, damage);
//    }

    public String getDesc(){
        return this.monsterDesc;
    }
    public int getHp(){
        return this.healthBar;
    }
    public int getDmg() {return this.damage;}
}
