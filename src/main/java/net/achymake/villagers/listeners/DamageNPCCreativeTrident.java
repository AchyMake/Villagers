package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DamageNPCCreativeTrident implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public DamageNPCCreativeTrident(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreativeTrident(EntityDamageByEntityEvent event) {
        if (!getEntityConfig().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.TRIDENT))return;
        Trident damager = (Trident) event.getDamager();
        if (!isPlayer(damager.getShooter()))return;
        event.setCancelled(true);
    }
    private boolean isPlayer(ProjectileSource projectileSource) {
        return projectileSource instanceof Player;
    }
}