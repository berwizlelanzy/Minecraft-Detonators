package EventListeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import Builder.TntBuilder;
import Fuzes.CodeFuzeFactory;
import Fuzes.RemoteFuze;
import Fuzes.TimeFuze;
import Tnt.Tnt;
import net.md_5.bungee.api.ChatColor;

public class PlayerRClickListener implements Listener {
    private static PlayerRClickListener instance;
    public String mode;
    public String arg;

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
                e.getPlayer().sendMessage(ChatColor.RED + "Please right-click on a tnt!");
                return;
            }

            Block tntBlock = e.getClickedBlock();
            Player player = e.getPlayer();

            if (this.mode.equals("remote")) {
                // Tnt tnt = new Tnt(tntBlock, new RemoteFuze());
                TntBuilder builder = new TntBuilder();
                builder.setBlock(tntBlock);
                builder.setFuze(new RemoteFuze());
                Tnt tnt = builder.build();

                TntDestroyListener destroyListener = TntDestroyListener.getInstance();
                destroyListener.addTnt(tnt);

                player.sendMessage(ChatColor.YELLOW + "Tnt added to remote list.");
                this.mode = "";
            } else if (this.mode.equals("code")) {
                if (this.arg == null) {
                    e.getPlayer().sendMessage("Null code!");
                    return;
                }

                // Tnt tnt = new Tnt(tntBlock, CodeFuzeFactory.getInstance().getFuze(this.arg));
                TntBuilder builder = new TntBuilder();
                builder.setBlock(tntBlock);
                builder.setFuze(CodeFuzeFactory.getInstance().getFuze(this.arg));
                Tnt tnt = builder.build();
                
                TntDestroyListener destroyListener = TntDestroyListener.getInstance();
                destroyListener.addTnt(tnt);

                player.sendMessage(ChatColor.YELLOW + "Tnt code set!");
                this.mode = "";
                this.arg = "";
            } else if (this.mode.equals("time")) {
                Tnt tnt = new Tnt(tntBlock, new TimeFuze());
                TntDestroyListener destroyListener = TntDestroyListener.getInstance();
                destroyListener.addTnt(tnt);
                
                player.sendMessage(ChatColor.YELLOW + "Tnt will explode " + arg + " sec(s) from now!");

                try {
                    ((TimeFuze) tnt.getFuze()).beginCountdown(Integer.parseInt(arg));
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                }

                this.mode = "";
                this.arg = "";
            }
        }   
    }
}
