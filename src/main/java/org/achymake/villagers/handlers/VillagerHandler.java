package org.achymake.villagers.handlers;

import org.achymake.villagers.Villagers;
import org.achymake.villagers.data.Message;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Villager;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class VillagerHandler {
    private Villagers getInstance() {
        return Villagers.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private File getFolder() {
        return new File(getInstance().getDataFolder(), "villager");
    }
    public File getFile(String idName) {
        return new File(getFolder(), idName + ".yml");
    }
    public boolean exists(String idName) {
        return getFile(idName).exists();
    }
    public FileConfiguration getConfig(String idName) {
        return YamlConfiguration.loadConfiguration(getFile(idName));
    }
    public List<String> getListed() {
        var listed = new ArrayList<String>();
        var folder = getFolder();
        if (folder.exists() && folder.isDirectory()) {
            for(var file : folder.listFiles()) {
                if (file.exists() && file.isFile()) {
                    listed.add(file.getName().replace(".yml", ""));
                }
            }
        }
        return listed;
    }
    public Villager create(Location location, String idName) {
        var villager = getWorldHandler().spawnVillager(location);
        setup(villager, idName);
        return villager;
    }
    public Villager get(String idName) {
        if (exists(idName)) {
            load(idName);
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            return get(UUID.fromString(config.getString("uuid")));
        } else return null;
    }
    public Villager get(UUID uuid) {
        return (Villager) getInstance().getServer().getEntity(uuid);
    }
    public String getName(String idName) {
        if (exists(idName)) {
            var config = getConfig(idName);
            return config.isString("name") ? config.getString("name") : "&6Villager";
        } else return "&6Villager";
    }
    public boolean setName(String idName, String name) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            config.set("name", name);
            get(idName).setCustomName(getMessage().addColor(name));
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public boolean isAdult(String idName) {
        return exists(idName) ? getConfig(idName).getBoolean("adult") : true;
    }
    public boolean setAdult(String idName, boolean value) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            if (value) {
                config.set("adult", true);
                get(idName).setAdult();
            } else {
                config.set("adult", false);
                get(idName).setBaby();
            }
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public Villager.Profession getProfession(String idName) {
        if (exists(idName)) {
            var config = getConfig(idName);
            if (config.isString("profession")) {
                var profession = config.getString("profession");
                if (profession.equalsIgnoreCase("armorer")) {
                    return Villager.Profession.ARMORER;
                } else if (profession.equalsIgnoreCase("butcher")) {
                    return Villager.Profession.BUTCHER;
                } else if (profession.equalsIgnoreCase("cartographer")) {
                    return Villager.Profession.CARTOGRAPHER;
                } else if (profession.equalsIgnoreCase("cleric")) {
                    return Villager.Profession.CLERIC;
                } else if (profession.equalsIgnoreCase("farmer")) {
                    return Villager.Profession.FARMER;
                } else if (profession.equalsIgnoreCase("fisherman")) {
                    return Villager.Profession.FISHERMAN;
                } else if (profession.equalsIgnoreCase("fletcher")) {
                    return Villager.Profession.FLETCHER;
                } else if (profession.equalsIgnoreCase("leatherworker")) {
                    return Villager.Profession.LEATHERWORKER;
                } else if (profession.equalsIgnoreCase("librarian")) {
                    return Villager.Profession.LIBRARIAN;
                } else if (profession.equalsIgnoreCase("mason")) {
                    return Villager.Profession.MASON;
                } else if (profession.equalsIgnoreCase("nitwit")) {
                    return Villager.Profession.NITWIT;
                } else if (profession.equalsIgnoreCase("none")) {
                    return Villager.Profession.NONE;
                } else if (profession.equalsIgnoreCase("shepherd")) {
                    return Villager.Profession.SHEPHERD;
                } else if (profession.equalsIgnoreCase("toolsmith")) {
                    return Villager.Profession.TOOLSMITH;
                } else return profession.equalsIgnoreCase("weaponsmith") ? Villager.Profession.WEAPONSMITH : Villager.Profession.NONE;
            } else return Villager.Profession.NONE;
        } else return Villager.Profession.NONE;
    }
    public boolean setProfession(String idName, String profession) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            if (profession.equalsIgnoreCase("armorer")) {
                config.set("profession", "armorer");
                get(idName).setProfession(Villager.Profession.ARMORER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("butcher")) {
                config.set("profession", "butcher");
                get(idName).setProfession(Villager.Profession.BUTCHER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("cartographer")) {
                config.set("profession", "cartographer");
                get(idName).setProfession(Villager.Profession.CARTOGRAPHER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("cleric")) {
                config.set("profession", "cleric");
                get(idName).setProfession(Villager.Profession.CLERIC);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("farmer")) {
                config.set("profession", "farmer");
                get(idName).setProfession(Villager.Profession.FARMER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("fisherman")) {
                config.set("profession", "fisherman");
                get(idName).setProfession(Villager.Profession.FISHERMAN);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("fletcher")) {
                config.set("profession", "fletcher");
                get(idName).setProfession(Villager.Profession.FLETCHER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("leatherworker")) {
                config.set("profession", "leatherworker");
                get(idName).setProfession(Villager.Profession.LEATHERWORKER);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("librarian")) {
                config.set("profession", "librarian");
                get(idName).setProfession(Villager.Profession.LIBRARIAN);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("mason")) {
                config.set("profession", "mason");
                get(idName).setProfession(Villager.Profession.MASON);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("nitwit")) {
                config.set("profession", "nitwit");
                get(idName).setProfession(Villager.Profession.NITWIT);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("none")) {
                config.set("profession", "none");
                get(idName).setProfession(Villager.Profession.NONE);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("shepherd")) {
                config.set("profession", "shepherd");
                get(idName).setProfession(Villager.Profession.SHEPHERD);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("toolsmith")) {
                config.set("profession", "toolsmith");
                get(idName).setProfession(Villager.Profession.TOOLSMITH);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (profession.equalsIgnoreCase("weaponsmith")) {
                config.set("profession", "weaponsmith");
                get(idName).setProfession(Villager.Profession.WEAPONSMITH);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else return false;
        } else return false;
    }
    public Villager.Type getType(String idName) {
        if (exists(idName)) {
            var config = getConfig(idName);
            if (config.isString("type")) {
                var type = config.getString("type");
                if (type.equalsIgnoreCase("desert")) {
                    return Villager.Type.DESERT;
                } else if (type.equalsIgnoreCase("jungle")) {
                    return Villager.Type.JUNGLE;
                } else if (type.equalsIgnoreCase("plains")) {
                    return Villager.Type.PLAINS;
                } else if (type.equalsIgnoreCase("savanna")) {
                    return Villager.Type.SAVANNA;
                } else if (type.equalsIgnoreCase("snow")) {
                    return Villager.Type.SNOW;
                } else if (type.equalsIgnoreCase("swamp")) {
                    return Villager.Type.SWAMP;
                } else return type.equalsIgnoreCase("taiga") ? Villager.Type.TAIGA : Villager.Type.PLAINS;
            } else return Villager.Type.PLAINS;
        } else return Villager.Type.PLAINS;
    }
    public boolean setType(String idName, String type) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            if (type.equalsIgnoreCase("desert")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.DESERT);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("jungle")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.JUNGLE);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("plains")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.PLAINS);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("savanna")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.SAVANNA);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("snow")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.SNOW);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("swamp")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.SWAMP);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else if (type.equalsIgnoreCase("taiga")) {
                config.set("type", type);
                get(idName).setVillagerType(Villager.Type.TAIGA);
                try {
                    config.save(file);
                    return true;
                } catch (IOException e) {
                    getInstance().sendWarning(e.getMessage());
                    return false;
                }
            } else return false;
        } else return false;
    }
    public boolean isSilent(String idName) {
        return exists(idName) && getConfig(idName).getBoolean("silent");
    }
    public boolean setSilent(String idName, boolean value) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            if (value) {
                config.set("silent", true);
                get(idName).setSilent(true);
            } else {
                config.set("silent", false);
                get(idName).setSilent(false);
            }
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public String getCommandType(String idName) {
        return exists(idName) ? getConfig(idName).getString("command-type") : "";
    }
    public String getCommand(String idName) {
        return exists(idName) ? getConfig(idName).getString("command") : "";
    }
    public Location getLocation(String idName) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            var world = getInstance().getServer().getWorld(config.getString("location.world"));
            if (world != null) {
                var x = config.getDouble("location.x");
                var y = config.getDouble("location.y");
                var z = config.getDouble("location.z");
                var yaw = config.getLong("location.yaw");
                var pitch = config.getLong("location.pitch");
                return new Location(world, x, y, z, (float) yaw, (float) pitch);
            } else return null;
        } else return null;
    }
    public boolean setLocation(String idName, Location location) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            location.setPitch(0.0F);
            config.set("location.world", location.getWorld().getName());
            config.set("location.x", location.getX());
            config.set("location.y", location.getY());
            config.set("location.z", location.getZ());
            config.set("location.yaw", location.getYaw());
            config.set("location.pitch", location.getPitch());
            get(idName).teleport(location);
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                this.getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public boolean isConsoleCommand(String idName) {
        if (exists(idName)) {
            return getConfig(idName).isString("command-type") ? getCommandType(idName).equals("console") : false;
        } else return false;
    }
    public boolean isPlayerCommand(String idName) {
        if (exists(idName)) {
            return getConfig(idName).isString("command-type") ? getCommandType(idName).equals("player") : false;
        } else return false;
    }
    public boolean setCommandType(String idName, String commandType) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            config.set("command-type", commandType);
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public boolean setCommand(String idName, String command) {
        if (exists(idName)) {
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            config.set("command", command);
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else return false;
    }
    public boolean setup(Villager villager, String idName) {
        villager.setInvulnerable(true);
        villager.setAI(false);
        villager.setGravity(false);
        villager.setPersistent(true);
        if (!exists(idName)) {
            var location = villager.getLocation();
            var file = getFile(idName);
            var config = YamlConfiguration.loadConfiguration(file);
            config.set("name", getName(idName));
            config.set("adult", villager.isAdult());
            config.set("profession", villager.getProfession().toString());
            config.set("type", villager.getVillagerType().toString());
            config.set("silent", villager.isSilent());
            config.set("command-type", "player");
            config.set("command", "villager help");
            config.set("location.world", location.getWorld().getName());
            config.set("location.x", location.getX());
            config.set("location.y", location.getY());
            config.set("location.z", location.getZ());
            config.set("location.yaw", location.getYaw());
            config.set("location.pitch", location.getPitch());
            config.set("uuid", villager.getUniqueId().toString());
            villager.setCustomName(getMessage().addColor(getName(idName)));
            villager.setCustomNameVisible(true);
            try {
                config.save(file);
                return true;
            } catch (IOException e) {
                getInstance().sendWarning(e.getMessage());
                return false;
            }
        } else {
            reload(villager, idName);
            return true;
        }
    }
    public void reload(Villager villager, String idName) {
        var file = getFile(idName);
        var config = YamlConfiguration.loadConfiguration(file);
        villager.setCustomName(getMessage().addColor(getName(idName)));
        villager.setCustomNameVisible(true);
        villager.setProfession(getProfession(idName));
        villager.setVillagerType(getType(idName));
        if (isAdult(idName)) {
            villager.setAdult();
        } else villager.setBaby();
        villager.setSilent(isSilent(idName));
        villager.teleport(getLocation(idName));
        config.set("uuid", villager.getUniqueId().toString());
        try {
            config.save(file);
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
    public boolean remove(String idName) {
        if (exists(idName)) {
            if (load(idName)) {
                get(idName).remove();
                getFile(idName).delete();
                return true;
            } else return false;
        } else return false;
    }
    public boolean load(String idName) {
        var chunk = getLocation(idName).getChunk();
        if (chunk.isLoaded()) {
            return true;
        } else return chunk.load();
    }
    public boolean isNPC(Villager villager) {
        return getUUIDMap().containsKey(villager.getUniqueId());
    }
    public String getID(Villager villager) {
        return getUUIDMap().get(villager.getUniqueId());
    }
    public Map<UUID, String> getUUIDMap() {
        var listed = new HashMap<UUID, String>();
        var folder = new File(this.getInstance().getDataFolder(), "villager");
        if (folder.exists() && folder.isDirectory()) {
            for(var file : folder.listFiles()) {
                if (file.exists() && file.isFile()) {
                    var config = YamlConfiguration.loadConfiguration(file);
                    var uuid = UUID.fromString(config.getString("uuid"));
                    listed.put(uuid, file.getName().replace(".yml", ""));
                }
            }
        }
        return listed;
    }
    public void disable() {
        var folder = new File(getInstance().getDataFolder(), "villager");
        if (!folder.exists())return;
        if (!folder.isDirectory())return;
        for (var file : folder.listFiles()) {
            if (!file.exists())return;
            if (!file.isFile())return;
            var idName = file.getName().replace(".yml", "");
            if (load(idName)) {
                var config = YamlConfiguration.loadConfiguration(file);
                var villager = get(UUID.fromString(config.getString("uuid")));
                if (villager != null) {
                    villager.remove();
                }
            }
        }
    }
    public void reload() {
        var folder = new File(getInstance().getDataFolder(), "villager");
        if (!folder.exists())return;
        if (!folder.isDirectory())return;
        for(var file : folder.listFiles()) {
            if (!file.exists())return;
            if (!file.isFile())return;
            var config = YamlConfiguration.loadConfiguration(file);
            try {
                config.load(file);
            } catch (InvalidConfigurationException | IOException e) {
                getInstance().sendWarning(e.getMessage());
            }
        }
    }
    public void setup() {
        var folder = new File(getInstance().getDataFolder(), "villager");
        if (!folder.exists())return;
        if (!folder.isDirectory())return;
        for(var file : folder.listFiles()) {
            if (!file.exists())return;
            if (!file.isFile())return;
            var idName = file.getName().replace(".yml", "");
            if (!load(idName))return;
            if (get(idName) != null)return;
            var config = YamlConfiguration.loadConfiguration(file);
            var world = getInstance().getServer().getWorld(config.getString("location.world"));
            if (world == null)return;
            var x = config.getDouble("location.x");
            var y = config.getDouble("location.y");
            var z = config.getDouble("location.z");
            var yaw = config.getLong("location.yaw");
            var pitch = config.getLong("location.pitch");
            setup(getWorldHandler().spawnVillager(new Location(world, x, y, z, (float) yaw, (float) pitch)), idName);
        }
    }
}