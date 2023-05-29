package net.achymake.villagers.commands;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.commands.sub.*;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

public class VillagersCommand implements CommandExecutor, TabCompleter {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final ArrayList<VillagersSubCommand> villagersSubCommands = new ArrayList<>();

    public VillagersCommand() {
        villagersSubCommands.add(new Adult());
        villagersSubCommands.add(new Create());
        villagersSubCommands.add(new Profession());
        villagersSubCommands.add(new Remove());
        villagersSubCommands.add(new Rename());
        villagersSubCommands.add(new Silent());
        villagersSubCommands.add(new SubCommand());
        villagersSubCommands.add(new Type());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                for (VillagersSubCommand commands : villagersSubCommands) {
                    if (args[0].equalsIgnoreCase(commands.getName())) {
                        commands.perform((Player) sender, args);
                    }
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> commands = new ArrayList<>();
        if (sender instanceof Player) {
            if (args.length == 1) {
                for (VillagersSubCommand commandTypes : villagersSubCommands) {
                    commands.add(commandTypes.getName());
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("profession")) {
                    for (Villager.Profession profession : Villager.Profession.values()) {
                        commands.add(profession.name());
                    }
                }
                if (args[0].equalsIgnoreCase("type")) {
                    for (Villager.Type type : Villager.Type.values()) {
                        commands.add(type.name());
                    }
                }
                if (args[0].equalsIgnoreCase("command")) {
                    commands.add("console");
                    commands.add("player");
                }
                if (args[0].equalsIgnoreCase("adult")) {
                    if (entityConfig.hasSelected((Player) sender)) {
                        commands.add(String.valueOf(entityConfig.getSelected((Player) sender).isAdult()));
                    }
                }
                if (args[0].equalsIgnoreCase("silent")) {
                    if (entityConfig.hasSelected((Player) sender)) {
                        commands.add(String.valueOf(entityConfig.getSelected((Player) sender).isSilent()));
                    }
                }
            }
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("command")) {
                    if (args[1].equalsIgnoreCase("console")) {
                        commands.add("%player%");
                    }
                }
            }
        }
        return commands;
    }
}