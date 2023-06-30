package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.entity.Player;

public class Remove extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    private Message getMessage() {
        return Villagers.getMessage();
    }
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
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    getMessage().send(player, "&6You removed&f " + getEntityConfig().getSelected(player).getName());
                    getEntityConfig().getSelected(player).remove();
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