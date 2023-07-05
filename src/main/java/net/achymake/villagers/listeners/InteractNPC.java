package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.Database;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class InteractNPC implements Listener {
    private Database getDatabase() {
        return Villagers.getDatabase();
    }
    public InteractNPC(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteractAtNPC(PlayerInteractEntityEvent event) {
        if (!getDatabase().isNPC(event.getRightClicked()))return;
        event.setCancelled(true);
        if (event.getPlayer().isSneaking()) {
            if (event.getPlayer().hasPermission("villagers.command")) {
                if (getDatabase().hasSelected(event.getPlayer())) {
                    if (getDatabase().getSelected(event.getPlayer()).equals(event.getRightClicked())) {
                        event.getPlayer().getServer().getScheduler().cancelTask(getDatabase().getTask(event.getPlayer()));
                        getDatabase().removeSelected(event.getPlayer());
                        Villagers.send(event.getPlayer(), "&6You removed selected&f " + event.getRightClicked().getName());
                    } else {
                        event.getPlayer().getServer().getScheduler().cancelTask(getDatabase().getTask(event.getPlayer()));
                        int taskID = Villagers.getInstance().getServer().getScheduler().runTaskLater(Villagers.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                getDatabase().removeSelected(event.getPlayer());
                                Villagers.send(event.getPlayer(), "&cSelection of villagers expired");
                            }
                        }, 600).getTaskId();
                        event.getPlayer().getPersistentDataContainer().set(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING, event.getRightClicked().getUniqueId().toString());
                        getDatabase().addTask(event.getPlayer(), taskID);
                        Villagers.send(event.getPlayer(), "&6You selected&f " + event.getRightClicked().getName());
                    }
                } else {
                    int taskID = Villagers.getInstance().getServer().getScheduler().runTaskLater(Villagers.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            getDatabase().removeSelected(event.getPlayer());
                            Villagers.send(event.getPlayer(), "&cSelection of villagers expired");
                        }
                    }, 600).getTaskId();
                    event.getPlayer().getPersistentDataContainer().set(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING, event.getRightClicked().getUniqueId().toString());
                    getDatabase().addTask(event.getPlayer(), taskID);
                    Villagers.send(event.getPlayer(), "&6You selected&f " + event.getRightClicked().getName());
                }
            }
        } else {
            if (getDatabase().hasCommand(event.getRightClicked())) {
                if (getDatabase().isCommandPlayer(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer(), getDatabase().getCommand(event.getRightClicked()));
                }
                if (getDatabase().isCommandConsole(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer().getServer().getConsoleSender(), getDatabase().getCommand(event.getRightClicked()).replaceAll("%player%", event.getPlayer().getName()));
                }
            }
        }
    }
}