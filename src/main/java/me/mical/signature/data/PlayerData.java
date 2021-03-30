package me.mical.signature.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.mical.signature.data.struct.Signature;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.serverct.parrot.parrotx.data.PData;
import org.serverct.parrot.parrotx.data.PID;
import org.serverct.parrot.parrotx.data.PStruct;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoload;
import org.serverct.parrot.parrotx.data.autoload.annotations.PAutoloadGroup;
import org.serverct.parrot.parrotx.utils.MapUtil;

import java.io.File;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

@Getter
@ToString
@PAutoloadGroup
public class PlayerData extends PData {

    private Map<String, Signature> histories;

    @PAutoload("Current")
    private String current;
    @PAutoload("History")
    private Map<String, ConfigurationSection> rawHistories;
    @PAutoload("Show")
    @Setter
    private boolean show;

    public PlayerData(PID id, File file) {
        super(id, file, "玩家数据");
        load();
    }

    @Override
    public void load() {
        if (data.getConfigurationSection("History") == null) {
            data.createSection("History");
        }
        super.load();

        final Map<String, Signature> signatures = MapUtil.transformValue(this.rawHistories,
                section -> new Signature(new PID(plugin, "signature", section.getName()), section));
        this.histories = MapUtil.sort(signatures, Comparator.comparingLong(entry -> entry.getValue().getTimestamp()),
                true);
    }

    @Nullable
    public Signature getCurrent() {
        if (StringUtils.isEmpty(this.current)) {
            return null;
        }
        return this.histories.get(this.current);
    }

    @Override
    public void save() {
        histories.values().forEach(PStruct::save);
        super.save();
    }

    public void setCurrent(@NotNull final String signature) {
        final UUID randomUUID = UUID.randomUUID();

        data.createSection("History." + randomUUID.toString());

        final ConfigurationSection section = data.getConfigurationSection("History." + randomUUID.toString());

        histories.put(randomUUID.toString(), new Signature(
                new PID(plugin, "signature", randomUUID.toString()),
                section,
                signature
        ));

        rawHistories.put(randomUUID.toString(), section);

        this.current = randomUUID.toString();
    }
}
