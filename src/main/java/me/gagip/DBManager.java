package me.gagip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.gagip.model.PlayerLocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.logging.Logger;

public class DBManager {

    private final String path;
    private final Logger logger;
    private final Gson gson;

    public DBManager(String path, Logger logger) {
        this.path = path + "/DB/";
        this.logger = logger;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        createDBDirectory();
    }

    /**
     * DB 폴더 생성
     */
    private void createDBDirectory() {
        File file = new File(path);
        if (!file.exists()) {
            boolean result = file.mkdirs();
            logger.info("DB 폴더 생성" + result);
        } else {
            logger.info("DB 폴더 이미 생성");
        }
    }

    public void saveLocation(PlayerLocation playerLocation, ResultCallback callback) {
        try {
            File file = new File(getJsonFile(playerLocation.getUuid()));
            Writer fw = new FileWriter(file);
            gson.toJson(playerLocation, fw);
            fw.flush();
            fw.close();
            callback.onSuccess();
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFail();
        }
    }

    public PlayerLocation loadLocation(String uuid) {
        PlayerLocation location = null;
        try {
            File file = new File(getJsonFile(uuid));
            Reader reader = new FileReader(file);
            location = gson.fromJson(reader, PlayerLocation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return location;
    }

    private String getJsonFile(String uuid) {
        return path + uuid + ".json";
    }

    public interface ResultCallback {
        void onSuccess();
        void onFail();
    }
}
