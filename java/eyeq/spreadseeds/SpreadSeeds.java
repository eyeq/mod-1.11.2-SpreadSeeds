package eyeq.spreadseeds;

import eyeq.spreadseeds.event.SpreadSeedsEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static eyeq.spreadseeds.SpreadSeeds.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class SpreadSeeds {
    public static final String MOD_ID = "eyeq_spreadseeds";

    @Mod.Instance(MOD_ID)
    public static SpreadSeeds instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SpreadSeedsEventHandler());
    }
}
