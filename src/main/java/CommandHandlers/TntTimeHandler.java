package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.CommandPattern;
import Commands.SetTimeCommand;
import net.md_5.bungee.api.ChatColor;

public class TntTimeHandler implements CommandExecutor {
    private CommandChecker checker;
    private CommandPattern command;

    public TntTimeHandler() {
        this.checker = new CommandChecker();
    }

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!this.checker.check(sender, label)) {
            return true;
        } else if (args.length > 2 || args.length == 0) {
            return false;
        }

        if (this.command == null) {
            sender.sendMessage(ChatColor.RED + "No command specified!");
            return true;
        }

        try {
            ((SetTimeCommand) this.command).setParam(Integer.parseInt(args[0]));
            if (args.length == 2) {
                ((SetTimeCommand) this.command).setType(args[1]);
            }
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please provide a valid number!");
        }

        this.command.execute();

		return true;
	}
}
