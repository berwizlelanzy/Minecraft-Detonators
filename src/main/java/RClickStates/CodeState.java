package RClickStates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import Builder.TntBuilder;
import EventListeners.TntDestroyListener;
import ExplosionTypes.LavaExplosion;
import ExplosionTypes.NormalExplosion;
import ExplosionTypes.WaterExplosion;
import Fuzes.CodeFuzeFactory;
import Tnt.Tnt;
import net.md_5.bungee.api.ChatColor;

public class CodeState implements RClickState {
    private String code;
    private String type;

    public CodeState(String code, String type) {
        this.code = code;

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

        if (this.code == null) {
            e.getPlayer().sendMessage("Null code!");
            return;
        }

        TntBuilder builder = new TntBuilder();
        builder.setBlock(tntBlock);
        builder.setFuze(CodeFuzeFactory.getInstance().getFuze(this.code));

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

        player.sendMessage(ChatColor.YELLOW + "Tnt code set!");
    }
}
