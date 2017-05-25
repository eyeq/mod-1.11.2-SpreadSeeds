package eyeq.spreadseeds.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpreadSeedsEventHandler {
    @SubscribeEvent
    public void onPlayerInteractBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        if(world.isRemote) {
            return;
        }
        EntityPlayer player = event.getEntityPlayer();
        if(!player.isSneaking()) {
            return;
        }
        ItemStack itemStack = event.getItemStack();
        Item item = itemStack.getItem();
        if(!(item instanceof IPlantable)) {
            return;
        }
        BlockPos pos = event.getPos();
        EnumHand hand = event.getHand();
        EnumFacing facing = event.getFace();
        Vec3d hitVec = event.getHitVec();
        if(player.isCreative()) {
            player.setHeldItem(hand, new ItemStack(item, 64, itemStack.getMetadata()));
        }
        for(int x = -1; x < 2; x++) {
            for(int z = -1; z < 2; z++) {
                if(itemStack.isEmpty()) {
                    return;
                }
                if(x != 0 || z != 0) {
                    item.onItemUse(player, world, pos.add(x, 0, z), hand, facing, (float) hitVec.xCoord, (float) hitVec.yCoord, (float) hitVec.zCoord);
                }
            }
        }
        if(player.isCreative()) {
            player.setHeldItem(hand, itemStack);
        }
    }
}
