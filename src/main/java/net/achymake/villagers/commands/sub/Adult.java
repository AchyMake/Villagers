package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Player;

public class Adult extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
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
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    boolean value = Boolean.valueOf(args[1]);
                    if (value) {
                        getEntityConfig().getSelected(player).setAdult();
                        Villagers.send(player, "&6You set&f " + getEntityConfig().getSelected(player).getName() + "&6 to&f adult");
                        getEntityConfig().removeSelected(player);
                    } else {
                        getEntityConfig().getSelected(player).setBaby();
                        Villagers.send(player, "&6You set&f " + getEntityConfig().getSelected(player).getName() + "&6 to&f baby");
                        getEntityConfig().removeSelected(player);
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