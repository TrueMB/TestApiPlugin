package me.truemb.testapi.main;

import java.lang.reflect.Field;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.truemb.rentitworlds.api.RiwAPI;
import me.truemb.rentitworlds.main.RIW;
import me.truemb.tempfly.api.FlyManager;
import me.truemb.tempfly.main.TempFly;
import me.truemb.testapi.commands.TestCOMMAND;

public class Main extends JavaPlugin {
    
	@Getter
	private FlyManager tempFlyApi;
	@Getter
	private RiwAPI riwApi;
	
	@Override
	public void onEnable() {
		
		this.setupTempFlyAPI();
		this.setupRentItWorldsAPI();
		
		try{
		    Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		    commandMapField.setAccessible(true);
		    CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

			//COMMANDS
			commandMap.register(this.getDescription().getName(), new TestCOMMAND(this, new ArrayList<String>()));
		    
		}catch(Exception ex){
		    ex.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		
	}

	private void setupTempFlyAPI() {
		
		//PLUGIN WAS FOUND
		Plugin tempFlyPlugin = Bukkit.getPluginManager().getPlugin("TempFly");
	    if(tempFlyPlugin != null && tempFlyPlugin.isEnabled()){
	        this.tempFlyApi = TempFly.getFlyManager();
			this.getLogger().info("TempFly was found and registered!");
	    }else {
			this.getLogger().info("TempFly was not found on the server.");
	    }
		
	}
	
	private void setupRentItWorldsAPI() {
		
		//PLUGIN WAS FOUND
		Plugin riwPlugin = Bukkit.getPluginManager().getPlugin("RentIt-Worlds");
	    if(riwPlugin != null && riwPlugin.isEnabled()){
	       	this.riwApi = RIW.getAPI();
			this.getLogger().info("RentIt-Worlds was found and registered!");
	    }else {
			this.getLogger().info("RentIt-Worlds was not found on the server.");
	    }
		
	}

}
