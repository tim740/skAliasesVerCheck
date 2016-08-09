package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import uk.tim740.skUtilities.util.*;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 09/08/2016
 */
class AliasesUpdater {
    static void check() {
        getLogger().info("Checking for update now you will be notified if there is an update!");
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
            BufferedReader fr = new BufferedReader(new FileReader("plugins" + File.sep +"Skript\\aliases-english.sk"));
            fv = fr.readLine().replaceAll("#! VERSION: ", "").replaceAll("!", "");
            fr.close();
        } catch (Exception e) {
            getLogger().warning("Error while reading the aliases file!");
        }
        if (!Objects.equals(fv, v)){
            getLogger().info("A new version of the aliases is out v" + v + " (Currently using v" + fv + ")");
            getLogger().info("You can find the latest version here: https://github.com/tim740/skAliases/releases/latest");

            Bukkit.broadcast("[AliasesUpdaterChecker] A new version of the aliases is out v" + v + " (Currently using v" + fv + ")", "skAliasesVerCheck.notice");
            Bukkit.broadcast("[AliasesUpdaterChecker] You can find the latest version here: https://github.com/tim740/skAliases/releases/latest", "skAliasesVerCheck.notice");
        }else{
            getLogger().info("Currently using the latest version!");
        }

    }

    static void forceUpdate() {

    }
}
