package net.darkunscripted.Warps.managers;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private Main plugin = Main.getPlugin(Main.class);

    //Files & File Configs

    public FileConfiguration warpscfg;
    public File warpsfile;
    public FileConfiguration minescfg;
    public File minesfile;

    //--------------------

    public void setupWarps(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        warpsfile = new File(plugin.getDataFolder(), "warps.yml");

        if(!warpsfile.exists()){
            try{
                warpsfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create warps.yml file!"));
            }
        }

        warpscfg = YamlConfiguration.loadConfiguration(warpsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&awarps.yml file has been created!!"));
    }

    public FileConfiguration getWarps(){
        return warpscfg;
    }

    public void saveWarps(){
        try{
            warpscfg.save(warpsfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&awarps.yml has been saved"));
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save the warps.yml file"));
        }
    }

    public void reloadWarps(){
        warpscfg = YamlConfiguration.loadConfiguration(warpsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&awarps.yml has been reloaded"));
    }

    public void setupMines(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        minesfile = new File(plugin.getDataFolder(), "mines.yml");

        if(!minesfile.exists()){
            try{
                minesfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create mines.yml file!"));
            }
        }

        minescfg = YamlConfiguration.loadConfiguration(minesfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&amines.yml file has been created!!"));
    }

    public FileConfiguration getMines(){
        return minescfg;
    }

    public void saveMines(){
        try{
            minescfg.save(minesfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&amines.yml has been saved"));
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save the mines.yml file"));
        }
    }

    public void reloadMines(){
        minescfg = YamlConfiguration.loadConfiguration(minesfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&amines.yml has been reloaded"));
    }

}
