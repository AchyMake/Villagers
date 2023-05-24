package net.achymake.villagers.listeners.connection;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    public Quit(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        if (!entityConfig.hasSelected(event.getPlayer()))return;
        entityConfig.removeSelected(event.getPlayer());
    }
}