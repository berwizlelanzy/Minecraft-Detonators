package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import EventListeners.PlayerRClickListener;
import net.md_5.bungee.api.ChatColor;

public class TntTimeHandler implements CommandExecutor {
    private CommandChecker checker;

    public TntTimeHandler() {
        this.checker = new CommandChecker();
    }

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!this.checker.check(sender, label)) {
            return true;
        } else if (args.length != 1) {
            return false;
        }

        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        if (listener.mode == "time") {
            listener.mode = "";
            listener.arg = "";
            sender.sendMessage("Time R-Click interact disabled");
        } else {
            listener.mode = "time";
            listener.arg = args[0];
            sender.sendMessage("Time R-Click interact enabled");
            sender.sendMessage(ChatColor.RED + "Warning: Timed tnt can't be defused!");
        }

		return true;
	}
}
