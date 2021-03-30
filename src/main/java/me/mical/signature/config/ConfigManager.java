package me.mical.signature.config;

import me.mical.signature.Signature;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoloadGroup;

import java.util.Objects;

@PAutoloadGroup
public class ConfigManager extends PConfig {

    private static ConfigManager instance;

    public ConfigManager() {
        super(ParrotXAPI.getPlugin(Signature.class), "settings", "主配置文件");
    }

    @PAutoload("EditCost")
    public static double editCost;
    @PAutoload("DefaultSignature")
    public static String defaultSignature;
    @PAutoload("LengthLimit")
    public static int lengthLimit;

    public static ConfigManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
