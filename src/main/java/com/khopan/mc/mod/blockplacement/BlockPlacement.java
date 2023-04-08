package com.khopan.mc.mod.blockplacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class BlockPlacement implements ModInitializer {
	public static final String MOD_NAME = "Block Placement";
	public static final String MOD_ID = "blockplacement";

	public static final Logger LOGGER = LoggerFactory.getLogger(BlockPlacement.MOD_NAME);

	@Override
	public void onInitialize() {
		BlockPlacement.LOGGER.info("Initializing Block Placement Mod (Server)");
	}
}
