package net.achymake.villagers.listeners.interact;

import net.achymake.villagers.Villagers;
import net.achymake.villagers.files.EntityConfig;
import net.achymake.villagers.files.Message;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class InteractNPC implements Listener {
    private final EntityConfig entityConfig = Villagers.getEntityConfig();
    private final Message message = Villagers.getMessage();
    public InteractNPC(Villagers villagers) {
        villagers.getServer().getPluginManager().registerEvents(this, villagers);
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteractAtNPC(PlayerInteractEntityEvent event) {
        if (!entityConfig.isNPC(event.getRightClicked()))return;
        event.setCancelled(true);
        if (event.getPlayer().isSneaking()) {
            if (event.getPlayer().hasPermission("villagers.command")) {
                if (!entityConfig.hasSelected(event.getPlayer())) {
                    int taskID = Villagers.getInstance().getServer().getScheduler().runTaskLater(Villagers.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            entityConfig.removeSelected(event.getPlayer());
                            message.send(event.getPlayer(), "&cSelection of villagers expired");
                        }
                    }, 600).getTaskId();
                    event.getPlayer().getPersistentDataContainer().set(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING, event.getRightClicked().getUniqueId().toString());
                    entityConfig.addTask(event.getPlayer(), taskID);
                    message.send(event.getPlayer(), "&6You selected&f " + event.getRightClicked().getName());
                }
            }
        } else {
            if (entityConfig.hasCommand(event.getRightClicked())) {
                if (entityConfig.isCommandPlayer(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer(), entityConfig.getCommand(event.getRightClicked()));
                }
                if (entityConfig.isCommandConsole(event.getRightClicked())) {
                    event.getPlayer().getServer().dispatchCommand(event.getPlayer().getServer().getConsoleSender(), entityConfig.getCommand(event.getRightClicked()).replaceAll("%player%", event.getPlayer().getName()));
                }
            }
        }
    }
}