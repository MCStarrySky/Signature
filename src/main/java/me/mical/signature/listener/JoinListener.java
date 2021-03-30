package me.mical.signature.listener;

import me.mical.signature.utils.StorageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;

@PAutoload
public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        StorageUtil.initPlayerData(player);
    }
}
