package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageNPCCreative implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public DamageNPCCreative(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreative(EntityDamageByEntityEvent event) {
        if (!getDatabase().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.PLAYER))return;
        event.setCancelled(true);
    }
}