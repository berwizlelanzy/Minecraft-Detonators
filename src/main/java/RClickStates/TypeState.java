package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import EventListeners.TntDestroyListener;
import ExplosionTypes.*;
import Tnt.Tnt;
import TntCollection.TntIterator;
import net.md_5.bungee.api.ChatColor;

public class TypeState implements RClickState {
    private String type;

    public TypeState(String type) {
        this.type = type;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block tntBlock = e.getClickedBlock();
        Player player = e.getPlayer();

        TntIterator tnts = TntDestroyListener.getInstance().getTntsIterator();
        while (tnts.hasNext()) {
            Tnt tnt = tnts.getNext().getTnt();

            if (tnt.getExpType().getBlock().equals(tntBlock)) {
                switch (type) {
                    case "water":
                        tnt.setExpType(new WaterExplosion(tntBlock));
                        player.sendMessage(ChatColor.BLUE + "Tnt type changed to Water!");
                    break;
                    case "lava":
                        tnt.setExpType(new LavaExplosion(tntBlock));
                        player.sendMessage(ChatColor.YELLOW + "Tnt type changed to Lava!");
                    break;
                    default:
                        tnt.setExpType(new NormalExplosion(tntBlock));
                        player.sendMessage("Tnt type changed to Normal!");
                    break;
                }
                
                return;
            }
        }

        player.sendMessage(ChatColor.RED + "Tnt not found! (Is Fuze set?)");
    }
}
