package com.hexagram2021.everyxherbs.common.register;

import com.hexagram2021.everyxherbs.common.blocks.AngelicaBlock;
import com.hexagram2021.everyxherbs.common.blocks.GingerBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.hexagram2021.everyxherbs.EveryXHerbs.MODID;

public final class HerbsModBlocks {
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

	public static final class Crops {
		public static final Supplier<BlockBehaviour.Properties> CROP_PROPERTIES = () ->
				BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks()
						.instabreak().sound(SoundType.CROP);

		public static final BlockEntry<GingerBlock> GINGER = new BlockEntry<>("ginger", CROP_PROPERTIES, GingerBlock::new, HerbsModBlocks::toItem);
		public static final BlockEntry<AngelicaBlock> ANGELICA = new BlockEntry<>("angelica", CROP_PROPERTIES, AngelicaBlock::new, HerbsModBlocks::toNamedItem, Crops::toSeedName);


		private Crops() {
		}

		public static void init() {
		}

		private static String toSeedName(String name) {
			return name + "_seed";
		}
	}

	private static <T extends Block> BlockItem toItem(T block) {
		return new BlockItem(block, new Item.Properties());
	}

	private static <T extends Block> ItemNameBlockItem toNamedItem(T block) {
		return new ItemNameBlockItem(block, new Item.Properties());
	}

	public static final class BlockEntry<T extends Block> implements Supplier<T>, ItemLike {
		private final RegistryObject<T> regObject;
		private final Supplier<BlockBehaviour.Properties> properties;

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make) {
			this.properties = properties;
			this.regObject = REGISTER.register(name, () -> make.apply(properties.get()));
		}

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make, Function<T, Item> toItem) {
			this(name, properties, make);
			HerbsModItems.ItemEntry.register(name, () -> toItem.apply(this.regObject.get()));
		}

		public BlockEntry(String name, Supplier<BlockBehaviour.Properties> properties, Function<BlockBehaviour.Properties, T> make, Function<T, Item> toItem, Function<String, String> toItemName) {
			this(name, properties, make);
			HerbsModItems.ItemEntry.register(toItemName.apply(name), () -> toItem.apply(this.regObject.get()));
		}

		@Override
		public T get() {
			return this.regObject.get();
		}

		public BlockState defaultBlockState() {
			return this.get().defaultBlockState();
		}

		public ResourceLocation getId() {
			return this.regObject.getId();
		}

		public BlockBehaviour.Properties getProperties() {
			return this.properties.get();
		}

		@Override
		public Item asItem() {
			return this.get().asItem();
		}
	}

	private HerbsModBlocks() {
	}

	public static void init(IEventBus bus) {
		REGISTER.register(bus);
	}
}
