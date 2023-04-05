package dragonmaven.keepInvDrops;

import dragonmaven.keepInvDrops.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public final class KeepInvDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Starting KeepsInvDrops - By Dragonmaven");

        new DeathHandler(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Shutdown KeepsInvDrops.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("playerSkull")) {

            if (!(sender instanceof Player)) { //Check if it is a player who ran the command
                sender.sendMessage("You cannot do this");
                return true;
            }

            Player player = (Player) sender;
            if (args.length == 0) {
                //give their own head
                player.sendMessage("You have been given the skull of" + player.getName());

            } else {
                //give their own head
                player.sendMessage("You have been given the skull of" + args[0]);

            }

            return giveOrDropItem(player);

        }
        return false;
    }

    private boolean giveOrDropItem(Player player) {

        if (player.getInventory().firstEmpty() != 1) {
            player.getInventory().addItem((Objects.requireNonNull(DeathHandler.getPlayerHead(player.getName()))));
            return true;
        } else {
            Location l = player.getLocation();

            l.getWorld().dropItemNaturally(l.add(0.5, 0.5, 0.5), DeathHandler.getPlayerHead(player.getName()));

        }
        return false;
    }




}
