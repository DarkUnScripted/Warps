package net.darkunscripted.Warps.commands;

import me.drawethree.ultraprisoncore.UltraPrisonCore;
import me.drawethree.ultraprisoncore.api.events.player.UltraPrisonPlayerEvent;
import me.drawethree.ultraprisoncore.ranks.api.UltraPrisonRankupAPI;
import me.drawethree.ultraprisoncore.ranks.api.UltraPrisonRankupAPIImpl;
import me.drawethree.ultraprisoncore.ranks.rank.Rank;
import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.MineWarp;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand implements CommandExecutor {

    Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(args.length == 0){
                Rank rank = UltraPrisonCore.getInstance().getRanks().getRankManager().getPlayerRank(p);
                int rankid = rank.getId();
                boolean check = true;
                for(MineWarp mine : WarpData.mineWarps){
                    if(mine.getName().equalsIgnoreCase("" + rankid)){
                        p.teleport(mine.getLocation());
                        p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.mine.succesful", "&aSuccesfully teleported to your mine!")));
                    }
                }
                if(check){
                    p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.mine.mine-not-exists", "&cThe is no mine warp for that mine!")));
                }
            }else{
                p.sendMessage(Utils.chat(plugin.getConfig().getString("messages.mine.incorrect-command")));
            }
        }else{
            s.sendMessage(Utils.chat(plugin.getConfig().getString("messages.console.deny")));
        }
        return false;
    }
}
