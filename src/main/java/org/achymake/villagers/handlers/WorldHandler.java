package org.achymake.villagers.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Villager;

public class WorldHandler {
    public Villager spawnVillager(Location location) {
        var world = location.getWorld();
        if (world != null) {
            location.setPitch(0.0F);
            var villager = world.createEntity(location, Villager.class);
            return world.addEntity(villager);
        } else return null;
    }
}