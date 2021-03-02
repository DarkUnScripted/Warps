package net.darkunscripted.Warps;

import net.darkunscripted.Warps.commands.*;
import net.darkunscripted.Warps.events.onClick;
import net.darkunscripted.Warps.events.onDrag;
import net.darkunscripted.Warps.managers.FileManager;
import net.darkunscripted.Warps.managers.WarpManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileManager cfgm;

    @Override
    public void onEnable() {
        loadConfigManager();
        loadConfigManager();
        registerCommands();
        registerEvents();
        registerManagers();
        WarpManager.loadMineWarp();
        WarpManager.loadWarps();
    }

    @Override
    public void onDisable() {

    }

    public void registerCommands(){
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("setminewarp").setExecutor(new SetMineWarpCommand());
        getCommand("mine").setExecutor(new MineCommand());
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new onClick(), this);
        getServer().getPluginManager().registerEvents(new onDrag(), this);
    }

    public void registerManagers(){

    }

    public void loadConfigManager(){
        cfgm = new FileManager();
        cfgm.setupWarps();
        cfgm.saveWarps();
        cfgm.reloadWarps();
        cfgm.setupMines();
        cfgm.saveMines();
        cfgm.reloadMines();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public FileManager getManager(){
        return cfgm;
    }

}
