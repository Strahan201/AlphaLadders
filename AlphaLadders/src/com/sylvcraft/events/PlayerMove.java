package com.sylvcraft.events;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.sylvcraft.AlphaLadders;


public class PlayerMove implements Listener {
  AlphaLadders plugin;
  
  public PlayerMove(AlphaLadders instance) {
    plugin = instance;
  }

	@EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
		if (!plugin.getConfig().getBoolean("config.status")) return;
		if (!e.getPlayer().hasPermission("alad.use") && !e.getPlayer().hasPermission("alad.admin")) return;
		
		if (e.getFrom().getBlockY() >= e.getTo().getBlockY()) return;
		if (e.getFrom().getBlock().getType() != Material.LADDER) return;
		if (e.getTo().getBlock().getType() == Material.LADDER) return;
		if (e.getTo().getBlock().getRelative(BlockFace.UP).getType() != Material.LADDER) return;

		e.getPlayer().setVelocity(new Vector(0, plugin.getConfig().getDouble("config.value", 0.38), 0));  
  }
}