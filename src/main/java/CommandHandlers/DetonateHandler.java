package CommandHandlers;

import java.util.Vector;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Fuzes.CodeFuze;
import Fuzes.CodeFuzeFactory;
import Remote.RemoteBroadcaster;
import net.md_5.bungee.api.ChatColor;

public class DetonateHandler implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0 || args.length > 1) {
            return false;
        }

        if (args[0].equals("remote")) {
            sender.sendMessage(ChatColor.RED + "Detonating remote fuzes");
            RemoteBroadcaster.getInstance().fire();
        } else {
            final String code = args[0];
            if (code.length() != 4) {
                sender.sendMessage(ChatColor.RED + "Code must be 4 digits long!");
                return true;
            }
            
            Vector<CodeFuze> codeFuzes = CodeFuzeFactory.getInstance().getFuzeList();
            for (CodeFuze codeFuze : codeFuzes) {
                if (codeFuze.getCode().equals(code)) {
                    sender.sendMessage(ChatColor.RED + "Detonating code: " + code + " fuzes");
                    codeFuze.trigger();
                }
            }
        }
        return true;
    }
}
