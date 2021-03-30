package me.mical.signature.data.inventory;

import me.mical.signature.Signature;
import me.mical.signature.utils.StorageUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.serverct.parrot.parrotx.api.ParrotXAPI;
import org.serverct.parrot.parrotx.data.inventory.FileDefinedInventory;
import org.serverct.parrot.parrotx.data.inventory.PInventory;
import org.serverct.parrot.parrotx.data.inventory.element.InventoryButton;
import org.serverct.parrot.parrotx.data.inventory.element.InventoryTemplate;
import org.serverct.parrot.parrotx.utils.JsonChatUtil;
import org.serverct.parrot.parrotx.utils.TimeUtil;
import org.serverct.parrot.parrotx.utils.i18n.I18n;

import java.util.*;
import java.util.List;

public class ListInventory extends PInventory<List<me.mical.signature.data.struct.Signature>> {

    public ListInventory(@NotNull final Player viewer, @NotNull final Player target) {
        super(
                ParrotXAPI.getPlugin(Signature.class),
                StorageUtil.getAllData(target.getUniqueId().toString()),
                viewer,
                ParrotXAPI.getPlugin(Signature.class).getFileWithResource("Gui.yml")
        );

        final FileDefinedInventory base = (FileDefinedInventory) getBase();

        base.addSetting("title", I18n.color(base.getData().getString("Settings.Title"), target.getName()));

        for (String key : base.getItemMap().keySet()) {
            switch (key) {
                case "Template":
                    addElement(new InventoryTemplate.InventoryTemplateBuilder<me.mical.signature.data.struct.Signature>()
                            .base(InventoryButton.builder()
                                    .base(base.get(key, 1))
                                    .onClick(inventoryClickEvent -> {
                                        inventoryClickEvent.setCancelled(true);
                                        if (inventoryClickEvent.isLeftClick()) {
                                            final me.mical.signature.data.struct.Signature data = (me.mical.signature.data.struct.Signature) InventoryTemplate.get(this, key).getContent(inventoryClickEvent.getSlot());
                                            if (!data.getLikes().containsKey(viewer.getUniqueId())) {
                                                data.like(viewer.getUniqueId());
                                                refresh(getInventory());
                                                final String message = getPlugin().getLang().data.info("玩家 &c{0} &f赞了你的个签. (&c点击查看你的个签&f)", viewer.getName());
                                                final @NotNull TextComponent component = JsonChatUtil.getFromLegacy(message);
                                                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/signature list " + target.getName()));
                                                target.spigot().sendMessage(component);
                                            }
                                        }
                                    })
                                    .build())
                            .applyTemple((itemStack, signatureData) -> {
                                final List<DyeColor> colors = new ArrayList<>(Arrays.asList(DyeColor.values()));
                                final String color = colors.get(new Random().nextInt(colors.size())).name();
                                final ItemStack item = apply(itemStack, signatureData, base);
                                if (item.getType().name().contains("BED")) {
                                    item.setType(Material.valueOf(color + "_BED"));
                                }
                                return item;
                            })
                            .contents(StorageUtil.getAllData(target.getUniqueId().toString()))
                            .build());
                    break;
                case "Next":
                    addElement(InventoryButton.builder()
                            .base(base.get(key, 2, user -> InventoryTemplate.get(this, "Template").getMaxPage() > 1))
                            .onClick(event -> InventoryTemplate.get(this, "Template").nextPage(this))
                            .build());
                    break;
                case "Previous":
                    addElement(InventoryButton.builder()
                            .base(base.get(key, 2, user -> InventoryTemplate.get(this, "Template").getMaxPage() > 1))
                            .onClick(event -> InventoryTemplate.get(this, "Template").previousPage(this))
                            .build());
                    break;
                default:
                    addElement(base.get(key, 0, user -> true));
                    break;
            }
        }
    }

    public ItemStack apply(@NotNull final ItemStack itemStack0, @NotNull final me.mical.signature.data.struct.Signature signatureData, @NotNull final FileDefinedInventory base) {
        final ItemStack itemStack = itemStack0.clone();
        final ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        final List<String> lore = meta.getLore();

        assert lore != null;
        lore.replaceAll(s -> I18n.color(s.replace("{0}", TimeUtil.getDefaultFormatDate(new Date(signatureData.getTimestamp()))).replace("{1}", signatureData.getContent())));

        if (!signatureData.getLikes().isEmpty()) {
            lore.add(I18n.color(base.getItemMap().get("Template").getString("LikeFirst")));
            signatureData.getLikes().forEach((uuid, aLong) -> {
                final String result = base.getItemMap().get("Template").getString("Like");
                lore.add(I18n.color(result, Bukkit.getOfflinePlayer(uuid).getName(), TimeUtil.getDefaultFormatDate(new Date(signatureData.getTimestamp()))));
            });
            lore.add(I18n.color(base.getItemMap().get("Template").getString("LikeEnd")));
        }

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
