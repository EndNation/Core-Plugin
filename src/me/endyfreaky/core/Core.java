package me.endyfreaky.core;

import java.util.*;
import java.io.File;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_8_R1.Material;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class Core extends JavaPlugin 
{
	public boolean vanished = false;
	public static Economy economy = null;
	
	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
		{
			economy = economyProvider.getProvider();
		}
		getLogger().info("[Vault]Economy hooked");
		return (economy != null);
	}
	
	public void onEnable()
	{
		vanished = false;
		if (!setupEconomy())
		{
			getLogger().severe("PLUGIN UNABLE TO LOAD ECONOMY!PLUGIN VAULT IS MISSING.DISABLING PLUGIN!!!");
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		}
		getLogger().info("Plugin is enabled!Remember that this plugin is still in beta mode!");
	}
	
	public void onDisable()
	{
		vanished = false;
		getLogger().info("Plugin is disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (sender.hasPermission("core.givemeallachievements"))
			{
				if (label.equalsIgnoreCase("HackAchievements"))
				{
					((Player) sender).awardAchievement(Achievement.ACQUIRE_IRON);
					((Player) sender).awardAchievement(Achievement.BAKE_CAKE);
					((Player) sender).awardAchievement(Achievement.BOOKCASE);
					((Player) sender).awardAchievement(Achievement.BREED_COW);
					((Player) sender).awardAchievement(Achievement.BREW_POTION);
					((Player) sender).awardAchievement(Achievement.BUILD_BETTER_PICKAXE);
					((Player) sender).awardAchievement(Achievement.BUILD_FURNACE);
					((Player) sender).awardAchievement(Achievement.BUILD_HOE);
					((Player) sender).awardAchievement(Achievement.BUILD_PICKAXE);
					((Player) sender).awardAchievement(Achievement.BUILD_SWORD);
					((Player) sender).awardAchievement(Achievement.BUILD_WORKBENCH);
					((Player) sender).awardAchievement(Achievement.COOK_FISH);
					((Player) sender).awardAchievement(Achievement.DIAMONDS_TO_YOU);
					((Player) sender).awardAchievement(Achievement.ENCHANTMENTS);
					((Player) sender).awardAchievement(Achievement.END_PORTAL);
					((Player) sender).awardAchievement(Achievement.EXPLORE_ALL_BIOMES);
					((Player) sender).awardAchievement(Achievement.FLY_PIG);
					((Player) sender).awardAchievement(Achievement.FULL_BEACON);
					((Player) sender).awardAchievement(Achievement.GET_BLAZE_ROD);
					((Player) sender).awardAchievement(Achievement.GET_DIAMONDS);
					((Player) sender).awardAchievement(Achievement.GHAST_RETURN);
					((Player) sender).awardAchievement(Achievement.KILL_COW);
					((Player) sender).awardAchievement(Achievement.KILL_ENEMY);
					((Player) sender).awardAchievement(Achievement.KILL_WITHER);
					((Player) sender).awardAchievement(Achievement.MAKE_BREAD);
					((Player) sender).awardAchievement(Achievement.MINE_WOOD);
					((Player) sender).awardAchievement(Achievement.NETHER_PORTAL);
					((Player) sender).awardAchievement(Achievement.ON_A_RAIL);
					((Player) sender).awardAchievement(Achievement.OPEN_INVENTORY);
					((Player) sender).awardAchievement(Achievement.OVERKILL);
					((Player) sender).awardAchievement(Achievement.OVERPOWERED);
					((Player) sender).awardAchievement(Achievement.SNIPE_SKELETON);
					((Player) sender).awardAchievement(Achievement.SPAWN_WITHER);
					((Player) sender).awardAchievement(Achievement.THE_END);
					sender.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You have achieved all of the achievements!");
				}
			}
			
			if (sender.hasPermission("core.vanish"))
			{
				if (label.equalsIgnoreCase("invisible"))
				{
					String van = "vanish " + sender.getName() ;
					if (vanished == false)
					{
						vanished = true;
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), van);
						player.awardAchievement(Achievement.EXPLORE_ALL_BIOMES);
						player.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You are now invisible");
					}
					else if (vanished == true)
					{
						vanished = false;
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), van);
						player.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You are now visible");
					}
				}
			}
			
			if (sender.hasPermission("core.hub"))
			{
				if (label.equalsIgnoreCase("hub"))
				{
					String hub = "spawn " + player.getName();
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), hub);
					player.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "Returning to hub!");
				}
			}
			if(sender.hasPermission("core.whatsmyxp"))
			{
				if(label.equalsIgnoreCase("XP"))
				{
					float me = ((Player) sender).getExp();
					sender.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "Your exp is: " + me);
				}
			}
			if (sender.hasPermission("core.whatsmylevel"))
			{
				if (label.equalsIgnoreCase("LVL"))
				{
					int me = ((Player) sender).getLevel();
					sender.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "Your level is: " + me);
				}
			}
			if (sender.hasPermission("core.kill"))
			{
				if (label.equalsIgnoreCase("kill"))
				{
					Player killer = (Player) sender;
					String killerName = killer.getName();
					if (args.length == 0)
					{
						killer.setHealth(0F);
						killer.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You killed yourself");
						Bukkit.broadcastMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + killerName + " killed himself!");
					}
					else if (args.length == 1)
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						String targetName = target.getName();
						target.setHealth(0F);
						target.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.RED + "You have been killed by " + killerName);
						killer.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You have killed " + targetName);
					}
				}
			}
			if (sender.hasPermission("core.fakeop"))
			{
				if (label.equalsIgnoreCase("fakeop"))
				{
					String senderName = sender.getName();
					if (args.length == 0)
					{
						sender.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.RED + "Invalid arguments.Require a specified player!");
					}
					else if (args.length == 1)
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						String targetName = target.getName();
						sender.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You have faked " + targetName + " and have removed his op if he was opped!");
						if (target.isOp())
						{
							target.setOp(false);
						}
						target.sendMessage(ChatColor.GRAY + "You have been opped");
					}
				}
			}
			if(sender.hasPermission("core.playerteleportation"))
			{
				if(label.equalsIgnoreCase("PTP"))
				{
					Player commander = (Player) sender;
					String commanderName = commander.getName();
					if (args.length == 0)
					{
						commander.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.RED + "See help menu for details!");
					}
					else if(args.length == 1)
					{
						Player target = Bukkit.getServer().getPlayer(args[0]);
						String targetName = target.getName();
						Location tl = target.getLocation();
						commander.teleport(tl);
						commander.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + "You have teleported to " + targetName);
						target.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + commanderName + " has teleported to you.");
					}
					else if (args.length == 2)
					{
						Player from = Bukkit.getServer().getPlayer(args[0]);
						String fn = from.getName();
						Player to = Bukkit.getServer().getPlayer(args[1]);
						String tn = to.getName();
						Location tl = to.getLocation();
						from.teleport(tl);
						from.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + commanderName +  " has teleported you to " +  tn);
						to.sendMessage(ChatColor.BLUE + "[Core]" + ChatColor.GREEN + commanderName + " has teleported " + fn + " to you");
					}
				}
			}
		}
		return false;
	}
}
