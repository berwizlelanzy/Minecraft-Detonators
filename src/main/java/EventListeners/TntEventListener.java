package EventListeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TntEventListener implements Listener {
    private static TntEventListener instance;

    private TntEventListener(){}

    public static TntEventListener getInstance() {
        if (instance == null) {
            instance = new TntEventListener();
        }

        return instance;
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() == org.bukkit.Material.TNT) {
            e.getBlock().setType(Material.AIR);
            final double z = e.getBlock().getZ();
            final double y = e.getBlock().getY();
            final double x = e.getBlock().getX();
            e.getBlock().getWorld().createExplosion(x, y, z, 4F, false, true);
        }
    }
}
