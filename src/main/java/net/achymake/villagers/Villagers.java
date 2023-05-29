package net.achymake.villagers;

import net.achymake.villagers.api.Metrics;
import net.achymake.villagers.commands.VillagersCommand;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import net.achymake.villagers.listeners.connection.NotifyUpdate;
import net.achymake.villagers.listeners.connection.Quit;
import net.achymake.villagers.listeners.damage.*;
import net.achymake.villagers.listeners.entity.NPCBreed;
import net.achymake.villagers.listeners.entity.NPCEnterLoveMode;
import net.achymake.villagers.listeners.interact.InteractNPC;
import net.achymake.villagers.listeners.pickup.NPCPickupItem;
import net.achymake.villagers.listeners.target.EntityTargetNPC;
import net.achymake.villagers.listeners.villager.NPCAcquireTrade;
import net.achymake.villagers.listeners.villager.NPCCareerChange;
import net.achymake.villagers.listeners.villager.NPCReplenishTrade;
import net.achymake.villagers.version.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class Villagers extends JavaPlugin {
    private static Villagers instance;
    private static Message message;
    private static EntityConfig entityConfig;
    private static Metrics metrics;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message(this);
        entityConfig = new EntityConfig(this);
        metrics = new Metrics(this, 18608);
        getCommand("villagers").setExecutor(new VillagersCommand());
        new NotifyUpdate(this);
        new Quit(this);
        new DamageNPC(this);
        new DamageNPCCreative(this);
        new DamageNPCCreativeArrow(this);
        new DamageNPCCreativeSnowball(this);
        new DamageNPCCreativeSpectralArrow(this);
        new DamageNPCCreativeThrownPotion(this);
        new DamageNPCCreativeTrident(this);
        new NPCBreed(this);
        new NPCEnterLoveMode(this);
        new InteractNPC(this);
        new NPCPickupItem(this);
        new EntityTargetNPC(this);
        new NPCAcquireTrade(this);
        new NPCCareerChange(this);
        new NPCReplenishTrade(this);
        message.sendLog("Enabled " + getName() + " " + getDescription().getVersion());
        new UpdateChecker(this, 109924).getUpdate();
    }
    @Override
    public void onDisable() {
        metrics.shutdown();
        message.sendLog("Disabled " + getName() + " " + getDescription().getVersion());
    }
    public static Message getMessage() {
        return message;
    }
    public static EntityConfig getEntityConfig() {
        return entityConfig;
    }
    public static Villagers getInstance() {
        return instance;
    }
}