package uk.tim740.AliasesUpCheck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by tim740 on 06/03/2016
 */
public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, AliasesUpdater::check, 1L, 864000L);
    }

}
