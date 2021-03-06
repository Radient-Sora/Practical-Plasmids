package sora.plasmids.registry;

import java.util.*;

import sora.plasmids.api.IPlasmid;
import sora.plasmids.exceptions.PlasmidRegistryException;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

/**
 * @author p455w0rd
 *
 * Plasmid Registry
 *
 * Used to register an item as a Plasmid
 *
 * TODO: UpcraftLP: transform into IForgeRegistry
 *
 */
public class PlasmidRegistry {

	private static Map<String, IPlasmid<Item>> REGISTRY = new HashMap<String, IPlasmid<Item>>();

	/**
	 * Registers an  {@link net.minecraft.item.Item Item} as a {@link IPlasmid Plasmid}<br>
	 * <ul><li>This should be called in {@link FMLPostInitializationEvent PostInit}</ul></li>
	 * @param plasmidID   A unique {@link java.lang.String String} ID for this {@link IPlasmid Plasmid} (usually MODID:ITEM_NAME)
	 * @param plasmid - The {@link net.minecraft.item.Item Item} being registered as a {@link IPlasmid Plasmid}
	 * @throws PlasmidRegistryException
	 * @return true on success
	 */
	public static boolean register(String plasmidID, IPlasmid<Item> plasmid) {
		if (REGISTRY.containsKey(plasmidID) || REGISTRY.containsValue(plasmid)) {
			throw new PlasmidRegistryException("Plasmid \"" + plasmidID + ":" + plasmid.toString() + "\" has already been registered!");
		}
		else {
			REGISTRY.put(plasmidID, plasmid);
			return true;
		}
	}

	public static Map<String, IPlasmid<Item>> getPlasmidList() {
		return REGISTRY;
	}

	public static Item getPlasmid(String plasmidID, IPlasmid<Item> plasmid) {
		if (!REGISTRY.containsKey(plasmidID) && !REGISTRY.containsValue(plasmid)) {
			throw new PlasmidRegistryException("Plasmid \"" + plasmidID + ":" + plasmid.toString() + "\" has already been registered!");
		}
		else {
			return (Item) REGISTRY.get(plasmidID);
		}
	}

}
