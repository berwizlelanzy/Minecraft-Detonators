package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.CommandPattern;
import Commands.SetCodeCommand;
import net.md_5.bungee.api.ChatColor;

public class CodeHandler implements CommandExecutor {
    private CommandChecker checker;
    private CommandPattern command;

    public CodeHandler() {
        this.checker = new CommandChecker();
    }

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!checker.check(sender, label)) {
            return true;
        } else if (args.length == 0 || args.length > 2) {
            return false;
        } else if (args[0].length() != 4) {
            sender.sendMessage("Code must be 4 digits long!");
            return true;
        }

        if (this.command == null) {
            sender.sendMessage(ChatColor.RED + "No command specified!");
            return true;
        }

        ((SetCodeCommand) this.command).setParam(args[0]);
        if (args.length == 2) {
            ((SetCodeCommand) this.command).setType(args[1]);
        }
        
        this.command.execute();
        
        return true;
    }
}
