package fun.zcraft.sgk.zhubbungee.Commands;

import fun.zcraft.sgk.zhubbungee.ZHUBBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class hubCommand extends Command {

    public hubCommand(){
        super("hub","zhub.player","lobby");
    }


    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(!player.getServer().getInfo().getName().equalsIgnoreCase(ZHUBBungee.instance.getConfig().getString("Hub"))){

                ServerInfo target = ProxyServer.getInstance().getServerInfo(ZHUBBungee.instance.getConfig().getString("Hub"));
                player.connect(target);
            }else{
                player.sendMessage(new ComponentBuilder("You are already connected to the Lobby!").color(ChatColor.RED).create());
            }
        }else{
            sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
        }
    }
}