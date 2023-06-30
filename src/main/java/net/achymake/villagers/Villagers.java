package net.achymake.villagers;

import net.achymake.villagers.commands.*;
import net.achymake.villagers.files.*;
import net.achymake.villagers.listeners.*;
import net.achymake.villagers.version.UpdateChecker;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Villagers extends JavaPlugin {
    private static Villagers instance;
    public static Villagers getInstance() {
        return instance;
    }
    private static FileConfiguration configuration;
    public static FileConfiguration getConfiguration() {
        return configuration;
    }
    private static Message message;
    public static Message getMessage() {
        return message;
    }
    private static EntityConfig entityConfig;
    public static EntityConfig getEntityConfig() {
        return entityConfig;
    }
    private void start() {
        instance = this;
        configuration = getConfig();
        message = new Message(getLogger());
        entityConfig = new EntityConfig();
        reload();
        commands();
        events();
        getMessage().sendLog(Level.INFO, "Enabled " + getName() + " " + getDescription().getVersion());
        new UpdateChecker().getUpdate();
    }
    private void stop() {
        getMessage().sendLog(Level.INFO, "Disabled " + getName() + " " + getDescription().getVersion());
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
    @Override
    public void onEnable() {
        start();
    }
    @Override
    public void onDisable() {
        stop();
    }
    public void reload() {
        File file = new File(getDataFolder(), "config.yml");
        if (file.exists()) {
            try {
                getConfig().load(file);
                getMessage().sendLog(Level.INFO, "loaded config.yml");
            } catch (IOException | InvalidConfigurationException e) {
                getMessage().sendLog(Level.WARNING, e.getMessage());
            }
            saveConfig();
        } else {
            getConfig().options().copyDefaults(true);
            saveConfig();
            getMessage().sendLog(Level.INFO, "created config.yml");
        }
    }
}