package net.achymake.villagers.listeners.pickup;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class NPCPickupItem implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
    public NPCPickupItem(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onNPCPickupItem(EntityPickupItemEvent event) {
        if (!entityConfig.isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}