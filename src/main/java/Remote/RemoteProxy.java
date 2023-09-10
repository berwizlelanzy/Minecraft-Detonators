package Remote;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class RemoteProxy implements RemoteObserver {
    private RemoteObserver observer;

    public RemoteProxy(RemoteObserver observer) {
        this.observer = observer;
    }

    @Override
    public void fire() {
        JavaPlugin.getProvidingPlugin(getClass()).getLogger().info("DETONATING ALL REMOTE FUZES!!!");
        observer.fire();
    }

    public RemoteObserver getObserver() {
        return this.observer;
    }
}