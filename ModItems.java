package com.cutieworld.item;
import com.cutieworld.CutieWorld;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
 public static final DeferredRegister<Item> ITEMS=DeferredRegister.create(ForgeRegistries.ITEMS,CutieWorld.MODID);
 public static final RegistryObject<Item> MOONBLOOM_PETAL=ITEMS.register("moonbloom_petal",
  ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(.4f).build())));
 public static final RegistryObject<Item> ACORN_CHARM=ITEMS.register("acorn_charm",
  ()->new Item(new Item.Properties().stacksTo(16)));
 public static final RegistryObject<Item> CLOUDBERRY=ITEMS.register("cloudberry",
  ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(.7f).build())));
}
