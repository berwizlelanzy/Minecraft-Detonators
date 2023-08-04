package CommandHandlers;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandChecker {
    public boolean check(CommandSender sender, String label) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(label + " can only be used by players.");
            return false;
        }

        Player player = (Player) sender;
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage(label + " can only be used in creative mode.");
            return false;
        }

        return true;
    }
}
