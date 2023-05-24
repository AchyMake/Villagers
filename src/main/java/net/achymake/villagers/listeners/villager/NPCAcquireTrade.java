package net.achymake.villagers.listeners.villager;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;

public class NPCAcquireTrade implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public NPCAcquireTrade(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        if (!entityConfig.isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}