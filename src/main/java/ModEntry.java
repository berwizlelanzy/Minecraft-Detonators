import org.bukkit.plugin.java.JavaPlugin;

import CommandHandlers.*;
import Commands.*;
import EventListeners.*;
import UI.UI;

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
        
        ChangeTypeHandler changeTypeHandler = new ChangeTypeHandler();
        changeTypeHandler.setCommand(new ChangeTypeCommand());

        UndoTypeHandler undoTypeHandler = new UndoTypeHandler();
        undoTypeHandler.setCommand(new UndoCommand());

        DetonateAllHandler detonateAllHandler = new DetonateAllHandler();
        detonateAllHandler.setCommand(new DetonateAllCommand());

        getCommand("settnt").setExecutor(tntHandler);
        getCommand("setcode").setExecutor(codeHandler);
        getCommand("detonate").setExecutor(detonateHandler);
        getCommand("tnttimer").setExecutor(tntTimeHandler);
        getCommand("changetype").setExecutor(changeTypeHandler);
        getCommand("undotype").setExecutor(undoTypeHandler);
        getCommand("detonateall").setExecutor(detonateAllHandler);

        UI ui = new UI();
        ui.createUI();
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Disabling Detonators plugin...");
    }
}
