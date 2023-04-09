package com.khopan.mc.mod.blockplacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;

public class BlockPlacement implements ModInitializer {
	public static final String MOD_NAME = "Block Placement";
	public static final String MOD_ID = "blockplacement";

	public static final Logger LOGGER = LoggerFactory.getLogger(BlockPlacement.MOD_NAME);

	private static final BlockState DEFAULT_BLOCK = Blocks.STONE.getDefaultState();

	private BlockState blockState;

	@Override
	public void onInitialize() {
		BlockPlacement.LOGGER.info("Initializing Block Placement Mod (Server)");
		this.blockState = BlockPlacement.DEFAULT_BLOCK;

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("block").requires(source -> source.hasPermissionLevel(2)).executes(context -> {
				ServerCommandSource source = context.getSource();
				Entity entity = source.getEntity();

				if(entity == null) {
					throw new SimpleCommandExceptionType(Text.literal("Only entities can run this command")).create();
				}

				ServerWorld world = source.getWorld();

				if(this.blockState == null) {
					source.sendMessage(Text.literal("Internal Error: Field 'this.blockState' of class 'com.khopan.mc.mod.blockplacement.BlockPlacement' cannot be null").formatted(Formatting.RED));
					source.sendMessage(Text.literal("Set to default block"));
					this.blockState = BlockPlacement.DEFAULT_BLOCK;
				}

				BlockPos position = entity.getBlockPos();
				world.setBlockState(position, this.blockState);
				world.updateNeighbors(position, this.blockState.getBlock());
				return Command.SINGLE_SUCCESS;
			}).then(CommandManager.literal("reset").requires(source -> source.hasPermissionLevel(2)).executes(context -> {
				this.blockState = BlockPlacement.DEFAULT_BLOCK;
				context.getSource().sendMessage(Text.literal("Successfully reset to default!"));
				return Command.SINGLE_SUCCESS;
			})).then(CommandManager.literal("set").then(CommandManager.argument("block", BlockStateArgumentType.blockState(registryAccess)).requires(source -> source.hasPermissionLevel(2)).executes(context -> {
				this.blockState = BlockStateArgumentType.getBlockState(context, "block").getBlockState();
				context.getSource().sendMessage(Text.literal("Successfully changed to '" + this.blockState.getBlock().getName().getString() + "'!"));
				return Command.SINGLE_SUCCESS;
			}))));
		});
	}
}
