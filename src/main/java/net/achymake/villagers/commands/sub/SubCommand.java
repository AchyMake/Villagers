package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class SubCommand extends VillagersSubCommand {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public String getName() {
        return "command";
    }
    public String getDescription() {
        return "changes villager command";
    }
    public String getSyntax() {
        return "/villagers command console/player command";
    }
    public void perform(Player player, String[] args) {
        if (args.length >= 3) {
            if (getEntityConfig().hasSelected(player)) {
                if (getEntityConfig().getSelected(player) != null) {
                    getEntityConfig().getSelected(player).getPersistentDataContainer().set(NamespacedKey.minecraft("command-type"), PersistentDataType.STRING, args[1]);
                    StringBuilder command = new StringBuilder();
                    for (int i = 2; i < args.length; i++) {
                        command.append(args[i]);
                        command.append(" ");
                    }
                    getEntityConfig().getSelected(player).getPersistentDataContainer().set(NamespacedKey.minecraft("command"), PersistentDataType.STRING, command.toString().strip());
                    Villagers.send(player, "&6You added&f " + command.toString().strip() + "&6 with&f "+ args[1] + "&6 command");
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