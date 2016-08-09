package uk.tim740.AliasesUpCheck;

import org.bukkit.Bukkit;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

import static org.bukkit.Bukkit.getLogger;

/**
 * Created by tim740 on 09/08/2016
 */
class AliasesUpdater {
    static void check() {
        getLogger().info("Checking for update now you will be notified if there is an update!");
        File pth = new File("plugins" + File.separator + "Skript" + File.separator + "aliases-english.sk");
        String v = "";
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skAliases/master/version.txt").openStream()));
            v = ur.readLine();
            ur.close();
        } catch (Exception e) {
            getLogger().warning("Error while checking for update!");
        }
        String fv = "";
        try {
            BufferedReader fr = new BufferedReader(new FileReader(pth));
            fv = fr.readLine().replaceAll("#! VERSION: ", "").replaceAll("!", "");
            fr.close();
        } catch (Exception e) {
            getLogger().warning("Error while reading the aliases file!");
        }
        if (!Objects.equals(fv, v)){
            getLogger().info("A new version of the aliases is out v" + v + " (Currently using v" + fv + ")");
            Bukkit.broadcast("[AliasesUpdaterChecker] A new version of the aliases is out v" + v + " (Currently using v" + fv + ")", "skAliasesVerCheck.notice");

            File ptho = new File("plugins" + File.separator + "Skript" + File.separator + "aliases-english-v" + v + ".sk");
            //noinspection ResultOfMethodCallIgnored
            pth.renameTo(ptho);

            getLogger().info("Starting download of Aliases v" + v);
            try {
                ReadableByteChannel rbc = Channels.newChannel(new URL("https://github.com/tim740/skAliases/releases/download/v" + v + "/aliases-english.sk").openStream());
                FileOutputStream fos = new FileOutputStream(pth);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
            } catch (Exception e) {
                getLogger().severe("Error while downloading latest update!");
            }
            getLogger().info("Finished downloading Aliases v" + v);
        }else{
            getLogger().info("Currently using the latest version!");
        }

    }
}
