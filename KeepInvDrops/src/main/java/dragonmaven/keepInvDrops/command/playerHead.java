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




    public playerHead() {
        super();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if (label.equalsIgnoreCase("playerHead")) {
            // /bonus

            if (commandSender instanceof Player) { // Check if command comes from console or player
                Player player = (Player) commandSender;

                switch (strings.length) {
                    case 0:
                        if (player.isOp() || playerHead_permissions.permissions) {
                            giveOrDropItem(player, player);
                            player.sendMessage("You are receiving your own head.");
                            return false;
                        }

                        player.sendMessage("You don't have the permission to get your own head.");
                        return true;


                    case 1:
                        if (player.isOp() || playerHead_permissions.permissions) {
                            giveOrDropItem(Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])), player);
                            player.sendMessage("You are receiving the of " + strings[0]);
                            return false;
                        }

                        player.sendMessage("You don't have the permission to get a head.");
                        return true;


                    case 2:
                        if(player.isOp()) {
                            giveOrDropItem(Bukkit.getPlayerExact(strings[1]), Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])));
                            player.sendMessage("Giving " + strings[0] + "the head of " + strings[1]);
                            Objects.requireNonNull(Bukkit.getPlayerExact(strings[0])).sendMessage("You receive the head of " + strings[1]);
                            return false;
                        }
                        player.sendMessage("You don't have the permission to get a head.");
                        return true;


                    default:

                        return true;
                }

            }
        }

        return true;

    }


    private boolean giveOrDropItem(Player playerHead, Player playerReceiving) {
        try{
            if (playerReceiving.getInventory().firstEmpty() != 1) {
                playerReceiving.getInventory().addItem((Objects.requireNonNull(DeathHandler.getPlayerHead(playerHead))));

            }

            Location l = playerReceiving.getLocation();

            l.getWorld().dropItemNaturally(l.add(0.5, 0.5, 0.5), DeathHandler.getPlayerHead(playerHead));

            return true;
        }catch (Exception e){



        }
        return false;
    }
}
