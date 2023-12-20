package com.hexagram2021.everyxherbs.common.entities.capabilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IYinYangSystem {
	double TEMPERATURE_SCALE = Math.sqrt(4.0D + 2.0D * Math.sqrt(2.0D));
	double BOUND = 125;

	//[-125, 125]
	//[-125, -75)  [-75, -60) [-60, 60] (60, 75] (75, 120]
	double getTemperature();
	double getWet();
	void setTemperature(double temperature);
	void setWet(double wet);

	private static double sec(double x) {
		return 1.0D / Math.cos(x);
	}
	private static double getAdder(double x, double temperature) {
		return -x * (sec(temperature * 3.0D * Math.PI / 1000.0D) - TEMPERATURE_SCALE) / 2.0D;
	}

	default void addTemperature(double adder) {
		double temperature = this.getTemperature();
		double toAdd = getAdder(adder, temperature + adder);
		if(Double.isNaN(toAdd) || Double.isInfinite(toAdd) || toAdd * adder < 0) {
			this.setTemperature(Math.signum(adder) * BOUND);
		}
	}
	default void addWet(double adder) {
		double wet = this.getWet();
		double toAdd = getAdder(adder, wet + adder);
		if(Double.isNaN(toAdd) || Double.isInfinite(toAdd) || toAdd * adder < 0) {
			this.setWet(Math.signum(adder) * BOUND);
		}
	}

	default void tick(LivingEntity entity) {
		if(entity.isInPowderSnow) {
			this.addTemperature(-0.5D);
		}
		if(entity.isInWaterOrRain()) {
			if(entity.isInWater()) {

			} else {

			}
		}
	}
}
