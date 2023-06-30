package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageNPCCreative implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public DamageNPCCreative(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreative(EntityDamageByEntityEvent event) {
        if (!getEntityConfig().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.PLAYER))return;
        event.setCancelled(true);
    }
}