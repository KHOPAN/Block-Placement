package com.khopan.mc.mod.blockplacement;

import net.fabricmc.api.ClientModInitializer;

public class BlockPlacementClient implements ClientModInitializer {
	/*private static final int SYNC_COOLDOWN = 2;
	private static final String PROPERTIES_FILE = "block-placement.properties";
	private static final String DEFAULT_BLOCK = "minecraft:stone";
	static String Block = null;
	private static Properties Properties = null;
	private static File ConfigFile = null;

	static {
		File configDirectory = FabricLoader.getInstance().getConfigDir().toFile();

		if(!configDirectory.exists() && !configDirectory.mkdir()) {
			BlockPlacement.LOGGER.warn("Could not create configuration directory '" + configDirectory.getAbsolutePath() + "'");
		}

		BlockPlacementClient.ConfigFile = new File(configDirectory, BlockPlacementClient.PROPERTIES_FILE);
		BlockPlacementClient.Properties = new Properties();

		if(BlockPlacementClient.ConfigFile.exists()) {
			try {
				FileInputStream stream = new FileInputStream(BlockPlacementClient.ConfigFile);
				BlockPlacementClient.Properties.load(stream);
				stream.close();
			} catch(Throwable Errors) {
				BlockPlacement.LOGGER.warn("Could not read '" + BlockPlacementClient.PROPERTIES_FILE + "' file");
			}
		}

		BlockPlacementClient.Block = BlockPlacementClient.Properties.computeIfAbsent("block", key -> BlockPlacementClient.DEFAULT_BLOCK).toString();
		BlockPlacementClient.sync();
	}*/

	//private int lastSync;

	@Override
	public void onInitializeClient() {
		BlockPlacement.LOGGER.info("Initializing Block Placement Mod (Client)");
		/*ServerWorldEvents.UNLOAD.register((server, world) -> {
			int seconds = Calendar.getInstance().get(Calendar.SECOND);

			if(this.lastSync == 0 || seconds - this.lastSync > BlockPlacementClient.SYNC_COOLDOWN) {
				BlockPlacementClient.sync();
				this.lastSync = seconds;
			}			
		});*/
	}

	/*private static void sync() {
		try {
			FileOutputStream stream = new FileOutputStream(BlockPlacementClient.ConfigFile);
			BlockPlacementClient.Properties.store(stream, "Block Placement - Properties File");
			stream.close();
		} catch(Throwable Errors) {
			BlockPlacement.LOGGER.warn("Could not save '" + BlockPlacementClient.PROPERTIES_FILE + "' file");
		}
	}*/
}
