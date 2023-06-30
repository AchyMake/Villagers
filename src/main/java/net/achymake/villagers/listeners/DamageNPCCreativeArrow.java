package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DamageNPCCreativeArrow implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public DamageNPCCreativeArrow(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreativeArrow(EntityDamageByEntityEvent event) {
        if (!getEntityConfig().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.ARROW))return;
        Arrow damager = (Arrow) event.getDamager();
        if (!isPlayer(damager.getShooter()))return;
        event.setCancelled(true);
    }
    private boolean isPlayer(ProjectileSource projectileSource) {
        return projectileSource instanceof Player;
    }
}