package panda.glassworks.util;

import panda.glassworks.blocks.BlockTarFluid;
import panda.glassworks.blocks.TarFluid;
import panda.glassworks.init.GlassBlocks;
import panda.glassworks.init.GlassItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler
{
    
    @SubscribeEvent
    public void onRightClickHoldingBucket(FillBucketEvent event)
    {

        if (event.getEmptyBucket().getItem() != Items.BUCKET) {return;}
        if (event.getTarget() == null || event.getTarget().typeOfHit != RayTraceResult.Type.BLOCK) {return;}
        BlockPos blockpos = event.getTarget().getBlockPos();
        if (!event.getWorld().isBlockModifiable(event.getEntityPlayer(), blockpos)) {return;}
        if (!event.getEntityPlayer().canPlayerEdit(blockpos.offset(event.getTarget().sideHit), event.getTarget().sideHit, event.getEmptyBucket())) {return;}
        

        IBlockState iblockstate = event.getWorld().getBlockState(blockpos);
        Fluid filled_fluid = null;
        if (iblockstate.getBlock() == GlassBlocks.TAR && ((Integer)iblockstate.getValue(BlockTarFluid.LEVEL)).intValue() == 0)
        {
            filled_fluid = TarFluid.instance;
        }
        else
        {
            return;
        }
        
        // remove the fluid and return the appropriate filled bucket
        event.setResult(Result.ALLOW);
        //UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, filled_fluid)
        event.setFilledBucket(new ItemStack(GlassItems.TAR_BUCKET));
        event.getWorld().setBlockToAir(blockpos);
        //TODO: event.entityPlayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(event.getEmptyBucket().getItem())]);
    }
        
}