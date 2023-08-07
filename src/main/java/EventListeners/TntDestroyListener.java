package EventListeners;

import java.util.Vector;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Fuzes.CodeFuze;
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
        boolean removed = false;

        if (tnt.getFuze() instanceof RemoteFuze) {
            RemoteBroadcaster broadcaster = RemoteBroadcaster.getInstance();
            broadcaster.removeRemote((RemoteFuze) tnt.getFuze());
            removed = this.tnts.remove(tnt);
        } else if (tnt.getFuze() instanceof CodeFuze) {
            final String code = ((CodeFuze) tnt.getFuze()).getCode();
            removed = this.tnts.remove(tnt);
            tnt.disarmFuze();

            for (Tnt tnt2 : this.tnts) {
                if (tnt2.getFuze() instanceof CodeFuze && ((CodeFuze) tnt2.getFuze()).getCode().equals(code)) {
                    tnt2.armFuze();
                }
            }
        }
        
        return removed;
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
        } else if (fuzeType.equals(FuzeType.FUZE_CODE)) {
            this.tnts.removeIf(tnt -> tnt.getFuze() instanceof CodeFuze);
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
