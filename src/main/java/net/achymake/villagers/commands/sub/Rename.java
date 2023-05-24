package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;

public class Rename extends VillagersSubCommand {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
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
            if (entityConfig.hasSelected(player)) {
                if (entityConfig.getSelected(player) != null) {
                    StringBuilder name = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        name.append(args[i]);
                        name.append(" ");
                    }
                    message.send(player, "&6You renamed&f "+ entityConfig.getSelected(player).getName() + "&6 to&f " + message.color(name.toString().strip()));
                    entityConfig.getSelected(player).setCustomName(message.color(name.toString().strip()));
                    entityConfig.getSelected(player).setCustomNameVisible(true);
                    message.send(player, "&6You set&f "+ entityConfig.getSelected(player).getName() + "&6 profession to&f " + entityConfig.getSelected(player).getProfession().name());
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