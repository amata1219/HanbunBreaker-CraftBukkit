package amata1219.hanbun.breaker;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class HanbunCommand implements TabExecutor{

	private HanbunBreaker plugin;

	public HanbunCommand(HanbunBreaker plugin){
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "ゲーム内から実行して下さい。");
			return true;
		}
		Player p = (Player) sender;
		if(args.length == 0){
			p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "HanbunBreaker");
			p.sendMessage("");
			p.sendMessage(ChatColor.AQUA + "/hanbun reload");
			p.sendMessage(ChatColor.WHITE + "コンフィグを再読み込みします。");
			p.sendMessage(ChatColor.AQUA + "/hanbun break または、/hanbun b");
			p.sendMessage(ChatColor.WHITE + "半分破壊を使用するかトグル形式で切り替えます。");
			p.sendMessage(ChatColor.AQUA + "/hanbun particle または、/hanbun p");
			p.sendMessage(ChatColor.WHITE + "半分破壊時、視線先にパーティクルを表示するかトグル形式で切り替えます。");
			p.sendMessage("");
			p.sendMessage(ChatColor.GRAY + "Developed by amata1219(Twitter@amata1219)");
		}else if(args[0].equalsIgnoreCase("reload")){
			plugin.reloadConfig();
			p.sendMessage(ChatColor.AQUA + "コンフィグを再読み込みしました。");
			return true;
		}else if(args[0].equalsIgnoreCase("break") || args[0].equalsIgnoreCase("b")){
			String uuid = p.getUniqueId().toString();
			boolean b = plugin.getConfig().getBoolean(uuid + ".break");
			plugin.getConfig().set(uuid + ".break", !b);
			plugin.saveConfig();
			plugin.reloadConfig();
			p.sendMessage(ChatColor.AQUA + "半分破壊を" + (!b ? "有効" : "無効") + "にしました。");
			return true;
		}else if(args[0].equalsIgnoreCase("particle") || args[0].equalsIgnoreCase("p")){
			String uuid = p.getUniqueId().toString();
			boolean b = plugin.getConfig().getBoolean(uuid + ".particle");
			plugin.getConfig().set(uuid + ".particle", !b);
			plugin.saveConfig();
			plugin.reloadConfig();
			p.sendMessage(ChatColor.AQUA + "パーティクル表示を" + (!b ? "有効" : "無効") + "にしました。");
			return true;
		}
		return true;
	}

}
