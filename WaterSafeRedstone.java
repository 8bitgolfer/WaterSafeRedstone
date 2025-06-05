package me.yourname.watersaferedstone;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WaterSafeRedstone extends JavaPlugin implements Listener {

    private final Set<Material> redstoneComponents = new HashSet<>(Arrays.asList(
            Material.REDSTONE_WIRE,
            Material.REDSTONE_TORCH_ON,
            Material.REDSTONE_TORCH_OFF,
            Material.DIODE_BLOCK_ON,
            Material.DIODE_BLOCK_OFF,
            Material.LEVER,
            Material.STONE_BUTTON,
            Material.WOOD_BUTTON,
            Material.STONE_PLATE,
            Material.WOOD_PLATE,
            Material.GOLD_PLATE,
            Material.IRON_PLATE,
            Material.TRIPWIRE,
            Material.TRIPWIRE_HOOK
    ));

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("WaterSafeRedstone enabled successfully.");
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();
        if (redstoneComponents.contains(block.getType())) {
            Block above = block.getRelative(0, 1, 0);
            if (above.getType() == Material.WATER || above.getType() == Material.STATIONARY_WATER) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Block toBlock = event.getToBlock();
        if (redstoneComponents.contains(toBlock.getType())) {
            event.setCancelled(true);
        }
    }
}
