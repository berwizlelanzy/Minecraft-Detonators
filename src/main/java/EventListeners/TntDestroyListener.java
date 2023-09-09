package EventListeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import Fuzes.CodeFuze;
import Fuzes.RemoteFuze;
import Fuzes.Enums.FuzeType;
import Remote.RemoteBroadcaster;
import Tnt.Tnt;
import TntCollection.TntCollection;
import TntCollection.TntElement;
import TntCollection.TntIterator;
import net.md_5.bungee.api.ChatColor;

public class TntDestroyListener implements Listener {
    private static TntDestroyListener instance;
    private TntCollection tnts;

    private TntDestroyListener() {
        this.tnts = new TntCollection();
    }

    public static TntDestroyListener getInstance() {
        if (instance == null) {
            instance = new TntDestroyListener();
        }

        return instance;
    }

    private boolean removeTnt(Tnt tnt) {
        return this.tnts.remove(tnt);
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
        TntIterator iter = this.tnts.createIterator();

        if (fuzeType.equals(FuzeType.FUZE_REMOTE)) {
            while (iter.hasNext()) {
                TntElement e = iter.getNext();
                if (e.getTnt().getFuze() instanceof RemoteFuze) {
                    this.removeTnt(e.getTnt());
                }
            }
        } else if (fuzeType.equals(FuzeType.FUZE_CODE)) {
            while (iter.hasNext()) {
                TntElement e = iter.getNext();
                if (e.getTnt().getFuze() instanceof CodeFuze) {
                    this.removeTnt(e.getTnt());
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() != Material.TNT) {
            return;
        }

        boolean removed = false;

        TntIterator iter = this.tnts.createIterator();
        while(iter.hasNext()) {
            Tnt tnt = iter.getNext().getTnt();
            Block tntBlock = tnt.getExpType().getBlock();
            if (tntBlock.equals(e.getBlock())) {
                removed = this.removeTnt(tnt);
                break;
            }
        }

        if (removed) {
            e.getPlayer().sendMessage(ChatColor.GREEN + "Tnt fuze removed!");
        }
    }

    public TntIterator getTntsIterator() {
        return this.tnts.createIterator();
    }
}
