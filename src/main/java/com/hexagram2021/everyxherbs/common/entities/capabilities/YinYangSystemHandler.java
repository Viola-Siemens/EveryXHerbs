package com.hexagram2021.everyxherbs.common.entities.capabilities;

import com.hexagram2021.everyxherbs.common.register.HerbsModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class YinYangSystemHandler implements ICapabilityProvider, INBTSerializable<Tag> {
	private final YinYangSystem yinYangSystem;
	private final LazyOptional<IYinYangSystem> holder;

	public YinYangSystemHandler() {
		this.yinYangSystem = new YinYangSystem();
		this.holder = LazyOptional.of(() -> this.yinYangSystem);
	}

	@Override @NotNull
	public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
		return HerbsModCapabilities.YIN_YANG_SYSTEM.orEmpty(cap, this.holder);
	}

	@Override
	public Tag serializeNBT() {
		return this.yinYangSystem.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		this.yinYangSystem.deserializeNBT(nbt);
	}
}
