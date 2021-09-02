package com.github.soramame0256.guitest.Guis;

import com.github.soramame0256.guitest.Gui;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TestGui {
    public static Gui testGui;
    public static void setup(){
        testGui = Gui.Builder.getBuilder()
                .setRow(6)
                .setTitle("テスト")
                .setStealable(false)
                .setDefaultItem(new ItemStack(Material.BEDROCK))
                .build();
        testGui.setSlot(() -> testGui.getLatestClickedPlayer().sendMessage("テスト"),0, new ItemStack(Material.DIAMOND));
    }
}
