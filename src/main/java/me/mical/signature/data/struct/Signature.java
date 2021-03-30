package me.mical.signature.data.struct;

import lombok.Getter;
import lombok.ToString;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.data.PID;
import org.serverct.parrot.parrotx.data.PStruct;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoloadGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@ToString
@PAutoloadGroup
public class Signature extends PStruct {

    @PAutoload("Content")
    private String content;
    @PAutoload("Timestamp")
    private long timestamp;
    @PAutoload("Likes")
    private Map<UUID, Long> likes;

    public Signature(PID id, ConfigurationSection section) {
        super(id, section, "个性签名");
        load();
    }

    public Signature(PID id, ConfigurationSection section, String content) {
        super(id, section, "个性签名");

        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.likes = new HashMap<>();

        save();
    }

    public void like(@NotNull UUID uuid) {
        this.likes.putIfAbsent(uuid, System.currentTimeMillis());
    }
}
