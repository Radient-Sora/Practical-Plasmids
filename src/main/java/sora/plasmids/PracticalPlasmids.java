package sora.plasmids;

import sora.plasmids.api.IEveHolder;
import sora.plasmids.energy.implementation.EveContainer;
import sora.plasmids.energy.implementation.EveContainerProvider;
import sora.plasmids.proxy.CommonProxy;
import sora.plasmids.registry.ConfigHandler;
import sora.plasmids.registry.RecipeRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Globals.MODID, version = Globals.VERSION, name = Globals.MODNAME)
public class PracticalPlasmids {

	public static final CreativeTabs TAB_PRACTICAL_PLASIMDS = new PracticalPlasmidsCreativeTab();

	@SidedProxy(clientSide = Globals.CLIENTPROXY, serverSide = Globals.COMMONPROXY)
	public static CommonProxy proxy;

	@SuppressWarnings({"unchecked", "rawtypes"})
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(IEveHolder.class, new PlasmidsCapabilities.CapabilityEveHolder(), EveContainer.class);
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipeRegistry.init();
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@Mod.EventBusSubscriber(modid = Globals.MODID)
	public static class Handler {

		@SubscribeEvent
		public static void caps(AttachCapabilitiesEvent event) {
			if (event.getObject() instanceof EntityPlayer) {
				event.addCapability(new ResourceLocation(Globals.MODID, "eve"), new EveContainerProvider(EveContainer.create(ConfigHandler.eveMaxLevel, Long.MAX_VALUE, Long.MAX_VALUE)));
			}
		}

		@SubscribeEvent
		public static void clone(PlayerEvent.Clone event) {
			if (event.isWasDeath()) {
				EntityPlayer dead = event.getOriginal();
				EntityPlayer alive = event.getEntityPlayer();

				if (dead.hasCapability(PlasmidsCapabilities.EVE_HOLDER, null) && alive.hasCapability(PlasmidsCapabilities.EVE_HOLDER, null)) alive.getCapability(PlasmidsCapabilities.EVE_HOLDER, null).deserializeNBT(dead.getCapability(PlasmidsCapabilities.EVE_HOLDER, null).serializeNBT());
			}
		}

	}

}
