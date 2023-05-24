package net.achymake.villagers.files;

import net.achymake.villagers.Villagers;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
    private final Villagers villagers;
    public Message(Villagers villagers) {
        this.villagers = villagers;
    }
    public void send(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }
    public void sendLog(String message) {
        villagers.getServer().getConsoleSender().sendMessage("[" + villagers.getName() + "] " + message);
    }
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}