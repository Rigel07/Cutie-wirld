package com.cutieworld.entity;
import com.cutieworld.item.ModItems;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.server.level.ServerLevel;

public class MoonbloomEntity extends Animal {
 public MoonbloomEntity(EntityType<? extends Animal> t,Level l){super(t,l);}
 public static AttributeSupplier.Builder attributes(){return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH,12).add(Attributes.MOVEMENT_SPEED,.22);}
 protected void registerGoals(){
  goalSelector.addGoal(1,new FloatGoal(this));
  goalSelector.addGoal(2,new TemptGoal(this,1.0,Items.SUGAR,false));
  goalSelector.addGoal(3,new PanicGoal(this,1.2));
  goalSelector.addGoal(4,new WaterAvoidingRandomStrollGoal(this,.8));
  goalSelector.addGoal(5,new LookAtPlayerGoal(this,Player.class,6));
 }
 public InteractionResult mobInteract(Player p,InteractionHand h){
  ItemStack s=p.getItemInHand(h);
  if(s.is(Items.SHEARS)){
   if(!level().isClientSide){spawnAtLocation(new ItemStack(ModItems.MOONBLOOM_PETAL.get(),1+random.nextInt(2)));s.hurtAndBreak(1,p,x->x.broadcastBreakEvent(h));}
   return InteractionResult.sidedSuccess(level().isClientSide);
  }
  return super.mobInteract(p,h);
 }
 public AgeableMob getBreedOffspring(ServerLevel l,AgeableMob o){return ModEntities.MOONBLOOM.get().create(l);}
}
