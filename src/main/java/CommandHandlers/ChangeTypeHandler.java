package CommandHandlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import Commands.ChangeTypeCommand;
import Commands.CommandPattern;

public class ChangeTypeHandler implements CommandExecutor {
    private CommandPattern command;

    public void setCommand(CommandPattern cmd) {
        this.command = cmd;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 1){
            return false;
        }

        ((ChangeTypeCommand) this.command).setType(args[0]);
        this.command.execute();

        return true;
    }   
}
