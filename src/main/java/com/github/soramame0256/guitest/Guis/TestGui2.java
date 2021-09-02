package com.github.soramame0256.guitest.Guis;

import com.github.soramame0256.guitest.Gui;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestGui2 {
    public static Gui testGui;
    public static void setup(){
        testGui = Gui.Builder.getBuilder()
                .setRow(6)
                .setTitle("テスト123")
                .setStealable(false)
                .setDefaultItem(new ItemStack(Material.COMMAND))
                .build();
        testGui.setSlot(() -> testGui.getLatestClickedPlayer().sendMessage("テスト123"),0, new ItemStack(Material.WOOD));
    }
}
