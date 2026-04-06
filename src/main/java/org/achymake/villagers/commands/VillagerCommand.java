package org.achymake.villagers.commands;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.data.Message;
import org.achymake.villagers.handlers.VillagerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

public class VillagerCommand implements CommandExecutor, TabCompleter {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private VillagerHandler getVillagerHandler() {
        return getInstance().getVillagerHandler();
    }
    public VillagerCommand() {
        getInstance().getCommand("villager").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(getMessage().addColor("&6Villager Help:"));
                    player.sendMessage(getMessage().addColor("/villager adult idName true/false"));
                    player.sendMessage(getMessage().addColor("/villager command idName console fly %player%"));
                    player.sendMessage(getMessage().addColor("/villager command idName player fly"));
                    player.sendMessage(getMessage().addColor("/villager create idName"));
                    player.sendMessage(getMessage().addColor("/villager help"));
                    player.sendMessage(getMessage().addColor("/villager profession idName farmer"));
                    player.sendMessage(getMessage().addColor("/villager remove idName"));
                    player.sendMessage(getMessage().addColor("/villager rename idName &6Farmer"));
                    player.sendMessage(getMessage().addColor("/villager silent idName true/false"));
                    player.sendMessage(getMessage().addColor("/villager tp idName"));
                    player.sendMessage(getMessage().addColor("/villager tphere idName"));
                    player.sendMessage(getMessage().addColor("/villager type idName jungle"));
                    return true;
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("tp")) {
                    if (getVillagerHandler().exists(args[1])) {
                        player.teleport(getVillagerHandler().get(args[1]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("tphere")) {
                    if (getVillagerHandler().exists(args[1])) {
                        var location = player.getLocation();
                        location.setPitch(0);
                        getVillagerHandler().setLocation(args[1], location);
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("create")) {
                    if (!getVillagerHandler().exists(args[1])) {
                        getVillagerHandler().create(player.getLocation(), args[1]);
                        player.sendMessage(getMessage().get("commands.villager.create", args[1]));
                    } else player.sendMessage(getMessage().get("error.id.already-exist", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (getVillagerHandler().exists(args[1])) {
                        player.sendMessage(getMessage().get("commands.villager.remove", args[1]));
                        getVillagerHandler().remove(args[1]);
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("adult")) {
                    if (getVillagerHandler().exists(args[1])) {
                        var value = Boolean.parseBoolean(args[2]);
                        getVillagerHandler().setAdult(args[1], value);
                        if (getVillagerHandler().isAdult(args[1])) {
                            player.sendMessage(getMessage().get("commands.villager.adult.true", args[1]));
                        } else player.sendMessage(getMessage().get("commands.villager.adult.false", args[1]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("profession")) {
                    if (getVillagerHandler().exists(args[1])) {
                        if (getVillagerHandler().setProfession(args[1], args[2].toUpperCase())) {
                            player.sendMessage(getMessage().get("commands.villager.profession", args[1], getMessage().toTitleCase(args[2])));
                        } else player.sendMessage(getMessage().get("error.profession.invalid", args[2]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("type")) {
                    if (getVillagerHandler().exists(args[1])) {
                        if (getVillagerHandler().setType(args[1], args[2].toUpperCase())) {
                            player.sendMessage(getMessage().get("commands.villager.type", args[1], getMessage().toTitleCase(args[2])));
                        } else player.sendMessage(getMessage().get("error.type.invalid", args[2]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                } else if (args[0].equalsIgnoreCase("silent")) {
                    if (getVillagerHandler().exists(args[1])) {
                        var value = Boolean.parseBoolean(args[2]);
                        getVillagerHandler().setSilent(args[1], value);
                        if (getVillagerHandler().isSilent(args[1])) {
                            player.sendMessage(getMessage().get("commands.villager.silent.true", args[1]));
                        } else player.sendMessage(getMessage().get("commands.villager.silent.false", args[1]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                }
            }
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("rename")) {
                    if (getVillagerHandler().exists(args[1])) {
                        var name = getMessage().getBuilder(args, 2);
                        getVillagerHandler().setName(args[1], name);
                        player.sendMessage(getMessage().get("commands.villager.rename", args[1], name));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                }
            }
            if (args.length >= 3) {
                if (args[0].equalsIgnoreCase("command")) {
                    if (getVillagerHandler().exists(args[1])) {
                        var commandString = getMessage().getBuilder(args, 3);
                        getVillagerHandler().setCommandType(args[1], args[2]);
                        getVillagerHandler().setCommand(args[1], commandString);
                        player.sendMessage(getMessage().get("commands.villager.command", commandString, args[2], args[1]));
                    } else player.sendMessage(getMessage().get("error.id.invalid", args[1]));
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        var commands = new ArrayList<String>();
        if (sender instanceof Player) {
            if (args.length == 1) {
                commands.add("adult");
                commands.add("create");
                commands.add("command");
                commands.add("help");
                commands.add("profession");
                commands.add("remove");
                commands.add("rename");
                commands.add("silent");
                commands.add("type");
                commands.add("tp");
                commands.add("tphere");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("adult") ||
                        args[0].equalsIgnoreCase("command") ||
                        args[0].equalsIgnoreCase("profession") ||
                        args[0].equalsIgnoreCase("remove") ||
                        args[0].equalsIgnoreCase("rename") ||
                        args[0].equalsIgnoreCase("silent") ||
                        args[0].equalsIgnoreCase("type") ||
                        args[0].equalsIgnoreCase("tp") ||
                        args[0].equalsIgnoreCase("tphere")) {
                    for (var listed : getInstance().getVillagerHandler().getListed()) {
                        if (listed.startsWith(args[1])) {
                            commands.add(listed);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("create")) {
                    commands.add("idName");
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("adult")) {
                    commands.add(String.valueOf(getVillagerHandler().isAdult(args[1])));
                } else if (args[0].equalsIgnoreCase("command")) {
                    commands.add("console");
                    commands.add("player");
                } else if (args[0].equalsIgnoreCase("profession")) {
                    for (Villager.Profession profession : Villager.Profession.values()) {
                        var name = profession.name().toLowerCase();
                        if (name.startsWith(args[2])) {
                            commands.add(name);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("silent")) {
                    commands.add(String.valueOf(getVillagerHandler().isSilent(args[1])));
                } else if (args[0].equalsIgnoreCase("type")) {
                    for (Villager.Type type : Villager.Type.values()) {
                        var name = type.name().toLowerCase();
                        if (name.startsWith(args[2])) {
                            commands.add(name);
                        }
                    }
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("command")) {
                    if (args[2].equalsIgnoreCase("console")) {
                        commands.add("%player%");
                    }
                }
            }
        }
        return commands;
    }
}