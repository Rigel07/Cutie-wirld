package com.cutieworld;
import com.cutieworld.entity.ModEntities;
import com.cutieworld.entity.MoonbloomEntity;
import com.cutieworld.entity.NutkinEntity;
import com.cutieworld.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CutieWorld.MODID)
public class CutieWorld {
 public static final String MODID="cutieworld";
 public CutieWorld(){
  IEventBus b=FMLJavaModLoadingContext.get().getModEventBus();
  ModItems.ITEMS.register(b); ModEntities.ENTITIES.register(b);
  b.addListener(this::attributes); MinecraftForge.EVENT_BUS.register(this);
 }
 private void attributes(EntityAttributeCreationEvent e){
  e.put(ModEntities.MOONBLOOM.get(),MoonbloomEntity.attributes().build());
  e.put(ModEntities.NUTKIN.get(),NutkinEntity.attributes().build());
 }
}
