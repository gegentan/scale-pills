package io.github.gegentan.scalepills.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import virtuoel.pehkui.api.ScaleTypes;

import java.util.List;
import java.util.function.Consumer;

public class DownScalePill extends Item {
    public DownScalePill(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            if (stack.getNbt().contains("size")) {
                tooltip.add(Text.literal(stack.getNbt().getInt("size")+"x").setStyle(Style.EMPTY.withColor(Formatting.AQUA)));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            ItemStack stack = user.getStackInHand(hand);

            if (stack.hasNbt()) {
                if (stack.getNbt().contains("size")) {
                    double size = 1.0/stack.getNbt().getInt("size");

                    if ((float) size != ScaleTypes.BASE.getScaleData(user).getScale()) {
                        ScaleTypes.BASE.getScaleData(user).setScale((float) size);
                        stack.damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                    }
                }
            }
        }

        return super.use(world, user, hand);
    }
}
