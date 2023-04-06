package dragonmaven.keepInvDrops;

import dragonmaven.keepInvDrops.command.playerHead;
import dragonmaven.keepInvDrops.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HeadDropperMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting KeepsInvDrops - By Dragonmaven");

        new DeathHandler(this);

        Objects.requireNonNull(this.getCommand("playerHead")).setExecutor(new playerHead());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutdown KeepsInvDrops.");

    }
}
