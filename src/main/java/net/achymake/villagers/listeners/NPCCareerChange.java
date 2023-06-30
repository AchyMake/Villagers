package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;

public class NPCCareerChange implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public NPCCareerChange(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onVillagerCareerChange(VillagerCareerChangeEvent event) {
        if (!getEntityConfig().isNPC(event.getEntity()))return;
        event.setCancelled(true);
    }
}