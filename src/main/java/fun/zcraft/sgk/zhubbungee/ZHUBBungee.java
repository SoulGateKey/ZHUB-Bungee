package fun.zcraft.sgk.zhubbungee;

import fun.zcraft.sgk.zhubbungee.Commands.reloadCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.nio.file.Files;

public final class ZHUBBungee extends Plugin {
    public static ZHUBBungee instance;
    public Configuration config;

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerCommand(this,new reloadCommand());
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("ZHUB-Bungee has enabled successfully.");
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
        getLogger().info("ZHUB-Bungee has reloaded successfully.%n");
        getLogger().info("The Hub server is " + getConfig().getString("Hub"));
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
