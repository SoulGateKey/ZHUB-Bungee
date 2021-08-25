package fun.zcraft.sgk.zhubbungee.Commands;


import fun.zcraft.sgk.zhubbungee.ZHUBBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import java.util.ArrayList;

public class reloadCommand extends Command implements TabExecutor {
    public reloadCommand() {
        super("zhub");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("zhub.admin")){
            ZHUBBungee.instance.reloadConfig();
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender , String[] args) {
        return new ArrayList<>();
    }
}
