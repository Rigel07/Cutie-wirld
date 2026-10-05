package com.cutieworld.entity;
import com.cutieworld.CutieWorld;
import net.minecraft.world.entity.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
 public static final DeferredRegister<EntityType<?>> ENTITIES=DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,CutieWorld.MODID);
 public static final RegistryObject<EntityType<MoonbloomEntity>> MOONBLOOM=ENTITIES.register("moonbloom",
  ()->EntityType.Builder.of(MoonbloomEntity::new,MobCategory.CREATURE).sized(.55f,.7f).build("cutieworld:moonbloom"));
 public static final RegistryObject<EntityType<NutkinEntity>> NUTKIN=ENTITIES.register("nutkin",
  ()->EntityType.Builder.of(NutkinEntity::new,MobCategory.CREATURE).sized(.5f,.65f).build("cutieworld:nutkin"));
}
