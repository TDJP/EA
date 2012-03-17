package com.earth2me.essential;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essential.listeners.EssentialListener;
import com.earth2me.essential.runnable.PlayerCheck;
import com.earth2me.essential.runnable.Run;

public class Essential extends JavaPlugin {

	public HashMap<String, Boolean> pl = new HashMap<String, Boolean>();
	
	private LivingEntity e1;
	private LivingEntity e2;
	
	public PigZombie v1;
	public PigZombie v2;
	
	@Override
	public void onEnable() {
		World world = Bukkit.getWorlds().get(0);
		e1 = world.spawnCreature(new Location(world, -655, 102, -6), EntityType.PIG_ZOMBIE);
		e2 = world.spawnCreature(new Location(world, -661, 102, 0.545), EntityType.PIG_ZOMBIE);
		
		v1 = (PigZombie) e1;
		v2 = (PigZombie) e2;
		v1.setTarget(v2);
		v2.setTarget(v2);
		
		pl.put("mike_101_102", true);
		pl.put("Aiden_116", true);
		pl.put("hotshotz2010", true);
		pl.put("conor_d_96", true);
		
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Run(this), 0L, 175L);
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new PlayerCheck(this), 0L, 80L);
		getServer().getPluginManager().registerEvents(new EssentialListener(this), this);
	}
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
		v1.setHealth(0);
		v2.setHealth(0);
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
