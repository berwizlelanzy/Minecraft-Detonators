package CommandHandlers;

// import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.CommandPattern;
import Commands.DetonateCommand;
// import Fuzes.CodeFuze;
// import Fuzes.CodeFuzeFactory;
// import Remote.RemoteBroadcaster;
// import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatColor;

public class DetonateHandler implements CommandExecutor{
    private CommandPattern command;

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 || args.length > 1) {
            return false;
        }

        if (this.command == null) {
            sender.sendMessage(ChatColor.RED + "No command specified!");
            return true;
        }

        DetonateCommand cmd = (DetonateCommand) this.command;
        cmd.setParam(args[0]);
        cmd.execute();
        return true;
    }
}
