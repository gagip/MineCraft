package me.gagip;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public Logger logger = getServer().getLogger();

    @Override
    public void onEnable() {
        logger.info("gagip 플러그인 활성화");
        DBManager DBManager = new DBManager(getDataFolder().getAbsolutePath(), logger);
        EventManager eventManager = new EventManager();
        CommandManager commandManager = new CommandManager(DBManager);

        getServer().getPluginManager().registerEvents(eventManager, this);
        getCommand(CommandType.SAVE_LOCATION.value).setExecutor(commandManager);
        getCommand(CommandType.TELEPORT.value).setExecutor(commandManager);
    }

    @Override
    public void onDisable() {
        logger.info("gagip 플러그인 비활성화");
    }
}
