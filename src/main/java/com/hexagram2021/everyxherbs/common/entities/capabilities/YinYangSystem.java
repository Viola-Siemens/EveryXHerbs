package com.hexagram2021.everyxherbs.common.entities.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class YinYangSystem implements IYinYangSystem, INBTSerializable<Tag> {
	public double temperature;
	public double wet;

	@Override
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putDouble("Temperature", this.temperature);
		nbt.putDouble("Wet", this.wet);
		return nbt;
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		if(nbt instanceof CompoundTag compoundTag) {
			this.temperature = compoundTag.getDouble("Temperature");
			this.wet = compoundTag.getDouble("Wet");
		}
	}

	@Override
	public double getTemperature() {
		return this.temperature;
	}

	@Override
	public double getWet() {
		return this.wet;
	}

	@Override
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public void setWet(double wet) {
		this.wet = wet;
	}
}
