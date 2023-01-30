package com.github.creoii.creolib;

import com.github.creoii.creolib.registry.AttributeRegistry;
import com.github.creoii.creolib.registry.StructurePlacementTypeRegistry;
import com.github.creoii.creolib.world.ServerWorldTicker;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;

public class CreoLib implements ModInitializer {
    public static final String NAMESPACE = "creo_lib";
    public static final String COMMON_NAMESPACE = "c";

    @Override
    public void onInitialize() {
        AttributeRegistry.register();
        StructurePlacementTypeRegistry.register();

        ServerWorldTicker.TICKERS.add(world -> {
            world.getPlayers().forEach(player -> {
                player.sendMessage(Text.literal("tick test"));
            });
        });
    }
}
