package com.github.creoii.creolib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;

/**
 * A utility class to interact with the Minecraft Client
 * Only to be used client side
 */
@Environment(EnvType.CLIENT)
public final class ClientUtil {
    @Nullable
    public static String windowTitle = null;

    public void setWindowTitle(String windowTitle) {
        ClientUtil.windowTitle = windowTitle;
    }
}
