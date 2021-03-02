package net.darkunscripted.Warps.events;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class onDrag implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if(plugin.getConfig().contains("messages.warpgui.guiname")) {
            if (e.getInventory().getTitle().equals(Utils.chat(plugin.getConfig().getString("messages.warpgui.guiname")))) {
                e.setCancelled(true);
            }
        }else{
            if(e.getInventory().getTitle().equals(Utils.chat("&b&lWarps"))){
                e.setCancelled(true);
            }
        }
    }

}
