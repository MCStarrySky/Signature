package me.mical.signature.expansion;

import me.mical.signature.config.DataManager;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.hooks.BaseExpansion;

import java.util.Objects;

public class SignatureExpansion extends BaseExpansion {

    public SignatureExpansion(PPlugin plugin) {
        super(plugin);

        addParam(PlaceholderParam.builder()
                .name("signature")
                .parse((offlinePlayer, strings) ->
                        Objects.requireNonNull(ParrotXAPI.getConfigManager(DataManager.class).get(offlinePlayer.getUniqueId().toString())).getCurrent().getContent()
                ).build());
    }
}
