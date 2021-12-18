package me.gagip;

import me.gagip.model.PlayerLocation;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    private final DBManager dbManager;

    public CommandManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        final Player player = (Player) commandSender;

        switch (CommandType.getType(command.getLabel())) {
            case SAVE_LOCATION:
                respawn(player);
                return true;
            case TELEPORT:
                PlayerLocation playerLocation = dbManager.loadLocation(player.getUniqueId().toString());

                if (playerLocation != null) {
                    player.teleport(new Location(player.getWorld(),playerLocation.getX(),
                            playerLocation.getY(), playerLocation.getZ()));
                } else {
                    player.sendMessage("텔레포트를 실패하였습니다. 먼저 '리스폰' 명령어를 실행한 이후 해당 명령어를 사용해보세요.");
                }

                return true;
            default:
                return false;
        }
    }

    private void respawn(final Player player) {
        dbManager.saveLocation(new PlayerLocation(player, player.getLocation()),
            new DBManager.ResultCallback() {
                @Override
                public void onSuccess() {
                    player.sendMessage("좌표를 성공적으로 저장하였습니다.");
                }

                @Override
                public void onFail() {
                    player.sendMessage("좌표를 저장하는데 실패하였습니다.");
                }
            });
    }
}
