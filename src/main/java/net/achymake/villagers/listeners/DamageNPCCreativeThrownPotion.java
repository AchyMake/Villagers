package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DamageNPCCreativeThrownPotion implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public DamageNPCCreativeThrownPotion(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreativeThrownPotion(EntityDamageByEntityEvent event) {
        if (!getDatabase().isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.SPLASH_POTION))return;
        ThrownPotion damager = (ThrownPotion) event.getDamager();
        if (!isPlayer(damager.getShooter()))return;
        event.setCancelled(true);
    }
    private boolean isPlayer(ProjectileSource projectileSource) {
        return projectileSource instanceof Player;
    }
}