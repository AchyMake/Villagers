package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public Quit(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onQuit(PlayerQuitEvent event) {
        if (!getEntityConfig().hasSelected(event.getPlayer()))return;
        getEntityConfig().removeSelected(event.getPlayer());
    }
}