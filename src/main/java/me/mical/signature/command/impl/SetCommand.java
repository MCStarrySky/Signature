package me.mical.signature.command.impl;

import me.mical.signature.Signature;
import me.mical.signature.config.ConfigManager;
import me.mical.signature.config.DataManager;
import me.mical.signature.data.PlayerData;
import me.mical.signature.utils.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class SetCommand extends BaseCommand {

    public SetCommand(@NotNull PPlugin plugin) {
        super(plugin, "set", 1);

        mustPlayer(true);
        describe("设置或修改你的个性签名.");
        perm(".set");

        addParam(CommandParam.builder()
                .name("个性签名")
                .description("你的个性签名.")
                .position(0)
                .build());
    }

    @Override
    protected void call(String[] args) {
        if (Signature.getVaultUtil().getBalances(user) >= ConfigManager.editCost) {
            final String content = ArrayUtil.toString(args, null);
            if (content.length() > ConfigManager.lengthLimit) {
                I18n.send(user, warn("你设置的个人签名超过了最大限制长度(&c{0}&f),无法设置.", ConfigManager.lengthLimit));
            } else {
                Signature.getVaultUtil().take(user, ConfigManager.editCost);
                final PlayerData data = ParrotXAPI.getConfigManager(DataManager.class).get(user.getUniqueId().toString());
                assert data != null;
                data.setCurrent(content);
                I18n.send(user, info("已成功更新你的个人签名."));
            }
        } else {
            I18n.send(user, warn("你没有足够的资金 &c" + ConfigManager.editCost + " &f来支付手续费, 你只有 &c" + Signature.getVaultUtil().getBalances(user)) + "&f.");
        }
    }
}
