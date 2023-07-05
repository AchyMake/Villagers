package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.Player;

public class Adult extends VillagersSubCommand {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public String getName() {
        return "adult";
    }
    public String getDescription() {
        return "changes villager types";
    }
    public String getSyntax() {
        return "/villagers silent true/false";
    }
    public void perform(Player player, String[] args) {
        if (args.length == 2) {
            if (getDatabase().hasSelected(player)) {
                if (getDatabase().getSelected(player) != null) {
                    boolean value = Boolean.valueOf(args[1]);
                    if (value) {
                        getDatabase().getSelected(player).setAdult();
                        Villagers.send(player, "&6You set&f " + getDatabase().getSelected(player).getName() + "&6 to&f adult");
                        getDatabase().removeSelected(player);
                    } else {
                        getDatabase().getSelected(player).setBaby();
                        Villagers.send(player, "&6You set&f " + getDatabase().getSelected(player).getName() + "&6 to&f baby");
                        getDatabase().removeSelected(player);
                    }
                } else {
                    Villagers.send(player, "&cYou have to select a villager");
                }
            } else {
                Villagers.send(player, "&cYou have to select a villager");
            }
        }
    }
}