package net.achymake.villagers.listeners.entity;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class NPCBreed implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public NPCBreed(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityBreed(EntityBreedEvent event) {
        if (!entityConfig.isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}