package net.achymake.villagers.listeners.target;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTargetNPC implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public EntityTargetNPC(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityTargetNPC(EntityTargetEvent event) {
        if (event.getTarget() == null)return;
        if (!entityConfig.isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}