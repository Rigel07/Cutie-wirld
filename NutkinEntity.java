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

public class NutkinEntity extends Animal {
 public NutkinEntity(EntityType<? extends Animal> t,Level l){super(t,l);}
 public static AttributeSupplier.Builder attributes(){return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH,10).add(Attributes.MOVEMENT_SPEED,.28);}
 protected void registerGoals(){
  goalSelector.addGoal(1,new FloatGoal(this));
  goalSelector.addGoal(2,new TemptGoal(this,.9,Items.WHEAT_SEEDS,false));
  goalSelector.addGoal(3,new PanicGoal(this,1.3));
  goalSelector.addGoal(4,new WaterAvoidingRandomStrollGoal(this,.9));
  goalSelector.addGoal(5,new LookAtPlayerGoal(this,Player.class,6));
 }
 public InteractionResult mobInteract(Player p,InteractionHand h){
  ItemStack s=p.getItemInHand(h);
  if(s.is(Items.ACACIA_SAPLING)){
   if(!level().isClientSide){s.shrink(1);p.getCooldowns().addCooldown(s.getItem(),40);p.give(new ItemStack(ModItems.ACORN_CHARM.get()));}
   return InteractionResult.sidedSuccess(level().isClientSide);
  }
  return super.mobInteract(p,h);
 }
 public AgeableMob getBreedOffspring(ServerLevel l,AgeableMob o){return ModEntities.NUTKIN.get().create(l);}
}
