package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import Builder.TntBuilder;
import EventListeners.TntDestroyListener;
import ExplosionTypes.LavaExplosion;
import ExplosionTypes.NormalExplosion;
import ExplosionTypes.WaterExplosion;
import Fuzes.TimeFuze;
import Tnt.Tnt;
import net.md_5.bungee.api.ChatColor;

public class TimeState implements RClickState {
    private int time;
    private String type;

    public TimeState(int time, String type) {
        this.time = time;
        
        if (type == null){
            this.type = "";
        } else {
            this.type = type;
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent e) {
        Block tntBlock = e.getClickedBlock();
        Player player = e.getPlayer();

        TntBuilder builder = new TntBuilder();
        builder.setBlock(tntBlock);
        builder.setFuze(new TimeFuze());

        switch(this.type){
            case "lava":
                builder.setExpType(new LavaExplosion(tntBlock));
            break;
            case "water":
                builder.setExpType(new WaterExplosion(tntBlock));
            break;
            default:
                builder.setExpType(new NormalExplosion(tntBlock));
            break;
        }

        Tnt tnt = builder.build();
        
        TntDestroyListener destroyListener = TntDestroyListener.getInstance();
        destroyListener.addTnt(tnt);
        
        player.sendMessage(ChatColor.YELLOW + "Tnt will explode " + time + " sec(s) from now!");

        ((TimeFuze) tnt.getFuze()).beginCountdown(time);
    }
}
