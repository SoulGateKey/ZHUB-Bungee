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
        if(args.length == 0) {
            sender.sendMessage((TextComponent.fromLegacyText("§4Insufficient parameters! Available list: reload")));
        }
        else {
            if(!sender.hasPermission("zhub.admin")){
                sender.sendMessage((TextComponent.fromLegacyText("§4You Do Not Have Permission to Do That!")));
            }
            if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("zhub.admin")){
                ZHUBBungee.instance.reloadConfig();
                String h = ZHUBBungee.instance.getConfig().getString("Hub");
                sender.sendMessage((TextComponent.fromLegacyText("ZHUB-Bungee config.yml has reloaded successfully.")));
                sender.sendMessage(TextComponent.fromLegacyText("The Hub now is " + h));

            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender , String[] args) {
        return new ArrayList<>();
    }
}
