package com.cutieworld.worldgen;
import com.cutieworld.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid="cutieworld")
public class FunctionalWorkshop {
 private static final Set<Long> DONE=new HashSet<>();
 @SubscribeEvent public static void load(ChunkEvent.Load e){
  if(!(e.getLevel() instanceof ServerLevel l))return;
  int cx=e.getChunk().getPos().x,cz=e.getChunk().getPos().z;
  if(Math.floorMod(cx,9)!=0||Math.floorMod(cz,9)!=0)return;
  long key=(((long)cx)<<32)^(cz&0xffffffffL); if(!DONE.add(key))return;
  int x=e.getChunk().getPos().getMinBlockX()+4,z=e.getChunk().getPos().getMinBlockZ()+4;
  BlockPos h=l.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,new BlockPos(x,0,z));
  if(h.getY()<50||h.getY()>180)return;
  BlockPos b=new BlockPos(x,h.getY(),z);
  for(int dx=0;dx<7;dx++)for(int dz=0;dz<7;dz++){
   l.setBlock(b.offset(dx,0,dz),Blocks.SPRUCE_PLANKS.defaultBlockState(),3);
   if(dx==0||dx==6||dz==0||dz==6)for(int y=1;y<=3;y++)l.setBlock(b.offset(dx,y,dz),Blocks.SPRUCE_PLANKS.defaultBlockState(),3);
  }
  for(int dx=0;dx<7;dx++)for(int dz=0;dz<7;dz++)l.setBlock(b.offset(dx,4,dz),Blocks.SPRUCE_SLAB.defaultBlockState(),3);
  l.setBlock(b.offset(3,1,0),Blocks.CRAFTING_TABLE.defaultBlockState(),3);
  l.setBlock(b.offset(2,1,2),Blocks.CHEST.defaultBlockState(),3);
  l.setBlock(b.offset(4,1,2),Blocks.CAMPFIRE.defaultBlockState(),3);
  l.setBlock(b.offset(3,1,6),Blocks.OAK_DOOR.defaultBlockState(),3);
  var mob=ModEntities.NUTKIN.get().create(l);
  if(mob!=null){mob.moveTo(b.getX()+3.5,b.getY()+1,b.getZ()+3.5,0,0);mob.finalizeSpawn(l,l.getCurrentDifficultyAt(b),MobSpawnType.STRUCTURE,null,null);l.addFreshEntity(mob);}
 }
}
