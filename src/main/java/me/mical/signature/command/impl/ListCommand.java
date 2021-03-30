package me.mical.signature.command.impl;

import me.mical.signature.config.DataManager;
import me.mical.signature.data.PlayerData;
import me.mical.signature.data.inventory.ListInventory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.InventoryUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

public class ListCommand extends BaseCommand {

    public ListCommand(@NotNull PPlugin plugin) {
        super(plugin, "list", 1);

        mustPlayer(true);
        describe("列出玩家的个签列表.");
        perm(".list");

        addParam(CommandParam.player(0, "填入玩家名.", warn("玩家不在线或是不存在.")));
    }

    @Override
    protected void call(String[] args) {
        final Player target = (Player) convert(0, args);
        final PlayerData data = ParrotXAPI.getConfigManager(DataManager.class).get(target.getUniqueId().toString());
        final ListInventory inventory = new ListInventory(user, target);
        assert data != null;
        if (target.getUniqueId().toString().equals(user.getUniqueId().toString()) || data.isShow()) {
            InventoryUtil.openInventory(plugin, user, inventory.getInventory());
        } else {
            I18n.send(user, warn("你无法查看 &c{0} &f的历史个签, 对方不想让他人查看.", target.getName()));
        }
    }
}
