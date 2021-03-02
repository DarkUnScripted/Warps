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

public class SetWarpCommand implements CommandExecutor {

    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("warp.admin")){
                if(args.length == 1){
                    boolean check = true;
                    for(Warp warp : WarpData.warps){
                        if(warp.getName().equals(args[1])){
                            check = false;
                        }
                    }
                    if(check){
                        Warp warp = new Warp(args[0], p.getLocation());
                        WarpData.warps.add(warp);
                        try {
                            plugin.getManager().warpscfg.set("warps." + warp.getName() + ".location.x", warp.getLocation().getX());
                            plugin.getManager().warpscfg.set("warps." + warp.getName() + ".location.Y", warp.getLocation().getY());
                            plugin.getManager().warpscfg.set("warps." + warp.getName() + ".location.Z", warp.getLocation().getZ());
                            plugin.getManager().warpscfg.set("warps." + warp.getName() + ".location.world", warp.getLocation().getWorld());
                            plugin.getManager().warpscfg.save(plugin.getManager().warpsfile);
                        }catch (IOException e){
                            plugin.getServer().getConsoleSender().sendMessage("&c[Error] Could not save warp to file!");
                        }
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setwarp.succesful", "&aSuccesfully made the warp!")));
                    }else{
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setwarp.warp-exists", "&cThat warp already exists!")));
                    }
                }else{
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setwarp.incorrect-command", "&cWrong usage of command!")));
                }
            }else{
                p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setwarp.deny", "&cYou dont have permssion to set a warp!")));
            }
        }else{
            if(plugin.getConfig().contains("messages.console.deny")) {
                s.sendMessage(Utils.chat(plugin.getConfig().getString("messages.console.deny")));
            }else{
                s.sendMessage(Utils.chat("&cOnly players can perform this command!"));
            }
        }
        return false;
    }

}
