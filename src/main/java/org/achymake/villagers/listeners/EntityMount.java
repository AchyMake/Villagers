package org.achymake.villagers.listeners;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.handlers.VillagerHandler;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityMountEvent;
import org.bukkit.plugin.PluginManager;

public class EntityMount implements Listener {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private VillagerHandler getVillagerHandler() {
        return getInstance().getVillagerHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public EntityMount() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityMount(EntityMountEvent event) {
        if (event.getEntity() instanceof Villager villager) {
            if (!getVillagerHandler().isNPC(villager))return;
            event.setCancelled(true);
        }
    }
}