package net.darkunscripted.Warps.commands;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.MineWarp;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetMineWarpCommand implements CommandExecutor {

    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("warp.admin")) {
                if (args.length == 1) {
                    boolean check = true;
                    for(MineWarp mine : WarpData.mineWarps){
                        if(mine.getName().equalsIgnoreCase(args[0])) {
                            mine.setLocation(p.getLocation());
                            check = false;
                            p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setminewarp.succesful", "&aSuccesfully moved mine location")));
                            try{
                                plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.x", mine.getLocation().getX());
                                plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.y", mine.getLocation().getY());
                                plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.z", mine.getLocation().getZ());
                                plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.world", mine.getLocation().getWorld());
                                plugin.getManager().minescfg.save(plugin.getManager().minesfile);
                            }catch (IOException e){
                                plugin.getServer().getConsoleSender().sendMessage(Utils.chat("&c[Error] Could not save mines to file!"));
                            }
                        }
                    }
                    if(check){
                        MineWarp mine = new MineWarp(args[0], p.getLocation());
                        WarpData.mineWarps.add(mine);
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("message.setminewarp.succesful", "&aSuccesfully made the minewarp!")));
                        try{
                            plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.x", mine.getLocation().getX());
                            plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.y", mine.getLocation().getY());
                            plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.z", mine.getLocation().getZ());
                            plugin.getManager().minescfg.set("mines." + mine.getName() + ".location.world", mine.getLocation().getWorld());
                            plugin.getManager().minescfg.save(plugin.getManager().minesfile);
                        }catch(IOException e){
                            plugin.getServer().getConsoleSender().sendMessage(Utils.chat("&c[Error] Could not save mines to file!"));
                        }
                    }
                } else {
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.setminewarp.incorrect-command", "&cWrong usage of command!")));
                }
            }
        }else{
            s.sendMessage(Utils.chat(plugin.getConfig().getString("messages.console.deny", "&cOnly a player can perform this command!")));
        }
        return false;
    }

}
