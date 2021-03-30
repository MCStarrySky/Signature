package me.mical.signature.command.impl;

import me.mical.signature.config.DataManager;
import me.mical.signature.data.PlayerData;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class ShowCommand extends BaseCommand {

    public ShowCommand(@NotNull PPlugin plugin) {
        super(plugin, "show", 0);

        mustPlayer(true);
        describe("设置对外显示或隐藏历史个性签名.");
        perm(".show");
    }

    @Override
    protected void call(String[] args) {
        final PlayerData data = ParrotXAPI.getConfigManager(DataManager.class).get(user.getUniqueId().toString());
        assert data != null;
        if (data.isShow()) {
            data.setShow(false);
            I18n.send(user, info("设置成功. 现在别人不可以看到你的历史签名."));
        } else {
            data.setShow(true);
            I18n.send(user, info("设置成功, 现在别人可以看到你的历史签名了."));
        }
    }
}
