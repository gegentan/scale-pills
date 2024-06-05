package io.github.gegentan.scalepills.item;

import io.github.gegentan.scalepills.ScalePills;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item UPSCALE_PILL = registerItem("upscale_pill", new UpScalePill(new FabricItemSettings().maxDamage(250)));
    public static final Item DOWNSCALE_PILL = registerItem("downscale_pill", new DownScalePill(new FabricItemSettings().maxDamage(250)));
    public static final Item RESET_PILL = registerItem("reset_pill", new ResetPill(new FabricItemSettings().maxCount(1)));

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(UPSCALE_PILL))
            .displayName(Text.translatable("itemGroup.scale_pills.pills_group"))
            .entries((context, entries) -> {
                entries.add(RESET_PILL);

                entries.add(createScalePill(UPSCALE_PILL, 2));
                entries.add(createScalePill(UPSCALE_PILL, 4));
                entries.add(createScalePill(UPSCALE_PILL, 8));
                entries.add(createScalePill(UPSCALE_PILL, 16));
                entries.add(createScalePill(UPSCALE_PILL, 32));

                entries.add(createScalePill(DOWNSCALE_PILL, 2));
                entries.add(createScalePill(DOWNSCALE_PILL, 4));
                entries.add(createScalePill(DOWNSCALE_PILL, 8));
                entries.add(createScalePill(DOWNSCALE_PILL, 16));
                entries.add(createScalePill(DOWNSCALE_PILL, 32));

            })
            .build();

    private static ItemStack createScalePill(Item item, int size) {
        ItemStack itemStack = new ItemStack(item);
        itemStack.setSubNbt("size", NbtDouble.of(size));
        return itemStack;
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ScalePills.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(ScalePills.MOD_ID, "pills_group"), ITEM_GROUP);
    }
}
