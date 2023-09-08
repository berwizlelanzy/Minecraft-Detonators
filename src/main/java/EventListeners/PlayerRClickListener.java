package EventListeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import RClickStates.RClickState;
import net.md_5.bungee.api.ChatColor;

public class PlayerRClickListener implements Listener {
    private static PlayerRClickListener instance;
    private RClickState state;

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

    public void setState(RClickState state) {
        this.state = state;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        if (this.state == null) {
            return;
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
            if (!this.checkBlock(e.getClickedBlock())) {
                e.getPlayer().sendMessage(ChatColor.RED + "Please right-click on a tnt!");
                return;
            }

            this.state.onPlayerInteract(e);
            this.state = null;  // reset state after rclicking a tnt
        }   
    }
}
