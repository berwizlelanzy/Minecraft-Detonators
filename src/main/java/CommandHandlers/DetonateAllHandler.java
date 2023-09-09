package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.CommandPattern;
import net.md_5.bungee.api.ChatColor;

public class DetonateAllHandler implements CommandExecutor {
    private CommandPattern command;

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.command == null) {
            sender.sendMessage(ChatColor.RED + "No command specified!");
            return true;
        }
        
        this.command.execute();
        return true;
    }
}
