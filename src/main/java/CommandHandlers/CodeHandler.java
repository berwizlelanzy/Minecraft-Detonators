package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import EventListeners.PlayerRClickListener;

public class CodeHandler implements CommandExecutor {
    private CommandChecker checker;

    public CodeHandler() {
        this.checker = new CommandChecker();
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!checker.check(sender, label)) {
            return true;
        } else if (args.length == 0 || args.length > 1) {
            return false;
        } else if (args[0].length() != 4) {
            sender.sendMessage("Code must be 4 digits long!");
            return true;
        }

        PlayerRClickListener listener = PlayerRClickListener.getInstance();
        if (listener.mode == "code") {
            listener.mode = "";
            listener.arg = "";
            sender.sendMessage("Code R-CLick interact disabled");
        } else {
            listener.mode = "code";
            listener.arg = args[0];
            sender.sendMessage("Code R-Click interact enabled");
        }
        
        return true;
    }
}
