package core;

import java.util.HashMap;
import java.util.Map;

/**
 * Trida mistnosti
 * Obsahuje metody pro praci s vlastnistmi mistnosti
 * Obsahuje metody pro praci s mapami itemu, npc, exitu
 */
public class Room {
    private String id;
    private String name;
    private String description;

    private Map<String, String> exits;
    private Map<String, String> items;
    private Map<String, String> npcs;

    private boolean explored;
    private boolean blocked;
    private String requiredItemId;
    private boolean requiredHelp;

    public Room(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.exits = new HashMap<String, String>();
        this.items = new HashMap<String, String>();
        this.npcs = new HashMap<String, String>();

        this.explored = false;
        this.blocked = false;
        this.requiredItemId = null;
        this.requiredHelp = false;
    }

    //info
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    //exity
    public void addExit(String direction, String room) {
        exits.put(direction, room);
    }
    public String getExit(String direction) {
        return exits.get(direction);
    }

    //prozkoumavani
    public void explore() {
        this.explored = true;
    }
    public boolean isExplored() {
        return explored;
    }

    //itemy
    public boolean addItem(String item) {
        items.put(item, item);
        return true;
    }
    public String removeItem(String itemId) {
        return items.remove(itemId);
    }
    public boolean hasItem() {
        if (items.isEmpty()) {
            return false;
        } else return true;
    }
    public Map<String, String> getItems() {
        return items;
    }
    public String getItem(String itemId) {
        return items.get(itemId);
    }

    //npc
    public boolean addNpc(String npc) {
        npcs.put(npc, npc);
        return true;
    }
    public Map<String, String> getNpcs() {
        return npcs;
    }
    public boolean hasNpc() {
        if (npcs.isEmpty()){
            return false;
        } else return true;
    }

    //blokovani
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked, String requiredItemId, boolean requiredHelp) {
        this.blocked = blocked;
        this.requiredItemId = requiredItemId;
        this.requiredHelp = requiredHelp;
    }
    public String getRequiredItemId() {
        return requiredItemId;
    }
    public boolean isRequiredHelp() {
        return requiredHelp;
    }
}
