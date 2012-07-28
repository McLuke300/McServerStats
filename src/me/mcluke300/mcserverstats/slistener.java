package me.mcluke300.mcserverstats;


import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class slistener implements Listener {

	
	mcserverstats plugin;

	public slistener(mcserverstats instance) {
		plugin = instance;
	}

	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	

	
	
	
	//joins
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			int count = map.get("joins");
			count = count+1;
			map.put("joins", count);
		}}
	
	
	//mobs
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath2(org.bukkit.event.entity.EntityDeathEvent event) {
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			org.bukkit.entity.Entity ply = event.getEntity();
				if(!(ply instanceof Player)){            
						int count = map.get("mobs");
						count = count+1;
						map.put("mobs", count);
				}}}
	
	
	//itemscrafted
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCraft(CraftItemEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
				int count = map.get("itemscrafted");
				count = count+1;
				map.put("itemscrafted", count);
			}}}
	
	
	//itemssmelted
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSmelt(FurnaceSmeltEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
				int count = map.get("itemssmelted");
				count = count+1;
				map.put("itemssmelted", count);
			}}}
	
	
	//kills
	@EventHandler
	public void onEntityDeath1(org.bukkit.event.entity.EntityDeathEvent event) {
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			org.bukkit.entity.Entity ply = event.getEntity();
			if(event.getEntity().getLastDamageCause() instanceof org.bukkit.event.entity.EntityDamageByEntityEvent){
				org.bukkit.entity.Entity dmgr = ((org.bukkit.event.entity.EntityDamageByEntityEvent) event.getEntity().getLastDamageCause()).getDamager();
				if(ply instanceof Player){            
					if(dmgr instanceof Player){
						int count = map.get("kills");
						count = count+1;
						map.put("kills", count);
					}}}}}



	//deaths
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDeath(EntityDeathEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			Entity ent = event.getEntity();
			if(ent instanceof Player){
				int count = map.get("deaths");
				count = count+1;
				map.put("deaths", count);
			}}}
	
	//chat
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(PlayerChatEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			int count = map.get("chat");
			count = count+1;
			map.put("chat", count);
		}}

	
	//commands
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
			int count = map.get("commands");
			count = count+1;
			map.put("commands", count);
		}}}

	//exp
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommand(PlayerExpChangeEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			int count = map.get("exp");
			count = count+event.getAmount();
			map.put("exp", count);
		}}

	
	
	//Place
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
				int count = map.get("placed");
				count = count+1;
				map.put("placed", count);
			}}}

	//Break
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
				int count = map.get("destroyed");
				count = count+1;
				map.put("destroyed", count);
			}}}
	
	//Sign
	@EventHandler(priority = EventPriority.MONITOR)
	public void onSign(SignChangeEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false) {
				int count = map.get("signs");
				count = count+1;
				map.put("signs", count);
			}}}


	//Crops
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCropsBreak(BlockBreakEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.getBlock().getTypeId() == 59 && event.isCancelled() == false) {
				int count = map.get("crops");
				count = count+1;
				map.put("crops", count);
			}}}
	

	
	//Break
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDiamond(BlockBreakEvent event){
		if (plugin.getConfig().getBoolean("Serverstats.enabled")) {
			if (event.isCancelled() == false && event.getBlock().getTypeId() == 56) {
				int count = map.get("diamonds");
				count = count+1;
				map.put("diamonds", count);
			}}}
}
