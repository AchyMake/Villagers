package net.achymake.villagers.listeners.damage;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageNPCCreativeSnowball implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public DamageNPCCreativeSnowball(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageNPCCreativeSnowball(EntityDamageByEntityEvent event) {
        if (!entityConfig.isNPC(event.getEntity()))return;
        if (!event.getDamager().getType().equals(EntityType.SNOWBALL))return;
        Snowball damager = (Snowball) event.getDamager();
        if (damager.getShooter() instanceof Player) {
            event.setCancelled(true);
        }
    }
}