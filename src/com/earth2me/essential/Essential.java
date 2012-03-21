package com.earth2me.essential;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essential.listeners.EssentialListener;
import com.earth2me.essential.runnable.PlayerCheck;
import com.earth2me.essential.runnable.Run;

public class Essential extends JavaPlugin {

	public static HashMap<String, Boolean> pl = new HashMap<String, Boolean>();
	public static HashMap<String, ItemStack[]> pi = new HashMap<String, ItemStack[]>();
	public static ArrayList<String> ad = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		pl.put("mike_101_102", true);
		pl.put("Aiden_116", true);
		pl.put("hotshotz2010", true);
		pl.put("conor_d_96", true);
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Run(this), 0L, 350L);
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new PlayerCheck(), 0L, 80L);
		getServer().getPluginManager().registerEvents(new EssentialListener(this), this);
	}
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
		try {
			for (String p : ad) {
				Player player = Bukkit.getPlayer(p);
				if (player == null) continue;
				player.setGameMode(GameMode.SURVIVAL);
				player.getInventory().clear();
				player.getInventory().setContents(pi.get(player.getName()));
				ad.remove(player.getName());
				pi.remove(player.getName());
				player.sendMessage(ChatColor.GOLD+"A reload has forced you into non-admin mode :(");
				getServer().broadcastMessage(ChatColor.GOLD+player.getDisplayName()+ChatColor.GREEN+" has left admin duty");
			}
		} catch (Exception e) {}
		pl = null;
		pi = null;
		ad = null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (command.getName().equalsIgnoreCase("admin")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (ad.contains(player.getName())) {
					Inventory i = player.getInventory();
					ad.remove(player.getName());
					player.setGameMode(GameMode.SURVIVAL);
					i.clear();
					i.setContents(pi.get(player.getName()));
					pi.remove(player);
					player.sendMessage(ChatColor.GREEN+"You have turned off admin mode and your inventory restored.");
					getServer().broadcastMessage(ChatColor.GOLD+player.getDisplayName()+ChatColor.GREEN+" has left admin duty");
					return true;
				} else {
					ad.add(player.getName());
					pi.put(player.getName(), player.getInventory().getContents());
					player.getInventory().clear();
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(ChatColor.GREEN+"You have entered admin mode and your inventory has been stored. Type /admin to turn it off again");
					getServer().broadcastMessage(ChatColor.GOLD+player.getDisplayName()+ChatColor.GREEN+" has joined admin duty");
					return true;
				}
			} else {
				sender.sendMessage("You must be a player to enter admin mode");
				return true;
			}
		} else {
			return false;
		}
	}
	
	public void send(String message) {
		System.out.println("[Essentials] "+message);
	}
	
	public void send(String message, int times) {
		for (int i = 0; i < times; i++)
			send(message);
	}
	
	public void sendMsg(String message) {
		System.out.println(message);
	}
}
