package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.Player;

public class Remove extends VillagersSubCommand {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public String getName() {
        return "remove";
    }
    public String getDescription() {
        return "removes villager npc";
    }
    public String getSyntax() {
        return "/villagers remove";
    }
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            if (getDatabase().hasSelected(player)) {
                if (getDatabase().getSelected(player) != null) {
                    Villagers.send(player, "&6You removed&f " + getDatabase().getSelected(player).getName());
                    getDatabase().getSelected(player).remove();
                    getDatabase().removeSelected(player);
                } else {
                    Villagers.send(player, "&cYou have to select a villager");
                }
            } else {
                Villagers.send(player, "&cYou have to select a villager");
            }
        }
    }
}