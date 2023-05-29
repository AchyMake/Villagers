package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Type extends VillagersSubCommand {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
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
            if (entityConfig.hasSelected(player)) {
                if (entityConfig.getSelected(player) != null) {
                    entityConfig.getSelected(player).setVillagerType(Villager.Type.valueOf(args[1]));
                    entityConfig.getSelected(player).setCollidable(false);
                    message.send(player, "&6You set&f "+ entityConfig.getSelected(player).getName() + "&6 type to&f " + entityConfig.getSelected(player).getVillagerType().name());
                    entityConfig.removeSelected(player);
                } else {
                    message.send(player, "&cYou have to select a villager");
                }
            } else {
                message.send(player, "&cYou have to select a villager");
            }
        }
    }
}