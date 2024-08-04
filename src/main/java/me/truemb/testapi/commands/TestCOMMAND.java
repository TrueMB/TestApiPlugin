package me.truemb.testapi.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.truemb.testapi.main.Main;

public class TestCOMMAND extends BukkitCommand {
	
	private Main instance;
	
	public TestCOMMAND(Main plugin, List<String> aliases) {
		super("test", "The Main Command for the Test API Plugin", null, aliases);
		
		this.instance = plugin;
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			this.instance.getLogger().info("This command only works ingame!");
			return true;
		}
		
		Player p = (Player) sender;
		UUID uuid = p.getUniqueId();

		if(args.length == 2) {
			
			if(args[0].equalsIgnoreCase("riw") && this.instance.getRiwApi() != null) {
				if(args[1].equalsIgnoreCase("isPlayerWorld")) {
					boolean isPlayerWorld = this.instance.getRiwApi().isPlayerWorld(p.getWorld());
					p.sendMessage("Is a PlayerWorld: " + String.valueOf(isPlayerWorld));
				}
				
			}else if(args[0].equalsIgnoreCase("tempfly") && this.instance.getTempFlyApi() != null) {
				if(args[1].equalsIgnoreCase("mode")) {
					boolean flymode = this.instance.getTempFlyApi().isFlyMode(uuid);

					if(!flymode)
						this.instance.getTempFlyApi().enableFlyMode(uuid);
					else
						this.instance.getTempFlyApi().disableFlyMode(uuid);
				}
			}
			
		}
		
		return true;
	}
	
}
