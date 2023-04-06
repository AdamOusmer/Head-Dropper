package dragonmaven.keepInvDrops.command;

import dragonmaven.keepInvDrops.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class playerHead implements CommandExecutor {

    private boolean permission = false;


    public playerHead(){
        super();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if (label.equalsIgnoreCase("playerHead")) {
            // /bonus

            if (commandSender instanceof Player) { // Check if command comes from console or player
                Player player = (Player) commandSender;

                if(player.isOp() || permission){

                    switch (strings.length){
                        case 0:
                            giveOrDropItem(player, player);
                            return false;
                        case 1:
                            if(Bukkit.getPlayerExact(strings[0]) != null) {
                                giveOrDropItem(Bukkit.getPlayerExact(strings[0]), player);
                                return false;
                            }
                            return true;
                        case 2:
                            if((Bukkit.getPlayerExact(strings[0]) != null) ) {
                                giveOrDropItem(Bukkit.getPlayerExact(strings[0]), Objects.requireNonNull(Bukkit.getPlayerExact(strings[1])));
                                return false;
                            }
                             return false;
                        default:
                            return true;
                    }

                }
                return true;
            }
        }


        return true;
    }


    private void giveOrDropItem(Player playerHead, Player playerReceiving) {

        if (playerReceiving.getInventory().firstEmpty() != 1) {
            playerReceiving.getInventory().addItem((Objects.requireNonNull(DeathHandler.getPlayerHead(playerHead))));
            return;
        }

        Location l = playerReceiving.getLocation();

        l.getWorld().dropItemNaturally(l.add(0.5, 0.5, 0.5), DeathHandler.getPlayerHead(playerHead));
    }

    public boolean isPermission() {
        return this.permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
