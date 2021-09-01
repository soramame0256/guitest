package com.github.soramame0256.guitest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gui {
    private Inventory inventory;
    private Player LatestClickedPlayer;
    private Map<Integer,Runnable> slotrunnablemap = new HashMap<>();
    private Map<Integer,ItemStack> slotitemmap = new HashMap<>();
    private Boolean Stealable = true;
    public static List<Gui> guilist = new ArrayList<>();
    public static Map<Player,Gui> playerGuiMap = new HashMap<>();
    public Gui(int row,String title){
        inventory = Bukkit.createInventory(null, row*9,title);
        guilist.add(this);
    }
    public Inventory getGui() {
        return this.inventory;
    }

    public Boolean isPlayerViewing(Player p){
        return playerGuiMap.get(p).equals(this);
    }
    public Boolean getStealable() {
        return Stealable;
    }

    public Map<Integer, Runnable> getSlotrunnablemap() {
        return slotrunnablemap;
    }

    public Player getLatestClickedPlayer() {
        return LatestClickedPlayer;
    }
    public void close(Player p){
        playerGuiMap.remove(p);
    }

    public void setLatestClickedPlayer(Player latestClickedPlayer) {
        LatestClickedPlayer = latestClickedPlayer;
    }

    public void setSlot(Runnable r, int slot, ItemStack item){
        if(slotrunnablemap.containsKey(slot)){
            slotrunnablemap.remove(slot);
            slotrunnablemap.put(slot,r);
            slotitemmap.remove(slot);
            slotitemmap.put(slot,item);
            this.inventory.setItem(slot,item);
        }else{
            slotrunnablemap.put(slot,r);
            slotitemmap.put(slot,item);
            this.inventory.setItem(slot,item);
        }
    }
    public void setStealable(Boolean b){
        this.Stealable = b;
    }
    public void open(Player p){
        if(p.getInventory() != null){
            p.closeInventory();
        }
        p.openInventory(this.inventory);
        playerGuiMap.put(p,this);
    }
    @Override
    public String toString(){
        if(this.getLatestClickedPlayer() == null){
            this.LatestClickedPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[0];
        }
        return "Title:"+this.inventory.getTitle()+
                "LastClicked:"+this.getLatestClickedPlayer().getDisplayName()+
                "Stealable:"+this.Stealable+
                "runnable:"+this.slotrunnablemap+
                "slotitemmap:"+this.slotitemmap+
                "inventory:"+this.inventory;
    }
}
