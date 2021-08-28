package fun.zcraft.sgk.zhubbungee;

import fun.zcraft.sgk.zhubbungee.Commands.hubCommand;
import fun.zcraft.sgk.zhubbungee.Commands.reloadCommand;
import fun.zcraft.sgk.zhubbungee.Stats.Metrics;
import fun.zcraft.sgk.zhubbungee.Utils.ColorUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.*;
import java.nio.file.Files;


public final class ZHUBBungee extends Plugin {
    public static ZHUBBungee instance;
    public Configuration config;
    CommandSender sender;
    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerCommand(this, new reloadCommand());
        getProxy().getPluginManager().registerCommand(this, new hubCommand());
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("ZHUB-Bungee has enabled successfully.");
        int pluginId = 12613;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));
        getLogger().info("The main hub server is "+ getConfig().getString("Hub" , "Lobby"));
    }
    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("ZHUB-Bungee has disabled successfully.");
    }


    public void saveDefaultConfig() {
        File dir = getDataFolder();
        if (!dir.exists()) dir.mkdirs();
        File file = new File(dir,"config.yml");
        if(!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                 Files.copy(in,file.toPath());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadConfig() {
        File file = new File(getDataFolder(),"config.yml");
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveConfig() {
        File file = new File(getDataFolder(),"config.yml");
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config,file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public Configuration getConfig() {
        return config;
    }
}
