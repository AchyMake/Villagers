package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class NPCPickupItem implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public NPCPickupItem(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onNPCPickupItem(EntityPickupItemEvent event) {
        if (!getDatabase().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}