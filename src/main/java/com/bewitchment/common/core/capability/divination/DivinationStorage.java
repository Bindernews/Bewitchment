package com.bewitchment.common.core.capability.divination;

import com.bewitchment.common.divination.Fortune;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DivinationStorage implements IStorage<CapabilityDivination> {
	@Override
	public NBTBase writeNBT(Capability<CapabilityDivination> capability, CapabilityDivination instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		if (instance.getFortune() != null) {
			tag.setString("fortune", instance.getFortune().getRegistryName().toString());
			tag.setBoolean("active", instance.isActive());
			tag.setBoolean("removable", instance.isRemovable());
		}
		return tag;
	}

	@Override
	public void readNBT(Capability<CapabilityDivination> capability, CapabilityDivination instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound) nbt;
		if (tag.hasKey("fortune")) {
			instance.setFortune(Fortune.REGISTRY.getValue(new ResourceLocation(tag.getString("fortune"))));
			if (tag.getBoolean("active"))
				instance.setActive();
			if (tag.getBoolean("removable"))
				instance.setRemovable();
		}
	}
}
