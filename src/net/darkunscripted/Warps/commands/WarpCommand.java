package net.darkunscripted.Warps.commands;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.Warp;
import net.darkunscripted.Warps.managers.GUIManager;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    Main plugin  = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(args.length == 0){
                GUIManager.WarpGUI(p);
            }else if(args.length == 1){
                for(Warp warp : WarpData.warps){
                    if(warp.getName().equalsIgnoreCase(args[0])){
                        if(p.hasPermission("warp." + warp.getName())) {
                            p.teleport(warp.getLocation());
                            p.closeInventory();
                        }else{
                            if(plugin.getConfig().contains("messages.warps.deny")) {
                                p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.warps.deny")));
                                p.closeInventory();
                            }else{
                                p.sendMessage(Utils.chat("&c&lYou do not have permission to warp there!"));
                                p.closeInventory();
                            }
                        }
                    }
                }
            }else{
                if(plugin.getConfig().contains("messages.warps.incorrect-command")){
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.warps.incorrect-command")));
                }else{
                    p.sendMessage(Utils.chat("&c&lIncorrect Usage of command!"));
                }
            }
        }else{
            s.sendMessage(Utils.chat("&cOnly a player can perform this command!"));
        }
        return false;
    }

}
