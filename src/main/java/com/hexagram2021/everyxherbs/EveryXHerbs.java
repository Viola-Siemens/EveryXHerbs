package com.hexagram2021.everyxherbs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(EveryXHerbs.MODID)
public class EveryXHerbs {
	public static final String MODID = "everyxherbs";

	public EveryXHerbs() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
