package com.sylvcraft.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sylvcraft.AlphaLadders;

public class alad implements CommandExecutor {
  AlphaLadders plugin;
  
  public alad(AlphaLadders instance) {
    plugin = instance;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    try {
    	if (!sender.hasPermission("alad.admin")) {
    		plugin.msg("access-denied", sender);
    		return true;
    	}
			if (args.length == 0) {
        plugin.msg("help", sender);
        return true;
      }
			
			HashMap<String, String> data = new HashMap<String, String>();
      switch (args[0].toLowerCase()) {

      case "toggle":
      	boolean curStatus = !plugin.getConfig().getBoolean("config.status");
      	plugin.getConfig().set("config.status", curStatus);
      	plugin.saveConfig();
      	plugin.msg("plugin-" + ((curStatus)?"enabled":"disabled"), sender);
      	break;

      case "set":
      	if (args.length < 2) {
      		plugin.msg("help", sender);
      		return true;
      	}
      	try {
      		data.put("value", args[1]);
      		Double val = Double.parseDouble(args[1]);
      		plugin.getConfig().set("config.value", val);
      		plugin.saveConfig();
      		plugin.msg("valueset", sender, data);
      	} catch (NumberFormatException e) {
      		plugin.msg("valueset-invalid", sender);
      	}
      	break;

      }
      return true;
    } catch (Exception ex) {
      return false;
    }
  }
}
