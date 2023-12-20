package com.hexagram2021.everyxherbs.common;

import com.hexagram2021.everyxherbs.common.entities.capabilities.YinYangSystemHandler;
import com.hexagram2021.everyxherbs.common.register.HerbsModCapabilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.hexagram2021.everyxherbs.EveryXHerbs.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
	@SubscribeEvent
	public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
		LivingEntity entity = event.getEntity();
		Level level = entity.level();
		if (level instanceof ServerLevel serverLevel) {
			ItemStack food = event.getItem();
			//TODO
		}
	}

	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingTickEvent event) {
		LivingEntity entity = event.getEntity();
		entity.getCapability(HerbsModCapabilities.YIN_YANG_SYSTEM).ifPresent(c -> c.tick(entity));
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {

	}

	@SubscribeEvent
	public static void onAttachLivingCapability(AttachCapabilitiesEvent<Entity> event) {
		if(event.getObject() instanceof Player) {
			event.addCapability(HerbsModCapabilities.ID_YIN_YANG_SYSTEM, new YinYangSystemHandler());
		}
	}
}
