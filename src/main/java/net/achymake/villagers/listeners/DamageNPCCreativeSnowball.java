package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DamageNPCCreativeSnowball implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public DamageNPCCreativeSnowball(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreativeSnowball(EntityDamageByEntityEvent event) {
        if (!getDatabase().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.SNOWBALL))return;
        Snowball damager = (Snowball) event.getDamager();
        if (!isPlayer(damager.getShooter()))return;
        event.setCancelled(true);
    }
    private boolean isPlayer(ProjectileSource projectileSource) {
        return projectileSource instanceof Player;
    }
}