package com.bewitchment.common.divination.fortunes;

import com.bewitchment.common.core.capability.divination.CapabilityDivination;
import com.bewitchment.common.divination.Fortune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FortuneDropItem extends Fortune {

	public FortuneDropItem(int weight, String name, String modid) {
		super(weight, name, modid);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public boolean canBeUsedFor(EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canShouldBeAppliedNow(EntityPlayer player) {
		return player.getRNG().nextDouble() < 0.0005d;
	}

	@Override
	public boolean apply(EntityPlayer player) {
		player.getCapability(CapabilityDivination.CAPABILITY, null).setActive();
		return false;
	}

	@SubscribeEvent
	public void onItemUsed(RightClickItem evt) {
		if (evt.getEntityPlayer() != null) { // Needs to check for mainhand due to how the event works
			CapabilityDivination cap = evt.getEntityPlayer().getCapability(CapabilityDivination.CAPABILITY, null);
			if (cap.getFortune() == this && cap.isActive()) {
				if (evt.getEntityPlayer().dropItem(true) != null) {
					cap.setRemovable();
				}
			}
		}
	}


	@Override
	public boolean isNegative() {
		return true;
	}
}
