package net.achymake.villagers;

import net.achymake.villagers.commands.*;
import net.achymake.villagers.files.*;
import net.achymake.villagers.listeners.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Consumer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Villagers extends JavaPlugin {
    private static Villagers instance;
    public static Villagers getInstance() {
        return instance;
    }
    private static FileConfiguration configuration;
    public static FileConfiguration getConfiguration() {
        return configuration;
    }
    private static Logger logger;
    public static void sendLog(Level level, String message) {
        logger.log(level, message);
    }
    private static Database database;
    public static Database getDatabase() {
        return database;
    }
    @Override
    public void onEnable() {
        instance = this;
        configuration = getConfig();
        logger = getLogger();
        database = new Database();
        reload();
        commands();
        events();
        sendLog(Level.INFO, "Enabled " + getName() + " " + getDescription().getVersion());
        getUpdate();
    }
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        sendLog(Level.INFO, "Disabled " + getName() + " " + getDescription().getVersion());
    }
    private void commands() {
        getCommand("villagers").setExecutor(new VillagersCommand());
    }
    private void events() {
        new DamageNPC(this);
        new DamageNPCCreative(this);
        new DamageNPCCreativeArrow(this);
        new DamageNPCCreativeSnowball(this);
        new DamageNPCCreativeSpectralArrow(this);
        new DamageNPCCreativeThrownPotion(this);
        new DamageNPCCreativeTrident(this);
        new EntityTargetNPC(this);
        new InteractNPC(this);
        new NotifyUpdate(this);
        new NPCAcquireTrade(this);
        new NPCBreed(this);
        new NPCCareerChange(this);
        new NPCEnterLoveMode(this);
        new NPCPickupItem(this);
        new NPCReplenishTrade(this);
        new Quit(this);
    }
    public void reload() {
        File file = new File(getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfig().load(file);
                sendLog(Level.INFO, "loaded config.yml");
            } catch (IOException | InvalidConfigurationException e) {
                sendLog(Level.WARNING, e.getMessage());
            }
            saveConfig();
        } else {
            getConfig().options().copyDefaults(true);
            saveConfig();
            sendLog(Level.INFO, "created config.yml");
        }
    }
    public void getUpdate(Player player) {
        if (getConfig().getBoolean("notify-update.enable")) {
            getLatest((latest) -> {
                if (!getDescription().getVersion().equals(latest)) {
                    send(player,"&6" + getName() + " Update:&f " + latest);
                    send(player,"&6Current Version: &f" + getDescription().getVersion());
                }
            });
        }
    }
    public void getUpdate() {
        if (getConfig().getBoolean("notify-update.enable")) {
            getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
                @Override
                public void run() {
                    getLatest((latest) -> {
                        sendLog(Level.INFO, "Checking latest release");
                        if (getDescription().getVersion().equals(latest)) {
                            sendLog(Level.INFO, "You are using the latest version");
                        } else {
                            sendLog(Level.INFO, "New Update: " + latest);
                            sendLog(Level.INFO, "Current Version: " + getDescription().getVersion());
                        }
                    });
                }
            });
        }
    }
    public void getLatest(Consumer<String> consumer) {
        try {
            InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + 109924)).openStream();
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
                scanner.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            sendLog(Level.WARNING, e.getMessage());
        }
    }
    public static void send(ConsoleCommandSender commandSender, String message) {
        commandSender.sendMessage(message);
    }
    public static void send(Player player, String message) {
        player.sendMessage(addColor(message));
    }
    public static void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(addColor(message)));
    }
    public static String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}