import org.bukkit.plugin.java.JavaPlugin;

import EventListeners.TntEventListener;

public class ModEntry extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(TntEventListener.getInstance(), this);
        getLogger().warning("Enabling Detonators plugin...");
    }
    
    @Override
    public void onDisable() {
        getLogger().warning("Disabling Detonators plugin...");
    }
}
