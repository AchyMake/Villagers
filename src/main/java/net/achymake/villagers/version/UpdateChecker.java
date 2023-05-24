package net.achymake.villagers.version;

import net.achymake.villagers.Villagers;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private final Villagers plugin;
    private final int resourceId;
    public UpdateChecker(Villagers plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }
    public void getVersion(Consumer<String> consumer) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                InputStream inputStream = (new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId)).openStream();
                Scanner scanner = new Scanner(inputStream);
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                    scanner.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                Villagers.getMessage().sendLog(e.getMessage());
            }
        });
    }
    public void getUpdate() {
        (new UpdateChecker(plugin, resourceId)).getVersion((latest) -> {
            if (plugin.getDescription().getVersion().equals(latest)) {
                Villagers.getMessage().sendLog("You are using the latest version");
            } else {
                Villagers.getMessage().sendLog("New Update: " + latest);
                Villagers.getMessage().sendLog("Current Version: " + plugin.getDescription().getVersion());
            }
        });
    }
    public void sendMessage(Player player) {
        (new UpdateChecker(plugin, resourceId)).getVersion((latest) -> {
            if (!plugin.getDescription().getVersion().equalsIgnoreCase(latest)) {
                Villagers.getMessage().send(player,"&6" + plugin.getName() + " Update:&f "+ latest);
                Villagers.getMessage().send(player,"&6Current Version: &f" + plugin.getDescription().getVersion());
            }
        });
    }
}