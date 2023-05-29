package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;

public class Remove extends VillagersSubCommand {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
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
            if (entityConfig.hasSelected(player)) {
                if (entityConfig.getSelected(player) != null) {
                    message.send(player, "&6You removed&f "+ entityConfig.getSelected(player).getName());
                    entityConfig.getSelected(player).remove();
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