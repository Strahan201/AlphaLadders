package com.sylvcraft;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import com.sylvcraft.events.PlayerMove;

import com.sylvcraft.commands.alad;


public class AlphaLadders extends JavaPlugin {
  @Override
  public void onEnable() {
  	saveDefaultConfig();
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new PlayerMove(this), this);
    getCommand("alad").setExecutor(new alad(this));
  }
  
  public void msg(String msgCode, CommandSender sender) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode);
  	for (String m : tmp.split("%br%")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
  	}
  }

  public void msg(String msgCode, CommandSender sender, HashMap<String, String> data) {
  	String tmp = getConfig().getString("messages." + msgCode, msgCode);

  	Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
  	while (it.hasNext()) {
  	  Map.Entry<String, String> mapData = (Map.Entry<String, String>)it.next();
  	  tmp = tmp.replace("%" + mapData.getKey() + "%", mapData.getValue());
  	}

  	msg(tmp, sender);
  }
}