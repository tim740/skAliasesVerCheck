package uk.tim740.AliasesUpCheck;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

/**
 * Created by tim740 on 06/03/2016
 */
public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
        verChkTimer();
    }

    public void verChk() {
        getLogger().info("Checking for update now you will be notified if there is an update!");
        String v = "";
        try {
           // URL url = new URL("https://tim740.github.io/aliasesVer");
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://tim740.github.io/aliasesVer").openStream()));
            String inLi;
            while ((inLi = in.readLine()) != null)
                v = inLi;
            in.close();
        } catch (Exception e) {
            getLogger().severe(e.getCause().getMessage());
        }
        String fv = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("plugins\\Skript\\aliases-english.sk"));
            fv = br.readLine().replaceAll("#! VERSION: ", "").replaceAll("!", "");
        } catch (Exception e) {
            getLogger().severe(e.getCause().getMessage());
        }
        if (!Objects.equals(fv, v)){
            getLogger().warning("A new version of the aliases is out v" + v + " (Currently using v" + fv + ")");
            getLogger().warning("You can find the latest version here: https://forums.skunity.com/t/latest-aliases-v1-9-id-order-full-1-9-support/40");

            Bukkit.broadcast("[skAliasesVerCheck] A new version of the aliases is out v" + v + " (Currently using v" + fv + ")", "skAliasesVerCheck.notice");
            Bukkit.broadcast("[skAliasesVerCheck] You can find the latest version here: https://forums.skunity.com/t/latest-aliases-v1-9-id-order-full-1-9-support/40", "skAliasesVerCheck.notice");
        }else{
            getLogger().info("It seems like your using the latest version!");
        }
    }

    public void verChkTimer() {
        Thread t = new Thread() {
            @SuppressWarnings("InfiniteLoopStatement")
            @Override
            public void run() {
                while (true) {
                    try {
                        verChk();
                        Thread.sleep(1000*60*60*12);
                    } catch (InterruptedException e) {
                        getLogger().severe(e.getCause().getMessage());
                    }
                }
            }
        };
        t.start();
    }
}
