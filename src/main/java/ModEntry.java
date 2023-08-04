import org.bukkit.plugin.java.JavaPlugin;

import CommandHandlers.DetonateHandler;
import CommandHandlers.SetTntHandler;
import EventListeners.*;

public class ModEntry extends JavaPlugin {
    // TODO: Check if tnt is removed
    @Override
    public void onEnable() {
        getLogger().info("Enabling Detonators plugin...");
        getServer().getPluginManager().registerEvents(PlayerRClickListener.getInstance(), this);

        getCommand("settnt").setExecutor(new SetTntHandler());
        getCommand("detonate").setExecutor(new DetonateHandler());
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Disabling Detonators plugin...");
    }
}
