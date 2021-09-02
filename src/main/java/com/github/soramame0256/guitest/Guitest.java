package com.github.soramame0256.guitest;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.github.soramame0256.guitest.Guis.*;

public final class Guitest extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Handler(),this);
        TestGui.setup();
        TestGui2.setup();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("testgui")) {
            if (sender instanceof Player) {
                if(args.length == 0){
                    TestGui.testGui.open((Player) sender);
                    return true;
                }
                if(args[0].equals("1")) {
                    TestGui2.testGui.open((Player) sender);
                    return true;
                }
            }
        }
        return false;
    }
}
