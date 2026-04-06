package org.achymake.villagers.listeners;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.handlers.VillagerHandler;
import org.bukkit.EntityEffect;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerInteractEntity implements Listener {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private VillagerHandler getVillagerHandler() {
        return getInstance().getVillagerHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerInteractEntity() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager villager) {
            if (!getVillagerHandler().isNPC(villager))return;
            event.setCancelled(true);
            var id = getVillagerHandler().getID(villager);
            var command = getVillagerHandler().getCommand(id);
            if (command == null)return;
            var player = event.getPlayer();
            if (getVillagerHandler().isPlayerCommand(id)) {
                villager.playEffect(EntityEffect.VILLAGER_HAPPY);
                villager.shakeHead();
                player.performCommand(command);
            } else if (getVillagerHandler().isConsoleCommand(id)) {
                villager.playEffect(EntityEffect.VILLAGER_HAPPY);
                villager.shakeHead();
                player.getServer().dispatchCommand(player.getServer().getConsoleSender(), command.replaceAll("%player%", player.getName()));
            }
        }
    }
}