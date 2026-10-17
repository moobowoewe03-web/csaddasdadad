package com.foodmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FoodMod implements ModInitializer {
    public static final String MOD_ID = "foodmod";

    public static Block FOOD_PLATE;
    public static Item FOOD_PLATE_ITEM;

    @Override
    public void onInitialize() {
        Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, "food_plate");

        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        FOOD_PLATE = Registry.register(BuiltInRegistries.BLOCK, blockKey,
                new FoodPlateBlock(BlockBehaviour.Properties.of()
                        .setId(blockKey)
                        .strength(0.5f)
                        .sound(SoundType.WOOD)
                        .noOcclusion()));

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
        FOOD_PLATE_ITEM = Registry.register(BuiltInRegistries.ITEM, itemKey,
                new BlockItem(FOOD_PLATE, new Item.Properties()
                        .setId(itemKey)
                        .useBlockDescriptionPrefix()));

        // 크리에이티브 "음식과 음료" 탭에 추가
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register(output -> output.accept(FOOD_PLATE_ITEM));
    }
}
