package net.darkunscripted.Warps.managers;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.MineWarp;
import net.darkunscripted.Warps.domain.Warp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class WarpManager {

    static Main plugin = Main.getPlugin(Main.class);

    public static void loadWarps(){
        if(plugin.getManager().warpscfg.contains("warps")){
            ConfigurationSection section = plugin.getManager().warpscfg.getConfigurationSection("warps");
            for(String warpName : section.getKeys(false)){
                String name = warpName;
                double x = Double.parseDouble(section.getString(warpName + ".location.x"));
                double y = Double.parseDouble(section.getString(warpName + ".location.y"));
                double z = Double.parseDouble(section.getString(warpName + ".location.z"));
                World world = Bukkit.getWorld(section.getString(warpName + ".location.world"));
                Location location = new Location(world, x, y, z);
                Warp warp = new Warp(warpName, location);
                WarpData.warps.add(warp);
            }
        }
    }

    public static void loadMineWarp(){
        if(plugin.getManager().minescfg.contains("warps")){
            ConfigurationSection section = plugin.getManager().minescfg.getConfigurationSection("mines");
            for(String mineName : section.getKeys(false)){
                String name = mineName;
                double x = Double.parseDouble(section.getString(mineName + ".location.x"));
                double y = Double.parseDouble(section.getString(mineName + ".location.y"));
                double z = Double.parseDouble(section.getString(mineName + ".location.z"));
                World world = Bukkit.getWorld(section.getString(mineName + ".location.world"));
                Location location = new Location(world, x, y, z);
                MineWarp mine = new MineWarp(mineName, location);
                WarpData.mineWarps.add(mine);
            }
        }
    }

}
