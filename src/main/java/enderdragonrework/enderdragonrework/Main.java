package enderdragonrework.enderdragonrework;

import enderdragonrework.enderdragonrework.Listener.EntityDeathListener;
import enderdragonrework.enderdragonrework.Listener.EntitySpawnListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    public boolean canKillDragon = true;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
        getServer().getPluginManager().registerEvents(new EntitySpawnListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
