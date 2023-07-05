package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NotifyUpdate implements Listener {
    private Villagers getPlugin() {
        return Villagers.getInstance();
    }
    public NotifyUpdate(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onNotifyUpdate (PlayerJoinEvent event) {
        if (!event.getPlayer().hasPermission("villagers.command"))return;
        getPlugin().getUpdate(event.getPlayer());
    }
}