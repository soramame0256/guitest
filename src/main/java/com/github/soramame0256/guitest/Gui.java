package com.github.soramame0256.guitest;

import javafx.util.Builder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gui {
    private Inventory inventory;
    private Player latestClickedPlayer;
    private Map<Integer,Runnable> slotRunnableMap = new HashMap<>();
    private Map<Integer,ItemStack> slotItemMap = new HashMap<>();
    private Boolean stealable;
    public static List<Gui> guiList = new ArrayList<>();
    public static Map<Player,Gui> playerGuiMap = new HashMap<>();
    private Gui(Builder b){
        inventory = Bukkit.createInventory(null, b.row*9,b.title);
        stealable = b.stealable;
        for(int i=0;i < b.row*9;i++){
            this.setSlot(()->{},i,b.defaultItem);
        }
        guiList.add(this);
    }
    /**
     * @return gui inventory
     */
    public Inventory getGui() {
        return this.inventory;
    }

    /**
     *
     * @param player target
     * @return whether or not
     */
    public Boolean isPlayerViewing(Player player){
        return playerGuiMap.get(player).equals(this);
    }

    /**
     * @return When player clicked gui, Whether to cancel
     */
    public Boolean getStealable() {
        return stealable;
    }

    /**
     * @return slot number and Runnable that will be fired on click
     */
    public Map<Integer, Runnable> getSlotRunnableMap() {
        return slotRunnableMap;
    }

    /**
     *
     * @return Last clicked Player
     */
    public Player getLatestClickedPlayer() {
        return latestClickedPlayer;
    }

    /**
     *
     * @param player player who want to close gui
     */
    public void close(Player player){
        playerGuiMap.remove(player);
    }

    /**
     * It should not be used.
     * May cause errors if used.
     */
    public void setLatestClickedPlayer(Player latestClickedPlayer) {
        this.latestClickedPlayer = latestClickedPlayer;
    }

    /**
     *
     * @param r Runnable that fires when the slot is clicked
     * @param slot targeted slot
     * @param item ItemStack to be set in the slot
     */
    public void setSlot(Runnable r, int slot, ItemStack item){
        if(slotRunnableMap.containsKey(slot)){
            slotRunnableMap.remove(slot);
            slotRunnableMap.put(slot,r);
            slotItemMap.remove(slot);
            slotItemMap.put(slot,item);
            this.inventory.setItem(slot,item);
        }else{
            slotRunnableMap.put(slot,r);
            slotItemMap.put(slot,item);
            this.inventory.setItem(slot,item);
        }
    }

    /**
     *
     * @param b Whether to make it possible to steal items from gui
     */
    public void setStealable(Boolean b){
        this.stealable = b;
    }

    /**
     * open this gui to target
     * @param p targeted player
     */
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
            this.latestClickedPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[0];
        }
        return "Title:"+this.inventory.getTitle()+
                "LastClicked:"+this.getLatestClickedPlayer().getDisplayName()+
                "Stealable:"+this.stealable+
                "runnable:"+this.slotRunnableMap+
                "slotitemmap:"+this.slotItemMap+
                "inventory:"+this.inventory;
    }
    public static class Builder {
        private Boolean stealable = true;
        private String title = "null";
        private Integer row = 1;
        private ItemStack defaultItem = new ItemStack(Material.AIR);

        /**
         * @return GuiBuilder Instance
         */
        public static Builder getBuilder(){
            return new Builder();
        }

        /**
         * @param stealable Whether to make it possible to steal items from gui
         */
        public Builder setStealable(Boolean stealable){
            this.stealable = stealable;
            return this;
        }

        /**
         * @param str Gui Title
         */
        public Builder setTitle(String str){
            this.title = str;
            return this;
        }

        /**
         * @param i Gui row
         */
        public Builder setRow(Integer i){
            row = i;
            return this;
        }

        /**
         * @param i ItemStack to fill Gui
         */
        public Builder setDefaultItem(ItemStack i){
            defaultItem = i;
            return this;
        }

        /**
         * @return Gui
         */
        public Gui build(){
            return new Gui(this);
        }
    }
}
