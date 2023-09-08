import org.bukkit.plugin.java.JavaPlugin;

import CommandHandlers.*;
import Commands.*;
import EventListeners.*;

public class ModEntry extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Enabling Detonators plugin...");
        getServer().getPluginManager().registerEvents(PlayerRClickListener.getInstance(), this);
        getServer().getPluginManager().registerEvents(TntDestroyListener.getInstance(), this);

        SetTntHandler tntHandler = new SetTntHandler();
        tntHandler.setCommand(new SetTntCommand());

        CodeHandler codeHandler = new CodeHandler();
        codeHandler.setCommand(new SetCodeCommand());

        TntTimeHandler tntTimeHandler = new TntTimeHandler();
        tntTimeHandler.setCommand(new SetTimeCommand());

        DetonateHandler detonateHandler = new DetonateHandler();
        detonateHandler.setCommand(new DetonateCommand());

        getCommand("settnt").setExecutor(tntHandler);
        getCommand("setcode").setExecutor(codeHandler);
        getCommand("detonate").setExecutor(detonateHandler);
        getCommand("tnttimer").setExecutor(tntTimeHandler);
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Disabling Detonators plugin...");
    }
}
