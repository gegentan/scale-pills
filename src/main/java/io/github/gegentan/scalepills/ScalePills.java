package io.github.gegentan.scalepills;

import io.github.gegentan.scalepills.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScalePills implements ModInitializer {
	public static final String MOD_ID = "scale_pills";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
