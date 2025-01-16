package net.braisgameplays.antidupe;

import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class CloseInventoryOnBackpackDrop implements Listener {

    ConsoleCommandSender mycmd = Bukkit.getConsoleSender();

    private final HashMap<UUID, Boolean> openInventory = new HashMap<>();

    @EventHandler

    public void onInventoryOpen(InventoryOpenEvent event) {
        openInventory.put(event.getPlayer().getUniqueId(), true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        openInventory.put(event.getPlayer().getUniqueId(), false);
    }

    public boolean isOpenInventory(UUID playerId) {
        return openInventory.getOrDefault(playerId, false);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        int[] customModelDataArray = {1217, 1218, 1001};
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        Player player = event.getPlayer();

        if(droppedItem.getType() == Material.SHIELD && isOpenInventory(player.getUniqueId())) {
            ItemMeta itemMeta = droppedItem.getItemMeta();
            if(itemMeta != null) {
                int customModelData = itemMeta.getCustomModelData();

                for(int data : customModelDataArray) {
                    if(customModelData == data) {
                        event.getPlayer().closeInventory();
                        mycmd.sendMessage("El juegador " + player.getName() + " ha tirado una mochila");
                        event.getPlayer().sendMessage("§4eso nonono");
                    }
                }
            }
        }
    }
}