package enderdragonrework.enderdragonrework.Listener;

import enderdragonrework.enderdragonrework.Cooldown.CooldownHandler;
import enderdragonrework.enderdragonrework.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeathListener implements Listener {

    private Main main = Main.instance;

    public String color(String msg) { return ChatColor.translateAlternateColorCodes('&', msg); }

    @EventHandler
    void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if(entity instanceof EnderDragon) {

            if(killer != null) {
                killer.sendTitle(color("&5&kL&r &5&lEnder Dragon killed &5&kL"),"");

                if(main.getConfig().getBoolean("dropHead")) {
                    killer.getInventory().addItem(new ItemStack(Material.DRAGON_HEAD));
                }

                if(main.getConfig().getBoolean("dropEgg")) {
                    killer.getInventory().addItem(new ItemStack(Material.DRAGON_EGG));
                }

                if(main.getConfig().getBoolean("dropElytra")) {
                    killer.getInventory().addItem(new ItemStack(Material.ELYTRA));
                }

                Bukkit.broadcastMessage(color("&5&lDragon has been slayed"));
                main.canKillDragon = false;

                CooldownHandler cooldownHandler = new CooldownHandler();
                cooldownHandler.start(main.getConfig().getInt("cooldownSeconds"));
            }
        }
    }
}
