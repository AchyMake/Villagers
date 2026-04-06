package org.achymake.villagers.commands;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.data.Message;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VillagersCommand implements CommandExecutor, TabCompleter {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    public VillagersCommand() {
        getInstance().getCommand("villagers").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                getInstance().reload();
                player.sendMessage(getMessage().addColor("&6Villagers&f: reloaded"));
                return true;
            }
        } else if (sender instanceof ConsoleCommandSender consoleCommandSender) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                getInstance().reload();
                consoleCommandSender.sendMessage("Villagers: reloaded");
                return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        var commands = new ArrayList<String>();
        if (sender instanceof Player && args.length == 1) {
            commands.add("reload");
        }
        return commands;
    }
}