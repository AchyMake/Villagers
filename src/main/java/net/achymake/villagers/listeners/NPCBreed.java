package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

public class NPCBreed implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public NPCBreed(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityBreed(EntityBreedEvent event) {
        if (!getDatabase().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}