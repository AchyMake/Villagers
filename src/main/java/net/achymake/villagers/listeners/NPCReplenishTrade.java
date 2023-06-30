package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;

public class NPCReplenishTrade implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public NPCReplenishTrade(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onVillagerReplenishTrade(VillagerReplenishTradeEvent event) {
        if (!getEntityConfig().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}