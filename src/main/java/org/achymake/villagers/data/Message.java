package org.achymake.villagers.data;

import org.achymake.villagers.Villagers;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class Message {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private final File file = new File(getInstance().getDataFolder(), "message.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public String get(String path) {
        if (config.isString(path)) {
            return addColor(config.getString(path));
        } else return path + ": is missing a value";
    }
    public String get(String path, String... format) {
        if (config.isString(path)) {
            return addColor(MessageFormat.format(config.getString(path), format));
        } else return path + ": is missing a value";
    }
    private void setup() {
        config.set("error.profession.invalid", "{0}&c is not a profession");
        config.set("error.type.invalid", "{0}&c is not a type");
        config.set("error.id.invalid", "{0}&c does not exist");
        config.set("error.id.already-exist", "{0}&c already exist");
        config.set("commands.villager.adult.true", "{0}&6 is now an adult");
        config.set("commands.villager.adult.false", "{0}&6 is now a baby");
        config.set("commands.villager.command", "&6You added&f {0}&6 with&f {1}&6 command to&f {2}");
        config.set("commands.villager.create", "&6You created a villager npc&f: {0}");
        config.set("commands.villager.profession", "{0}&6 profession is now&f: {1}");
        config.set("commands.villager.remove", "&6You removed&f {0}&6 villager npc");
        config.set("commands.villager.rename", "&6You renamed&f {0}&6 npc to&f {1}");
        config.set("commands.villager.silent.true", "{0}&6 is now silent");
        config.set("commands.villager.silent.false", "{0}&6 is no longer silent");
        config.set("commands.villager.type", "{0}&6 type is now&f: {1}");
        config.options().copyDefaults(true);
        try {
            config.save(file);
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
    public void reload() {
        if (file.exists()) {
            config = YamlConfiguration.loadConfiguration(file);
        } else setup();
    }
    public String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public String getBuilder(String[] args, int value) {
        var builder = getBuilder();
        for(var i = value; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        return builder.toString().strip();
    }
    public String toTitleCase(String string) {
        if (string.contains(" ")) {
            var builder = getBuilder();
            for (var strings : string.split(" ")) {
                builder.append(strings.toUpperCase().charAt(0) + strings.substring(1).toLowerCase());
                builder.append(" ");
            }
            return builder.toString().strip();
        } else if (string.contains("_")) {
            var builder = getBuilder();
            for (var strings : string.split("_")) {
                builder.append(strings.toUpperCase().charAt(0) + strings.substring(1).toLowerCase());
                builder.append(" ");
            }
            return builder.toString().strip();
        } else return string.toUpperCase().charAt(0) + string.substring(1).toLowerCase();
    }
    public StringBuilder getBuilder() {
        return new StringBuilder();
    }
}