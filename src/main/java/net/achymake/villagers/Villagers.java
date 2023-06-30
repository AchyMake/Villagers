package net.achymake.villagers;

import net.achymake.villagers.commands.VillagersCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import net.achymake.villagers.listeners.*;
import net.achymake.villagers.listeners.NPCBreed;
import net.achymake.villagers.listeners.NPCEnterLoveMode;
import net.achymake.villagers.listeners.InteractNPC;
import net.achymake.villagers.listeners.NPCPickupItem;
import net.achymake.villagers.listeners.EntityTargetNPC;
import net.achymake.villagers.listeners.NPCAcquireTrade;
import net.achymake.villagers.listeners.NPCCareerChange;
import net.achymake.villagers.listeners.NPCReplenishTrade;
import net.achymake.villagers.version.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Villagers extends JavaPlugin {
    private static Villagers instance;
    public static Villagers getInstance() {
        return instance;
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
        message = new Message(getLogger());
        entityConfig = new EntityConfig();
        commands();
        events();
        getMessage().sendLog(Level.INFO, "Enabled " + getName() + " " + getDescription().getVersion());
        new UpdateChecker(this, 109924).getUpdate();
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
}