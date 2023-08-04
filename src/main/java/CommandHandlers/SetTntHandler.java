package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import EventListeners.PlayerRClickListener;

public class SetTntHandler implements CommandExecutor {
    private CommandChecker checker;

    public SetTntHandler() {
        this.checker = new CommandChecker();
    }

    // Return true if the command can be executed by the sender
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!this.checker.check(sender, label)) {
            // Langsung return aja
            return true;
        }

        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        if (listener.mode == "remote") {
            listener.mode = "";
            sender.sendMessage("R-Click interact disabled");
        } else {
            listener.mode = "remote";
            sender.sendMessage("R-Click interact enabled");
        }

        return true;
    }    
}
