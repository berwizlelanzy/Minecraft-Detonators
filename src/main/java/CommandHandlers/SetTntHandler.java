package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.CommandPattern;
import net.md_5.bungee.api.ChatColor;

public class SetTntHandler implements CommandExecutor {
    private CommandChecker checker;
    private CommandPattern command;

    public SetTntHandler() {
        this.checker = new CommandChecker();
    }

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

    // Return true if the command can be executed by the sender
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!this.checker.check(sender, label)) {
            // Langsung return aja
            return true;
        }

        if (this.command == null) {
            sender.sendMessage(ChatColor.RED + "No command specified!");
            return true;
        }

        this.command.execute();

        return true;
    }    
}
