package amata1219.hanbun.breaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class HanbunBreaker extends JavaPlugin implements Listener{

	private List<Material> list;

	@Override
	public void onEnable(){
		getCommand("hanbun").setExecutor(new HanbunCommand(this));
		list = new ArrayList<Material>(Arrays.asList(Material.DOUBLE_STEP, Material.WOOD_DOUBLE_STEP));
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}

	@Override
	public void onDisable(){
		BlockBreakEvent.getHandlerList().unregister((Listener) this);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(!list.contains(b.getType()))return;
		if(!getConfig().getBoolean(p.getUniqueId().toString() + ".break"))return;
		RayTrace rayTrace = new RayTrace(p.getEyeLocation().toVector(), p.getEyeLocation().getDirection());
		ArrayList<Vector> positions = rayTrace.traverse(5, 0.1);
		for(int i = 0; i < positions.size(); i++){
			Location position = positions.get(i).toLocation(p.getWorld());
			Block block = p.getWorld().getBlockAt(position);
			Material m = block.getType();
			if(block != null && m != Material.AIR && list.contains(m)){
				boolean top = position.getY() - position.getBlockY() >= 0.5;
				if(m == Material.DOUBLE_STEP){
					switch(block.getData()){
					case 0:
						setType(block, 44, top ? 0 : 8);
						break;
					case 1:
						setType(block, 44, top ? 1 : 9);
						break;
					case 2:
						setType(block, 44, top ? 2 : 10);
						break;
					case 3:
						setType(block, 44, top ? 3 : 11);
						break;
					case 4:
						setType(block, 44, top ? 4 : 12);
						break;
					case 5:
						setType(block, 44, top ? 5 : 13);
						break;
					case 6:
						setType(block, 44, top ? 6 : 14);
						break;
					case 7:
						setType(block, 44, top ? 7 : 15);
						break;
					case 8:
						setType(block, 44, top ? 0 : 8);
						break;
					case 9:
						setType(block, 44, top ? 1 : 9);
						break;
					case 10:
						setType(block, 44, top ? 2 : 10);
						break;
					case 11:
						setType(block, 44, top ? 3 : 11);
						break;
					case 12:
						setType(block, 44, top ? 4 : 12);
						break;
					case 13:
						setType(block, 44, top ? 5 : 13);
						break;
					case 14:
						setType(block, 44, top ? 6 : 14);
						break;
					case 15:
						setType(block, 44, top ? 7 : 15);
						break;
					}
				}else if(m == Material.WOOD_DOUBLE_STEP){
					switch(block.getData()){
					case 0:
						setType(block, 126, top ? 0 : 8);
						break;
					case 1:
						setType(block, 126, top ? 1 : 9);
						break;
					case 2:
						setType(block, 126, top ? 2 : 10);
						break;
					case 3:
						setType(block, 126, top ? 3 : 11);
						break;
					case 4:
						setType(block, 126, top ? 4 : 12);
						break;
					case 5:
						setType(block, 126, top ? 5 : 13);
						break;
					case 6:
						setType(block, 126, top ? 6 : 14);
						break;
					case 7:
						setType(block, 126, top ? 7 : 15);
						break;
					case 8:
						setType(block, 126, top ? 0 : 8);
						break;
					case 9:
						setType(block, 126, top ? 1 : 9);
						break;
					case 10:
						setType(block, 126, top ? 2 : 10);
						break;
					case 11:
						setType(block, 126, top ? 3 : 11);
						break;
					case 12:
						setType(block, 126, top ? 4 : 12);
						break;
					case 13:
						setType(block, 126, top ? 5 : 13);
						break;
					case 14:
						setType(block, 126, top ? 6 : 14);
						break;
					case 15:
						setType(block, 126, top ? 7 : 15);
						break;
					}
				}
				if(getConfig().getBoolean(p.getUniqueId().toString() + ".particle")){
					block.getWorld().playEffect(position, Effect.SMOKE, 0);
				}
				e.setCancelled(true);
				break;
		    }
		}
	}

	@SuppressWarnings("deprecation")
	private void setType(Block block, int type, int data){
		block.setTypeId(type);
		block.setData((byte) data);
	}

	/* Spigot 1.7.10
	 * [20:51:30 INFO]: 44:0
[20:51:31 INFO]: 44:8
[20:51:32 INFO]: 43:0
[20:51:32 INFO]: 63.90112512547406
[20:51:41 INFO]: 44:1
[20:51:42 INFO]: 44:9
[20:51:43 INFO]: 43:1
[20:51:43 INFO]: 63.45271778980772
[20:51:47 INFO]: 44:3
[20:51:47 INFO]: 44:11
[20:51:48 INFO]: 43:3
[20:51:48 INFO]: 63.8223530666304
[20:51:52 INFO]: 44:4
[20:51:52 INFO]: 44:12
[20:51:53 INFO]: 43:4
[20:51:53 INFO]: 63.6684331428043
[20:52:00 INFO]: 44:5
[20:52:00 INFO]: 44:13
[20:52:00 INFO]: 43:5
[20:52:00 INFO]: 63.693710529153094
[20:52:05 INFO]: 44:6
[20:52:05 INFO]: 44:14
[20:52:06 INFO]: 43:6
[20:52:06 INFO]: 63.900054358796254
[20:52:13 INFO]: 44:7
[20:52:13 INFO]: 44:15
[20:52:14 INFO]: 43:7
[20:52:14 INFO]: 63.92830018218419
[20:52:26 INFO]: 126:0
[20:52:27 INFO]: 126:8
[20:52:29 INFO]: 125:0
[20:52:29 INFO]: 63.94545249860804
[20:52:38 INFO]: 126:1
[20:52:39 INFO]: 126:9
[20:52:39 INFO]: 125:1
[20:52:39 INFO]: 63.968151378265134
[20:52:44 INFO]: 126:2
[20:52:45 INFO]: 126:10
[20:52:45 INFO]: 125:2
[20:52:45 INFO]: 63.81965044574654
[20:52:50 INFO]: 126:3
[20:52:52 INFO]: 126:11
[20:52:52 INFO]: 125:3
[20:52:52 INFO]: 63.982117116982934
[20:52:56 INFO]: 126:4
[20:52:56 INFO]: 126:12
[20:52:57 INFO]: 125:4
[20:52:57 INFO]: 63.93211270820337
[20:53:01 INFO]: 126:5
[20:53:01 INFO]: 126:13
[20:53:01 INFO]: 125:5
[20:53:01 INFO]: 63.951336180959565
	 */

}
