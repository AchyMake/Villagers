package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;

public class Silent extends VillagersSubCommand {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
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
            if (entityConfig.hasSelected(player)) {
                if (entityConfig.getSelected(player) != null) {
                    boolean value = Boolean.valueOf(args[1]);
                    if (value) {
                        entityConfig.getSelected(player).setSilent(true);
                        message.send(player, "&6You set&f "+ entityConfig.getSelected(player).getName() + "&6 silent to&f true");
                        entityConfig.removeSelected(player);
                    } else {
                        entityConfig.getSelected(player).setSilent(false);
                        message.send(player, "&6You set&f "+ entityConfig.getSelected(player).getName() + "&6 silent to&f false");
                        entityConfig.removeSelected(player);
                    }
                } else {
                    message.send(player, "&cYou have to select a villager");
                }
            } else {
                message.send(player, "&cYou have to select a villager");
            }
        }
    }
}