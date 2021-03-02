package net.darkunscripted.Warps.managers;

import net.darkunscripted.Warps.Main;
import net.darkunscripted.Warps.data.WarpData;
import net.darkunscripted.Warps.domain.Warp;
import net.darkunscripted.Warps.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIManager {

    static Main plugin = Main.getPlugin(Main.class);

    public static void WarpGUI(Player p){
        Inventory warpGUI = null;
        int slots = WarpData.warps.size();
        while (slots % 9 != 0) {
            slots += 1;
        }
        if(plugin.getConfig().contains("messages.warpgui.guiname")) {
            warpGUI = Bukkit.createInventory(null, slots + 9, Utils.chat(plugin.getConfig().getString("messages.warpgui.guiname")));
        }else{
            warpGUI = Bukkit.createInventory(null, slots + 9, Utils.chat("&b&lWarps"));
        }
        if(warpGUI != null){
            for(int i = 0; i < WarpData.warps.size(); i++){
                Warp warp = WarpData.warps.get(i);
                ItemStack item;
                if(p.hasPermission("warp." + warp.getName())){
                    item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
                }else{
                    item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
                }
                if(item != null){
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(Utils.chat("&a" + warp.getName()));
                    item.setItemMeta(meta);
                    warpGUI.setItem(i, item);
                }
            }
            ItemStack leaveItem = new ItemStack(Material.BARRIER, 1);
            ItemMeta leaveMeta = leaveItem.getItemMeta();
            leaveMeta.setDisplayName(Utils.chat("&c&lLeave"));
            leaveItem.setItemMeta(leaveMeta);
            warpGUI.setItem(slots + 5, leaveItem);
        }
        p.openInventory(warpGUI);
    }

}
