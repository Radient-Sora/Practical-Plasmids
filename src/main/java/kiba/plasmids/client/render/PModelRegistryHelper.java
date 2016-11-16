/*
 * This file is part of p455w0rd's Things.
 * Copyright (c) 2016, p455w0rd (aka TheRealp455w0rd), All rights reserved
 * unless
 * otherwise stated.
 *
 * p455w0rd's Things is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License.
 *
 * p455w0rd's Things is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * MIT License for more details.
 *
 * You should have received a copy of the MIT License
 * along with p455w0rd's Things. If not, see
 * <https://opensource.org/licenses/MIT>.
 */
package kiba.plasmids.client.render;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.render.item.IItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * @author p455w0rd
 *
 */
public class PModelRegistryHelper extends ModelRegistryHelper {

	public static void registerMetaRenderer(Item item, IItemRenderer renderer, int damage) {
		final ModelResourceLocation modelLoc = new ModelResourceLocation(Item.REGISTRY.getNameForObject(item) + "" + damage + damage, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, damage, modelLoc);
		register(modelLoc, renderer);
	}

}
