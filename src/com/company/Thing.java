package com.company;

// import java.util.Locale;

// import static com.company.ZorkWSingleton.*;

abstract class Thing { // can make it things , more vague, are they moveable or not?

    private String desc;
    private String name;

    // hard code the descriptions of the items?
    public Thing() {
        this("", "");
    }

    public Thing(String name, String desc) {
        this.name = name;
        this.desc = desc;

    }

    // public void examine(){
    // so.println(this.itemDesc);
    // }
    public String getItemName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String toString() {
        return String.format("%s %s ", checkVowl(this.name), this.name.toLowerCase());
    }

    public String checkVowl(String str) {
        str = str.toLowerCase();
        if (str.charAt(0) == 'A' || str.charAt(0) == 'E' || str.charAt(0) == 'U' || str.charAt(0) == 'I'
                || str.charAt(0) == 'O') {
            return "an";
        }
        return "a";
    }
}

class Item extends Thing {
    private String name;
    private String description;
    private String longDescription;
    private int ID;

    public Item(String name, String description, int ID) {
        super(name, description);
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

class Weapon extends Item { // only attacks monsters
    int damage;
    int ID;

    public Weapon() {
        this("", "", 0, 0);
    }

    public Weapon(String name, String desc, int ID, int damage) {
        super(name, desc, ID);
        this.damage = damage;
    }

    public void sharpenWeapon(int increaseDmg) {
        damage += increaseDmg;
    }

    public int getDmg() {
        return this.damage;
    }

    public void use(Object monster) {
        ((Monster) monster).takeDamage(this.damage);
    }
}

class WeaponSharpener extends Thing {
    int increaseDmg;

    public WeaponSharpener() {
        increaseDmg = 15;
    }

    public void use(Object weapon) {
        ((Weapon) weapon).sharpenWeapon(this.increaseDmg);
    }
}
