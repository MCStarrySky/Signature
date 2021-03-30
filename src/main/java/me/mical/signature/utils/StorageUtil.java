package me.mical.signature.utils;

import me.mical.signature.config.ConfigManager;
import me.mical.signature.config.DataManager;
import me.mical.signature.data.PlayerData;
import me.mical.signature.data.struct.Signature;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.api.ParrotXAPI;

import java.io.File;
import java.util.*;

public class StorageUtil {

    public static List<Signature> getAllData(@NotNull final String uuid) {
        return new ArrayList<>(ParrotXAPI.getConfigManager(DataManager.class).get(uuid).getHistories().values());
    }

    public static void initPlayerData(@NotNull final Player player) {
        if (!ParrotXAPI.getConfigManager(DataManager.class).has(player.getUniqueId().toString())) {
            final PlayerData playerData = new PlayerData(
                    ParrotXAPI.getConfigManager(DataManager.class).buildId(player.getUniqueId().toString()),
                    new File(ParrotXAPI.getConfigManager(DataManager.class).getFile(), player.getUniqueId().toString() + ".yml")
            );
            playerData.setCurrent(ConfigManager.defaultSignature);
            playerData.setShow(true);
            ParrotXAPI.getConfigManager(DataManager.class).put(playerData);
        }
    }
}
