package me.mical.signature.config;

import me.mical.signature.Signature;
import me.mical.signature.data.PlayerData;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.config.PFolder;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;
import org.serverct.parrot.parrotx.utils.FileUtil;

import java.io.File;

@PAutoload
public class DataManager extends PFolder<PlayerData> {

    public DataManager() {
        super(
                ParrotXAPI.getPlugin(Signature.class),
                "data",
                "玩家数据文件夹",
                "DATA"
        );
    }

    @Override
    public PlayerData loadFromDataFile(File dataFile) {
        return new PlayerData(buildId(FileUtil.getNoExFilename(dataFile)), dataFile);
    }
}
