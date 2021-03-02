package net.darkunscripted.Warps.events;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.Warp;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onClick implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(plugin.getConfig().contains("messages.warpgui.guiname")) {
            if (e.getClickedInventory().getTitle().equals(Utils.chat(plugin.getConfig().getString("messages.warpgui.guiname")))) {
                e.setCancelled(true);
                if(e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                        e.getWhoClicked().closeInventory();
                    }else{
                        String name = Utils.strip(e.getCurrentItem().getItemMeta().getDisplayName());
                        for(Warp warp : WarpData.warps){
                            if(warp.getName().equalsIgnoreCase(name)){
                                if(e.getWhoClicked().hasPermission("warp." + warp.getName())) {
                                    e.getWhoClicked().teleport(warp.getLocation());
                                    e.getWhoClicked().closeInventory();
                                }else{
                                    if(plugin.getConfig().contains("messages.warps.deny")) {
                                        e.getWhoClicked().sendMessage(Utils.chat(plugin.getConfig().getString("messages.warps.deny")));
                                        e.getWhoClicked().closeInventory();
                                    }else{
                                        e.getWhoClicked().sendMessage(Utils.chat("&c&lYou do not have permission to warp there!"));
                                        e.getWhoClicked().closeInventory();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            if(e.getClickedInventory().getTitle().equals(Utils.chat("&b&lWarps"))){
                e.setCancelled(true);
                if(e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType().equals(Material.BARRIER)) {
                        e.getWhoClicked().closeInventory();
                    }else{
                        String name = Utils.strip(e.getCurrentItem().getItemMeta().getDisplayName());
                        for(Warp warp : WarpData.warps){
                            if(warp.getName().equalsIgnoreCase(name)){
                                if(e.getWhoClicked().hasPermission("warp." + warp.getName())) {
                                    e.getWhoClicked().teleport(warp.getLocation());
                                    e.getWhoClicked().closeInventory();
                                }else{
                                    if(plugin.getConfig().contains("messages.warps.deny")) {
                                        e.getWhoClicked().sendMessage(Utils.chat(plugin.getConfig().getString("messages.warps.deny")));
                                        e.getWhoClicked().closeInventory();
                                    }else{
                                        e.getWhoClicked().sendMessage(Utils.chat("&c&lYou do not have permission to warp there!"));
                                        e.getWhoClicked().closeInventory();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
