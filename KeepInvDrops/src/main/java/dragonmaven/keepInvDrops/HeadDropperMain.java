package dragonmaven.keepInvDrops;

import dragonmaven.keepInvDrops.command.playerHead;
import dragonmaven.keepInvDrops.command.playerHead_permissions;
import dragonmaven.keepInvDrops.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

public final class HeadDropperMain extends JavaPlugin {
    private playerHead_permissions php;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting KeepsInvDrops - By Dragonmaven");
        Bukkit.getLogger().setLevel(Level.INFO);

        new DeathHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutdown KeepsInvDrops.");

    }

    public playerHead_permissions getHeadPermission() {
        return this.php;
    }


}
