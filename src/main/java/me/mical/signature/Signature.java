package me.mical.signature;

import lombok.Getter;
import me.mical.signature.config.ConfigManager;
import me.mical.signature.expansion.SignatureExpansion;
import me.mical.signature.utils.StorageUtil;
import org.bukkit.Bukkit;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;
import org.serverct.parrot.parrotx.listener.InventoryListener;

public final class Signature extends PPlugin {

    @Getter
    private static VaultUtil vaultUtil;

    //public static boolean validate = ValidateUtil.validate();

    @Override
    protected void preload() {
        pConfig = ConfigManager.getInstance();
        //if (ValidateUtil.unValidate()) {
        //    lang.log.log(lang.data.error("验证失败, 你可能在使用未经许可的插件副本."));
        //}
    }

    @Override
    protected void load() {
        vaultUtil = new VaultUtil(this, true);
        Bukkit.getOnlinePlayers().forEach(StorageUtil::initPlayerData);
        lang.log.debug("已成功为所有在线玩家初始化玩家数据.");
        registerExpansion(new SignatureExpansion(this));
    }

    @Override
    protected void afterInit() {
        // 必须注册, 为容器监听器, 不注册将会出现很严重的后果.
        // 同时请放在 init() 方法之后注册, 要不然重启插件会再次注册, 可能会出问题.
        registerListener(new InventoryListener(this));
        //if (ValidateUtil.unValidate()) {
        //   Bukkit.getPluginManager().disablePlugin(this);
        //}
        registerStats(10859, null);
    }
}
