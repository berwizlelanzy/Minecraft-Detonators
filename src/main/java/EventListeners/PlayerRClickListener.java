package EventListeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import Fuzes.RemoteFuze;
import Remote.RemoteBroadcaster;
import Tnt.Tnt;

public class PlayerRClickListener implements Listener {
    private static PlayerRClickListener instance;
    public String mode;

    private PlayerRClickListener(){
        this.mode = "";
    }

    public static PlayerRClickListener getInstance(){
        if (instance == null) {
            instance = new PlayerRClickListener();
        }

        return instance;
    }

    private boolean checkBlock(Block block){
        if (block.getType() == Material.TNT) {
            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if (this.mode.equals("")) {
            return;
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!this.checkBlock(e.getClickedBlock())) {
                e.getPlayer().sendMessage("Please right-click on a tnt!");
                return;
            }

            Block tntBlock = e.getClickedBlock();
            Player player = e.getPlayer();

            if (this.mode.equals("remote")) {
                Tnt tnt = new Tnt(new RemoteFuze(tntBlock));
                RemoteBroadcaster broadcaster = RemoteBroadcaster.getInstance();
                broadcaster.addRemote((RemoteFuze) tnt.getFuze());
                player.sendMessage("Tnt added to remote list.");
                this.mode = "";
            }
        }   
    }
}
