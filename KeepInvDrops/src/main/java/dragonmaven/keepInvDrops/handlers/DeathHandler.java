package dragonmaven.keepInvDrops.handlers;

import dragonmaven.keepInvDrops.HeadDropperMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DeathHandler implements Listener {


    public DeathHandler(HeadDropperMain plugin){

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getPlayer();

        Location l = player.getLocation();

        l.getWorld().dropItemNaturally(l.add(0.5, 0.5, 0.5), DeathHandler.getPlayerHead(player));

    }

    public static ItemStack getPlayerHead(Player player) {

        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");

        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");

        ItemStack item = null;

        item = new ItemStack(type, 1);


        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);

        item.setItemMeta(meta);

        return item;
    }

}
