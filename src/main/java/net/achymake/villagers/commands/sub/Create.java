package net.achymake.villagers.commands.sub;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.VillagersSubCommand;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.Player;

public class Create extends VillagersSubCommand {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public String getName() {
        return "create";
    }
    public String getDescription() {
        return "creates villager npc";
    }
    public String getSyntax() {
        return "/villagers create name";
    }
    public void perform(Player player, String[] args) {
        if (args.length >= 2) {
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                name.append(args[i]);
                name.append(" ");
            }
            getDatabase().createVillager(player, name.toString().strip());
        }
    }
}