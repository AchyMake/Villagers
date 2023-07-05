package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Type extends VillagersSubCommand {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public String getName() {
        return "type";
    }
    public String getDescription() {
        return "changes villager types";
    }
    public String getSyntax() {
        return "/villagers biomes type";
    }
    public void perform(Player player, String[] args) {
        if (args.length == 2) {
            if (getDatabase().hasSelected(player)) {
                if (getDatabase().getSelected(player) != null) {
                    getDatabase().getSelected(player).setVillagerType(Villager.Type.valueOf(args[1]));
                    Villagers.send(player, "&6You set&f " + getDatabase().getSelected(player).getName() + "&6 type to&f " + getDatabase().getSelected(player).getVillagerType().name());
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