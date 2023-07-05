package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Player;

public class Rename extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public String getName() {
        return "rename";
    }
    public String getDescription() {
        return "changes villager name";
    }
    public String getSyntax() {
        return "/villagers rename name";
    }
    public void perform(Player player, String[] args) {
        if (args.length >= 2) {
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    StringBuilder name = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        name.append(args[i]);
                        name.append(" ");
                    }
                    Villagers.send(player, "&6You renamed&f "+ getEntityConfig().getSelected(player).getName() + "&6 to&f " + Villagers.addColor(name.toString().strip()));
                    getEntityConfig().getSelected(player).setCustomName(Villagers.addColor(name.toString().strip()));
                    getEntityConfig().removeSelected(player);
                } else {
                    Villagers.send(player, "&cYou have to select a villager");
                }
            } else {
                Villagers.send(player, "&cYou have to select a villager");
            }
        }
    }
}