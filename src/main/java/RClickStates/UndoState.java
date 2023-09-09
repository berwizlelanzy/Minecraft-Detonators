package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import EventListeners.TntDestroyListener;
import ExplosionTypes.LavaExplosion;
import ExplosionTypes.WaterExplosion;
import Tnt.Tnt;
import TntCollection.TntIterator;
import net.md_5.bungee.api.ChatColor;

public class UndoState implements RClickState {
    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block tntBlock = e.getClickedBlock();
        Player player = e.getPlayer();

        TntIterator tnts = TntDestroyListener.getInstance().getTntsIterator();
        while (tnts.hasNext()) {
            Tnt tnt = tnts.getNext().getTnt();

            if (tnt.getExpType().getBlock().equals(tntBlock)) {
                tnt.undoExpType();
                if (tnt.getExpType() instanceof WaterExplosion) {
                    player.sendMessage(ChatColor.BLUE + "Tnt type changed back to Water!");
                } else if (tnt.getExpType() instanceof LavaExplosion) {
                    player.sendMessage(ChatColor.YELLOW + "Tnt type changed back to Lava!");
                } else {
                    player.sendMessage("Tnt type changed back to Normal!");
                }
                
                return;
            }
        }

        player.sendMessage(ChatColor.RED + "Nothing to undo!");
    }
}
