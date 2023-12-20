package com.hexagram2021.everyxherbs.common.register;

import com.hexagram2021.everyxherbs.common.entities.capabilities.IYinYangSystem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import static com.hexagram2021.everyxherbs.EveryXHerbs.MODID;

public final class HerbsModCapabilities {
	public static final ResourceLocation ID_YIN_YANG_SYSTEM = new ResourceLocation(MODID, "yin_yang");
	public static final Capability<IYinYangSystem> YIN_YANG_SYSTEM = CapabilityManager.get(new CapabilityToken<>(){});

	private HerbsModCapabilities() {
	}
}
