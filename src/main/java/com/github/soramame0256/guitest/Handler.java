package com.github.soramame0256.guitest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class Handler implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
//        e.getWhoClicked().closeInventory();
        if(e.getClickedInventory() == null) return;
        if(!Gui.playerGuiMap.containsKey((Player) e.getWhoClicked())) return;
        Gui gui = Gui.playerGuiMap.get((Player)e.getWhoClicked());
        if (gui.isPlayerViewing((Player) e.getWhoClicked())) {
            gui.setLatestClickedPlayer((Player) e.getWhoClicked());
            if (!gui.getStealable()) {
                e.setCancelled(true);
            }
            if (gui.getSlotRunnableMap().containsKey(e.getSlot())) {
                gui.getSlotRunnableMap().get(e.getSlot()).run();
            }
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
        if(!Gui.playerGuiMap.containsKey((Player) e.getPlayer())) return;
        Gui.playerGuiMap.get((Player)e.getPlayer()).close((Player)e.getPlayer());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(!Gui.playerGuiMap.containsKey(e.getPlayer())) return;
        Gui.playerGuiMap.get(e.getPlayer()).close(e.getPlayer());
    }
}
