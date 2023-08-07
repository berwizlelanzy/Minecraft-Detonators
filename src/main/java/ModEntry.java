import org.bukkit.plugin.java.JavaPlugin;

import CommandHandlers.CodeHandler;
import CommandHandlers.DetonateHandler;
import CommandHandlers.SetTntHandler;
import CommandHandlers.TntTimeHandler;
import EventListeners.*;

public class ModEntry extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Enabling Detonators plugin...");
        getServer().getPluginManager().registerEvents(PlayerRClickListener.getInstance(), this);
        getServer().getPluginManager().registerEvents(TntDestroyListener.getInstance(), this);

        getCommand("settnt").setExecutor(new SetTntHandler());
        getCommand("setcode").setExecutor(new CodeHandler());
        getCommand("detonate").setExecutor(new DetonateHandler());
        getCommand("tnttimer").setExecutor(new TntTimeHandler());
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Disabling Detonators plugin...");
    }
}
