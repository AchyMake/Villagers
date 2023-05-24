package net.achymake.villagers.listeners.villager;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;

public class NPCReplenishTrade implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public NPCReplenishTrade(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onVillagerReplenishTrade(VillagerReplenishTradeEvent event) {
        if (!entityConfig.isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}