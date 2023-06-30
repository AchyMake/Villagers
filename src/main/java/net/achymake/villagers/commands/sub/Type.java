package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Type extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    private Message getMessage() {
        return Villagers.getMessage();
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
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    getEntityConfig().getSelected(player).setVillagerType(Villager.Type.valueOf(args[1]));
                    getMessage().send(player, "&6You set&f " + getEntityConfig().getSelected(player).getName() + "&6 type to&f " + getEntityConfig().getSelected(player).getVillagerType().name());
                    getEntityConfig().removeSelected(player);
                } else {
                    getMessage().send(player, "&cYou have to select a villager");
                }
            } else {
                getMessage().send(player, "&cYou have to select a villager");
            }
        }
    }
}