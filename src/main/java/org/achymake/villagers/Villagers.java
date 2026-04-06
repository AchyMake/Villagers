package org.achymake.villagers;

import org.achymake.villagers.commands.*;
import org.achymake.villagers.data.*;
import org.achymake.villagers.handlers.*;
import org.achymake.villagers.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class Villagers extends JavaPlugin {
    private static Villagers instance;
    private Message message;
    private ScheduleHandler scheduleHandler;
    private VillagerHandler villagerHandler;
    private WorldHandler worldHandler;
    private UpdateChecker updateChecker;
    private BukkitScheduler bukkitScheduler;
    private PluginManager pluginManager;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message();
        scheduleHandler = new ScheduleHandler();
        villagerHandler = new VillagerHandler();
        worldHandler = new WorldHandler();
        updateChecker = new UpdateChecker();
        bukkitScheduler = getServer().getScheduler();
        pluginManager = getServer().getPluginManager();
        commands();
        events();
        reload();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        getVillagerHandler().disable();
        getScheduleHandler().disable();
        sendInfo("Disabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    public void reload() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else reloadConfig();
        getMessage().reload();
        getVillagerHandler().disable();
        getVillagerHandler().reload();
        getVillagerHandler().setup();
    }
    private void commands() {
        new VillagerCommand();
        new VillagersCommand();
    }
    private void events() {
        new EntityBreed();
        new EntityDamage();
        new EntityDamageByEntity();
        new EntityEnterLoveMode();
        new EntityMount();
        new EntityPickupItem();
        new EntityTarget();
        new EntityTargetLivingEntity();
        new PlayerInteractEntity();
        new PlayerJoin();
        new VillagerAcquireTrade();
        new VillagerCareerChange();
        new VillagerReplenishTrade();
    }
    public PluginManager getPluginManager() {
        return pluginManager;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
    public VillagerHandler getVillagerHandler() {
        return villagerHandler;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public Message getMessage() {
        return message;
    }
    public static Villagers getInstance() {
        return instance;
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
}