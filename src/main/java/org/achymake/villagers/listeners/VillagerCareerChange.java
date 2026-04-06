package org.achymake.villagers.listeners;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.handlers.VillagerHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.plugin.PluginManager;

public class VillagerCareerChange implements Listener {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private VillagerHandler getVillagerHandler() {
        return getInstance().getVillagerHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public VillagerCareerChange() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onVillagerCareerChange(VillagerCareerChangeEvent event) {
        if (!getVillagerHandler().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}