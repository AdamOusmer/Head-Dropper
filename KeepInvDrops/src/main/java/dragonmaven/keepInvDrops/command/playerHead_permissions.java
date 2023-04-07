package dragonmaven.keepInvDrops.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;




public class playerHead_permissions implements CommandExecutor {
    protected static boolean permissions = false;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings){
        if (label.equalsIgnoreCase("playerHead_permissions")) {
            if ((commandSender instanceof Player && ((Player) commandSender).isOp()) || commandSender instanceof ConsoleCommandSender) {

                if (strings[0].equalsIgnoreCase("true")) {
                    permissions = true;
                    commandSender.sendMessage("Global permissions for Head Dropper as been enabled");

                    Bukkit.getLogger().info("Global permissions for Head Dropper have been enabled by " + ((Player) commandSender).getName());

                    return false;

                } else if (strings[0].equalsIgnoreCase("false")) {

                    permissions = false;
                    commandSender.sendMessage("Global permissions for Head Dropper as been disabled");

                    Bukkit.getLogger().info("Global permissions for Head Dropper have been enabled by " + ((Player) commandSender).getName());


                    return false;

                }

            }
        }

        return true;
    }

    public boolean hasPermissions() {
        return permissions;
    }

    public void setPermissions(boolean permission) {
        permissions = permission;
    }
}
