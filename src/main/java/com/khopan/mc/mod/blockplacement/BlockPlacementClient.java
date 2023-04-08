package com.khopan.mc.mod.blockplacement;

import net.fabricmc.api.ClientModInitializer;

public class BlockPlacementClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockPlacement.LOGGER.info("Initializing Block Placement Mod (Client)");
	}
}
