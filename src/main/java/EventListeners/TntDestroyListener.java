package EventListeners;

import java.util.Vector;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Fuzes.RemoteFuze;
import Fuzes.Enums.FuzeType;
import Remote.RemoteBroadcaster;
import Tnt.Tnt;

public class TntDestroyListener implements Listener {
    private static TntDestroyListener instance;
    private Vector<Tnt> tnts;

    private TntDestroyListener() {
        this.tnts = new Vector<Tnt>();
    }

    public static TntDestroyListener getInstance() {
        if (instance == null) {
            instance = new TntDestroyListener();
        }

        return instance;
    }

    private boolean removeTnt(Tnt tnt) {
        boolean fuzeDisarmed = false;

        if (tnt.getFuze() instanceof RemoteFuze) {
            RemoteBroadcaster broadcaster = RemoteBroadcaster.getInstance();
            fuzeDisarmed = broadcaster.removeRemote((RemoteFuze) tnt.getFuze());
        }
        
        return this.tnts.remove(tnt) && fuzeDisarmed;
    }

    public void addTnt(Tnt tnt) {
        this.tnts.add(tnt);

        if (tnt.getFuze() instanceof RemoteFuze) {
            RemoteBroadcaster broadcaster = RemoteBroadcaster.getInstance();
            broadcaster.addRemote((RemoteFuze) tnt.getFuze());
        }
    }

    // Used to clear tnts after detonation
    public void clearTnt(FuzeType fuzeType) {
        if (fuzeType.equals(FuzeType.FUZE_REMOTE)) {
            this.tnts.removeIf(tnt -> tnt.getFuze() instanceof RemoteFuze);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.TNT) {
            return;
        }

        boolean removed = false;

        for (Tnt tnt : this.tnts) {
            if (tnt.getBlock().equals(e.getBlock())) {
                removed = this.removeTnt(tnt);
                break;
            }
        }

        if (removed) {
            e.getPlayer().sendMessage("Tnt fuze removed");
        }
    }
}
