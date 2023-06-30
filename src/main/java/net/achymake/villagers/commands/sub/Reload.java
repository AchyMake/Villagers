package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;

public class Reload extends VillagersSubCommand {
    private Villagers getPlugin() {
        return Villagers.getInstance();
    }
    private Message getMessage() {
        return Villagers.getMessage();
    }
    public String getName() {
        return "reload";
    }
    public String getDescription() {
        return "changes villager profession";
    }
    public String getSyntax() {
        return "/villagers profession type";
    }
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            getPlugin().reload();
            getMessage().send(player, "&6Villagers reloaded");
        }
    }
}