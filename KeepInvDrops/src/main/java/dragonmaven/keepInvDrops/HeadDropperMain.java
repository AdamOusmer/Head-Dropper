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

        Objects.requireNonNull(this.getCommand("playerHead")).setExecutor(new playerHead());
        this.php = new playerHead_permissions();

        Objects.requireNonNull(this.getCommand("playerHead_permission")).setExecutor((sender, command, label, args) -> {
            if (args.length == 1 && args[0].equalsIgnoreCase("true")) {
                php.setPermissions(true);
                sender.sendMessage("Global permissions for Head Dropper have been enabled");

                Bukkit.getLogger().info("Global permissions for Head Dropper have been enabled by " + sender.getName());

                return true;

            } else if (args.length == 1 && args[0].equalsIgnoreCase("false")) {
                this.php.setPermissions(false);
                sender.sendMessage("Global permissions for Head Dropper have been disabled");

                Bukkit.getLogger().info("Global permissions for Head Dropper have been disabled by " + sender.getName());

                return true;

            }

            return false;
        });
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
