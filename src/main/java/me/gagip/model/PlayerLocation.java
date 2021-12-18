package me.gagip.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerLocation {

    private String uuid;
    private int x;
    private int y;
    private int z;

    public PlayerLocation(Player player, Location location) {
        this.uuid = player.getUniqueId().toString();
        this.x = (int) Math.round(location.getX());
        this.y = (int) Math.round(location.getY());
        this.z = (int) Math.round(location.getZ());
    }

    public String getUuid() {
        return uuid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
