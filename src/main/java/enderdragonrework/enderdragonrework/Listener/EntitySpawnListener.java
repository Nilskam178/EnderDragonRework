package enderdragonrework.enderdragonrework.Listener;

import enderdragonrework.enderdragonrework.Main;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    private Main main = Main.instance;

    @EventHandler
    void onEntitySpawn(EntitySpawnEvent event) {

        Entity entity = event.getEntity();

        if(entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();

            if(!main.canKillDragon) {
                if(entity instanceof EnderDragon) {
                    livingEntity.setHealth(0);
                }
            }
        }
    }
}
