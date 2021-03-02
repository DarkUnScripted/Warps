package net.darkunscripted.Warps.commands;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.Warp;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class DelWarpCommand implements CommandExecutor {

    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("warp.admin")){
                if(args.length == 1){
                    boolean check = true;
                    for(Warp warp : WarpData.warps){
                        if(warp.getName().equalsIgnoreCase(args[0])){
                            check = false;
                            WarpData.warps.remove(warp);
                            try {
                                plugin.getManager().warpscfg.set("warps." + warp.getName(), null);
                                plugin.getManager().warpscfg.save(plugin.getManager().warpsfile);
                            }catch (IOException e){
                                plugin.getServer().getConsoleSender().sendMessage("&c[Error] could not delete from file!");
                            }
                            warp = null;
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.delwarp.succesful", "&aWarp has been deleted succesfully")));
                        }
                    }
                    if(check){
                       p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.delwarp.warp-not-exists", "&cThat warp does not exist!")));
                    }
                }else{
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.delwarp.incorrect-command", "&cWrong usage of command!")));
                }
            }else{
                p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.delwarp.deny")));
            }
        }else{
            s.sendMessage(Utils.chat("&cOnly a player can perform this command!"));
        }
        return false;
    }

}
