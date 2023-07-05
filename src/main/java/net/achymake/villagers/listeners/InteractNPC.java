package net.achymake.villagers.listeners;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class InteractNPC implements Listener {
    private EntityConfig getEntityConfig() {
        return Villagers.getEntityConfig();
    }
    public InteractNPC(Villagers plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteractAtNPC(PlayerInteractEntityEvent event) {
        if (!getEntityConfig().isNPC(event.getRightClicked()))return;
        event.setCancelled(true);
        if (event.getPlayer().isSneaking()) {
            if (event.getPlayer().hasPermission("villagers.command")) {
                if (getEntityConfig().hasSelected(event.getPlayer())) {
                    if (getEntityConfig().getSelected(event.getPlayer()).equals(event.getRightClicked())) {
                        event.getPlayer().getServer().getScheduler().cancelTask(getEntityConfig().getTask(event.getPlayer()));
                        getEntityConfig().removeSelected(event.getPlayer());
                        Villagers.send(event.getPlayer(), "&6You removed selected&f " + event.getRightClicked().getName());
                    } else {
                        event.getPlayer().getServer().getScheduler().cancelTask(getEntityConfig().getTask(event.getPlayer()));
                        int taskID = Villagers.getInstance().getServer().getScheduler().runTaskLater(Villagers.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                getEntityConfig().removeSelected(event.getPlayer());
                                Villagers.send(event.getPlayer(), "&cSelection of villagers expired");
                            }
                        }, 600).getTaskId();
                        event.getPlayer().getPersistentDataContainer().set(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING, event.getRightClicked().getUniqueId().toString());
                        getEntityConfig().addTask(event.getPlayer(), taskID);
                        Villagers.send(event.getPlayer(), "&6You selected&f " + event.getRightClicked().getName());
                    }
                } else {
                    int taskID = Villagers.getInstance().getServer().getScheduler().runTaskLater(Villagers.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            getEntityConfig().removeSelected(event.getPlayer());
                            Villagers.send(event.getPlayer(), "&cSelection of villagers expired");
                        }
                    }, 600).getTaskId();
                    event.getPlayer().getPersistentDataContainer().set(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING, event.getRightClicked().getUniqueId().toString());
                    getEntityConfig().addTask(event.getPlayer(), taskID);
                    Villagers.send(event.getPlayer(), "&6You selected&f " + event.getRightClicked().getName());
                }
            }
        } else {
            if (getEntityConfig().hasCommand(event.getRightClicked())) {
                if (getEntityConfig().isCommandPlayer(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer(), getEntityConfig().getCommand(event.getRightClicked()));
                }
                if (getEntityConfig().isCommandConsole(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer().getServer().getConsoleSender(), getEntityConfig().getCommand(event.getRightClicked()).replaceAll("%player%", event.getPlayer().getName()));
                }
            }
        }
    }
}