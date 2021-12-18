package me.gagip;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(player.getName() + "님 안녕하세요.");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent deathEvent) {
        Player p = deathEvent.getEntity();
        Location deathLocation = p.getLocation();
        p.sendMessage(String.format("%s님이 죽으신 장소는 [%.0f, %.0f, %.0f] 입니다.",
                p.getName(), deathLocation.getX(), deathLocation.getY(), deathLocation.getZ()));
    }
}
