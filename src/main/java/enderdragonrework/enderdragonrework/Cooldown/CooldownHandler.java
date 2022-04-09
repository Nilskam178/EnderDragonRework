package enderdragonrework.enderdragonrework.Cooldown;

import enderdragonrework.enderdragonrework.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;

public class CooldownHandler {

    public String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }


    private Main main = Main.instance;

    private int countdownTimer;

    private BossBar countDownBar;

    public void start(final int time)
    {
        countDownBar = Bukkit.createBossBar(
                color("&5&lYou can kill the dragon in: " + time),
                BarColor.PURPLE,
                BarStyle.SOLID
        );

        this.countdownTimer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable()
                {
                    int i = time;

                    public void run()
                    {
                        this.i--;

                        countDownBar.setTitle(color("&5&lDragon can be spawned in: " + i));
                        countDownBar.setProgress(1);
                        
                        for (Player p : Bukkit.getOnlinePlayers()) {

                            if(p.getWorld().getEnvironment() == World.Environment.THE_END) {
                                countDownBar.addPlayer(p);
                            }
                        }

                        if (this.i <= 0)
                        {
                            main.canKillDragon = true;

                            countDownBar.removeAll();

                            Bukkit.broadcastMessage(color("&5&lDragon can be spawned"));
                            cancel();
                        }
                    }
                }
                , 0L, 20L);
    }

    public void cancel()
    {
        Bukkit.getScheduler().cancelTask(this.countdownTimer);
    }
}
