package RClickStates;

import org.bukkit.event.player.PlayerInteractEvent;

public interface RClickState {
    public abstract void onPlayerInteract(PlayerInteractEvent e);
}
