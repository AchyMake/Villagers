package net.achymake.villagers.files;

import net.achymake.villagers.Villagers;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class EntityConfig {
    private Message getMessage() {
        return Villagers.getMessage();
    }
    public void createVillager(Player player, String name) {
        Location location = player.getLocation();
        location.setPitch(0);
        Villager villager = (Villager) player.getWorld().spawnEntity(location, EntityType.VILLAGER);
        villager.setProfession(Villager.Profession.NONE);
        villager.setVillagerType(Villager.Type.PLAINS);
        villager.setCustomName(getMessage().addColor(name));
        villager.setCustomNameVisible(true);
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.getPersistentDataContainer().set(NamespacedKey.minecraft("npc"), PersistentDataType.STRING, "true");
        getMessage().send(player, "&6You created&f " + name + "&6 villager");
    }
    private PersistentDataContainer data(Entity entity) {
        return entity.getPersistentDataContainer();
    }
    public boolean isNPC(Entity entity) {
        return data(entity).has(NamespacedKey.minecraft("npc"), PersistentDataType.STRING);
    }
    public boolean hasCommand(Entity entity) {
        return data(entity).has(NamespacedKey.minecraft("command"), PersistentDataType.STRING);
    }
    public String getCommand(Entity entity) {
        return data(entity).get(NamespacedKey.minecraft("command"), PersistentDataType.STRING);
    }
    public boolean isCommandConsole(Entity entity) {
        return data(entity).get(NamespacedKey.minecraft("command-type"), PersistentDataType.STRING).equalsIgnoreCase("console");
    }
    public boolean isCommandPlayer(Entity entity) {
        return data(entity).get(NamespacedKey.minecraft("command-type"), PersistentDataType.STRING).equalsIgnoreCase("player");
    }
    public boolean hasSelected(Entity entity) {
        return data(entity).has(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING);
    }
    public Villager getSelected(Entity entity) {
        return (Villager) entity.getServer().getEntity(UUID.fromString(data(entity).get(NamespacedKey.minecraft("selected-villager"), PersistentDataType.STRING)));
    }
    public void removeSelected(Entity entity) {
        data(entity).remove(NamespacedKey.minecraft("selected-villager"));
        if (hasTask(entity)) {
            if (entity.getServer().getScheduler().isQueued(getTask(entity))) {
                entity.getServer().getScheduler().cancelTask(getTask(entity));
            }
        }
    }
    public void addTask(Entity entity, int taskID) {
        data(entity).set(NamespacedKey.minecraft("villagers-task"), PersistentDataType.INTEGER, taskID);
    }
    public boolean hasTask(Entity entity) {
        return data(entity).has(NamespacedKey.minecraft("villagers-task"), PersistentDataType.INTEGER);
    }
    public int getTask(Entity entity) {
        return data(entity).get(NamespacedKey.minecraft("villagers-task"), PersistentDataType.INTEGER);
    }
}