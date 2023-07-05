package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetNPC implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public EntityTargetNPC(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityTargetNPC(EntityTargetEvent event) {
        if (event.getTarget() == null)return;
        if (!getDatabase().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}