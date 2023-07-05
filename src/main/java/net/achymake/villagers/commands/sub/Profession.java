package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Profession extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public String getName() {
        return "profession";
    }
    public String getDescription() {
        return "changes villager profession";
    }
    public String getSyntax() {
        return "/villagers profession type";
    }
    public void perform(Player player, String[] args) {
        if (args.length == 2) {
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    getEntityConfig().getSelected(player).setProfession(Villager.Profession.valueOf(args[1]));
                    Villagers.send(player, "&6You set&f "+ getEntityConfig().getSelected(player).getName() + "&6 profession to&f " + getEntityConfig().getSelected(player).getProfession().name());
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