package com.earth2me.essential.runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.earth2me.essential.Essential;

public class PlayerCheck implements Runnable {

	private Essential plugin;
	
	public PlayerCheck(Essential plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (plugin.pl.containsKey(p.getName())) {
				if (plugin.pl.get(p.getName())) {
					if (p.getGameMode() == GameMode.CREATIVE) continue;
					if (p.getFoodLevel() <= 6) {
						p.setFoodLevel(20);
						p.sendMessage(ChatColor.GOLD+"You suddenly don't feel hungry anymore..");
					}
					if (p.getFireTicks() > 0) {
						p.setFireTicks(0);
						p.sendMessage(ChatColor.GOLD+"You used to be on fire, then you took an arrow in the knee");
					}
					if (p.getSaturation() <= 0) {
						p.setSaturation(9001);
						p.sendMessage(ChatColor.GOLD+"You used to loose hunger, then you took an arrow in the knee");
					}
					if (p.getHealth() < 18) {
						p.setHealth(p.getHealth()+2);
					}
				}
			}
		}
	}
}
