package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import Builder.TntBuilder;
import EventListeners.TntDestroyListener;
import ExplosionTypes.*;
import Fuzes.RemoteFuze;
import Tnt.Tnt;
import net.md_5.bungee.api.ChatColor;

public class RemoteState implements RClickState {
    private String type;

    public RemoteState(String type) {
        if (type == null) {
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
        builder.setFuze(new RemoteFuze());

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

        player.sendMessage(ChatColor.YELLOW + "Tnt added to remote list.");
    }
}
