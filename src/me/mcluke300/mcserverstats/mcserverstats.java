package me.mcluke300.mcserverstats;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mcluke300.mcserverstats.Metrics.Graph;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class mcserverstats extends JavaPlugin{

	public static mcserverstats plugin;
	File file = new File ("plugins/mcserverstats/Stats.bin");
	
	@SuppressWarnings("unchecked")
	@Override
	public void onEnable() {
		plugin = this;
		LoadConfiguration();
		try {
			if (!file.exists())  {
				slistener.map.put("joins", 0);
				slistener.map.put("players", plugin.getServer().getOfflinePlayers().length);
				slistener.map.put("mobs", 0);
				slistener.map.put("itemscrafted", 0);
				slistener.map.put("itemssmelted", 0);
				slistener.map.put("days", (int) (Bukkit.getServer().getWorlds().get(0).getFullTime() / 24000));
				slistener.map.put("kills", 0);
				slistener.map.put("deaths", 0);
				slistener.map.put("chat", 0);
				slistener.map.put("commands", 0);
				slistener.map.put("exp", 0);
				slistener.map.put("placed", 0);
				slistener.map.put("destroyed", 0);
				slistener.map.put("signs", 0);
				slistener.map.put("crops", 0);
				slistener.map.put("diamonds", 0);
				try {
					SLAPI.save(slistener.map,"plugins/mcserverstats/Stats.bin");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			slistener.map = (HashMap<String, Integer>)SLAPI.load("plugins/mcserverstats/Stats.bin");
			if (!slistener.map.containsKey("joins") || slistener.map.get("exp") == null) {
				slistener.map.put("joins", 0);
				slistener.map.put("players", plugin.getServer().getOfflinePlayers().length);
				slistener.map.put("mobs", 0);
				slistener.map.put("itemscrafted", 0);
				slistener.map.put("itemssmelted", 0);
				slistener.map.put("days", (int) (Bukkit.getServer().getWorlds().get(0).getFullTime() / 24000));
				slistener.map.put("kills", 0);
				slistener.map.put("deaths", 0);
				slistener.map.put("chat", 0);
				slistener.map.put("commands", 0);
				slistener.map.put("exp", 0);
				slistener.map.put("placed", 0);
				slistener.map.put("destroyed", 0);
				slistener.map.put("signs", 0);
				slistener.map.put("crops", 0);
				slistener.map.put("diamonds", 0);
		}} catch (Exception e) {
				}
		Bukkit.getServer().getPluginManager().registerEvents(new slistener(this), this);
		//Metrics
		
		/*
		 * 						m = m.replaceAll("%joins%", ""  + slistener.map.get("joins"));
						m = m.replaceAll("%players%", ""  + slistener.map.get("players"));
						m = m.replaceAll("%mobs%", ""  + slistener.map.get("mobs"));
						m = m.replaceAll("%itemscrafted%", ""  + slistener.map.get("itemscrafted"));
						m = m.replaceAll("%itemsSmelted%", ""  + slistener.map.get("itemssmelted"));
						m = m.replaceAll("%days%", ""  + slistener.map.get("days"));
						m = m.replaceAll("%kills%", ""  + slistener.map.get("kills"));
						m = m.replaceAll("%deaths%", ""  + slistener.map.get("deaths"));
						m = m.replaceAll("%chat%", ""  + slistener.map.get("chat"));
						m = m.replaceAll("%commands%", ""  + slistener.map.get("commands"));
						m = m.replaceAll("%exp%", ""  + slistener.map.get("exp"));
						m = m.replaceAll("%placed%", ""  + slistener.map.get("placed"));
						m = m.replaceAll("%destroyed%", ""  + slistener.map.get("destroyed"));
						m = m.replaceAll("%signs%", ""  + slistener.map.get("signs"));
						m = m.replaceAll("%crops%", ""  + slistener.map.get("crops"));
						m = m.replaceAll("%diamonds%", ""  + slistener.map.get("diamonds"));
						m = m.replaceAll("&([0-9a-fA-F])", "§$1");
						*/
		try {
		    Metrics metrics = new Metrics(plugin);

		    //The Server Totals Graph
		    Graph graph = metrics.createGraph("Server Totals");

		    //Joins
		    graph.addPlotter(new Metrics.Plotter("Joins") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("joins"); 
		            }

		    });

		    // Players
		    graph.addPlotter(new Metrics.Plotter("Players") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("players");
		            }

		    });
		    
		    // Mobs
		    graph.addPlotter(new Metrics.Plotter("Mobs Killed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("mobs");
		            }

		    });
		    
		    // Items Crafted
		    graph.addPlotter(new Metrics.Plotter("Items Crafted") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("itemscrafted");
		            }

		    });
		    
		    // Items Smelted
		    graph.addPlotter(new Metrics.Plotter("Items Smelted") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("itemssmelted");
		            }

		    });
		    
		    
		    // Days
		    graph.addPlotter(new Metrics.Plotter("Days Passed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("days");
		            }

		    });
		    
		    
		    
		    
		    //The Player Totals Graph
		    Graph graph2 = metrics.createGraph("Player Totals");

		    //Kills	
		    graph2.addPlotter(new Metrics.Plotter("Kills") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("kills"); 
		            }

		    });

		    // Deaths
		    graph2.addPlotter(new Metrics.Plotter("Deaths") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("deaths");
		            }

		    });
		    
		    // Chat
		    graph2.addPlotter(new Metrics.Plotter("Chat") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("chat");
		            }

		    });
		    
		    // Commands
		    graph2.addPlotter(new Metrics.Plotter("Commands") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("commands");
		            }

		    });
		    
		   	    
		    // Exp
		    graph2.addPlotter(new Metrics.Plotter("Exp") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("exp");
		            }

		    });
		    
		    
		  //The Block Totals Graph
		    Graph graph3 = metrics.createGraph("Block Totals");

		    //Placed
		    graph3.addPlotter(new Metrics.Plotter("Placed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("placed"); 
		            }

		    });

		    // Destroyed
		    graph3.addPlotter(new Metrics.Plotter("Destroyed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("destroyed");
		            }

		    });
		    
		    // Signs
		    graph3.addPlotter(new Metrics.Plotter("Signs Placed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("signs");
		            }

		    });
		    
		    // Crops
		    graph3.addPlotter(new Metrics.Plotter("Crops Destroyed") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("crops");
		            }

		    });
		    
		   	    
		    // Diamonds
		    graph3.addPlotter(new Metrics.Plotter("Diamonds Mined") {

		            @Override
		            public int getValue() {
		                    return slistener.map.get("diamonds");
		            }

		    });

		    metrics.start();
		} catch (IOException e) {
		    System.out.print(e.getMessage());
		}
		long time = (long) plugin.getConfig().getInt("Serverstats.AutoSaveEvery") * 20;
		plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {


			   public void run() {
					try {
						SLAPI.save(slistener.map,"plugins/mcserverstats/Stats.bin");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
			}, 60L, time);
	}
	
	
	@Override
	public void onDisable() {
		try {
			SLAPI.save(slistener.map,"plugins/mcserverstats/Stats.bin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@EventHandler
	public void onPlayerEvent(PlayerChatEvent e) {

	}

	
	public void LoadConfiguration() {
		//Paths
		String path = "Serverstats.enabled";
		String path2 = "Serverstats.stats.message";
		String path3 = "Serverstats.AutoSaveEvery";
		
		//List
		List<String> words = new ArrayList<String>();
		words.add("&b======&2McServerStats&b======");
		words.add("&9Server Totals:");
		words.add("&2Joins: &c%joins%");
		words.add("&2Players: &c%players%");
		words.add("&2Mobs Killed: &c%mobs%");
		words.add("&2Items Crafted: &c%itemscrafted%");
		words.add("&2Items Smelted: &c%itemsSmelted%");
		words.add("&2Days Passed: &c%days%");
		words.add("&9Player Totals:");
		words.add("&2Kills: &c%kills%");
		words.add("&2Deaths: &c%deaths%");
		words.add("&2Chat: &c%chat%");
		words.add("&2Commands: &c%commands%");
		words.add("&2Exp Gained: &c%exp%");
		words.add("&9Block Totals:");
		words.add("&2Placed: &c%placed%");
		words.add("&2Destroyed: &c%destroyed%");
		words.add("&2Signs Placed: &c%signs%");
		words.add("&2Crops Harvested: &c%crops%");
		words.add("&2Diamonds Mined: &c%diamonds%");

		//Saving
		getConfig().addDefault(path, true);
		getConfig().addDefault(path2, words);
		getConfig().addDefault(path3, 30);
		getConfig().options().copyDefaults(true);
		saveConfig();

	}
	


	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("McServerStats") ||commandLabel.equalsIgnoreCase("mss")) {
			
			//Main Command(Help)
			if(args.length == 0 && sender.hasPermission("Mcserverstats.mss")) {
				sender.sendMessage(ChatColor.AQUA+"======McServerStats======");
				sender.sendMessage(ChatColor.AQUA+"/mss stats");
				sender.sendMessage(ChatColor.AQUA+"/mss reload");
				sender.sendMessage(ChatColor.AQUA+"======"+this.getDescription().getVersion()+" "+this.getDescription().getAuthors()+"======");
				return true;
				
				
			}else if (args.length == 1){
				
				//Reload
				if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("Mcserverstats.reload")) {
					getConfig();
					reloadConfig();
					try {
						SLAPI.save(slistener.map,"plugins/mcserverstats/Stats.bin");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GREEN+"McServerStats Config Reloaded");
					return true;
				}
				
				//Info
				if (args[0].equalsIgnoreCase("stats") && sender.hasPermission("Mcserverstats.stats")) {
					List<String> msg = plugin.getConfig().getStringList("Serverstats.stats.message");
					slistener.map.put("players", Bukkit.getServer().getOfflinePlayers().length);
					slistener.map.put("days", (int) (Bukkit.getServer().getWorlds().get(0).getFullTime() / 24000));
					for (String m : msg) {
						m = m.replaceAll("%joins%", ""  + slistener.map.get("joins"));
						m = m.replaceAll("%players%", ""  + slistener.map.get("players"));
						m = m.replaceAll("%mobs%", ""  + slistener.map.get("mobs"));
						m = m.replaceAll("%itemscrafted%", ""  + slistener.map.get("itemscrafted"));
						m = m.replaceAll("%itemsSmelted%", ""  + slistener.map.get("itemssmelted"));
						m = m.replaceAll("%days%", ""  + slistener.map.get("days"));
						m = m.replaceAll("%kills%", ""  + slistener.map.get("kills"));
						m = m.replaceAll("%deaths%", ""  + slistener.map.get("deaths"));
						m = m.replaceAll("%chat%", ""  + slistener.map.get("chat"));
						m = m.replaceAll("%commands%", ""  + slistener.map.get("commands"));
						m = m.replaceAll("%exp%", ""  + slistener.map.get("exp"));
						m = m.replaceAll("%placed%", ""  + slistener.map.get("placed"));
						m = m.replaceAll("%destroyed%", ""  + slistener.map.get("destroyed"));
						m = m.replaceAll("%signs%", ""  + slistener.map.get("signs"));
						m = m.replaceAll("%crops%", ""  + slistener.map.get("crops"));
						m = m.replaceAll("%diamonds%", ""  + slistener.map.get("diamonds"));
						m = m.replaceAll("&([0-9a-fA-F])", "§$1");
						sender.sendMessage(m);
					}
				}
				
				if (args[0].equalsIgnoreCase("save")) {
					try {
						SLAPI.save(slistener.map,"plugins/mcserverstats/Stats.bin");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sender.sendMessage(ChatColor.GREEN+"McServerStats Config Saved");
				}

			} else if (args.length < 2) {
				sender.sendMessage(ChatColor.RED + "Invalid amount of arguments");
				return false;
			}
		}
		return false;
	}

}
