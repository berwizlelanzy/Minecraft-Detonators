package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Remote.RemoteBroadcaster;

public class DetonateHandler implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 || args.length > 1) {
            return false;
        }

        if (args[0].equals("remote")) {
            sender.sendMessage("Detonating remote fuzes");
            RemoteBroadcaster.getInstance().fire();
        }
        return true;
    }
}
