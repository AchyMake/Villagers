package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Player;

public class Silent extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public String getName() {
        return "silent";
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
                        getEntityConfig().getSelected(player).setSilent(true);
                        Villagers.send(player, "&6You set&f " + getEntityConfig().getSelected(player).getName() + "&6 silent to&f true");
                        getEntityConfig().removeSelected(player);
                    } else {
                        getEntityConfig().getSelected(player).setSilent(false);
                        Villagers.send(player, "&6You set&f " + getEntityConfig().getSelected(player).getName() + "&6 silent to&f false");
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