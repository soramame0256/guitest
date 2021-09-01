package com.github.soramame0256.guitest.Guis;

import com.github.soramame0256.guitest.Gui;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class testGui {
    public static Gui testGui;
    public testGui(){
        testGui = new Gui(6,"test");
        testGui.setStealable(false);
        testGui.setSlot(() -> testGui.getLatestClickedPlayer().sendMessage("テスト"),0, new ItemStack(Material.DIAMOND));
    }
}
