package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import Builder.TntBuilder;
import EventListeners.TntDestroyListener;
import Fuzes.TimeFuze;
import Tnt.Tnt;
import net.md_5.bungee.api.ChatColor;

public class TimeState implements RClickState {
    private int time;

    public TimeState(int time) {
        this.time = time;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block tntBlock = e.getClickedBlock();
        Player player = e.getPlayer();

        TntBuilder builder = new TntBuilder();
        builder.setBlock(tntBlock);
        builder.setFuze(new TimeFuze());

        Tnt tnt = builder.build();
        
        TntDestroyListener destroyListener = TntDestroyListener.getInstance();
        destroyListener.addTnt(tnt);
        
        player.sendMessage(ChatColor.YELLOW + "Tnt will explode " + time + " sec(s) from now!");

        ((TimeFuze) tnt.getFuze()).beginCountdown(time);
    }
}
