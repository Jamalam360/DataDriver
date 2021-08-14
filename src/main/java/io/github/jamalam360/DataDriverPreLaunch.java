package io.github.jamalam360;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class DataDriverPreLaunch implements PreLaunchEntrypoint {
    public static final String ENTRYPOINT = DataDriverModInit.MOD_ID + ":register";

    @Override
    public void onPreLaunch() {
        for (DataDriverEntrypoint entrypoint : FabricLoader.getInstance().getEntrypoints(ENTRYPOINT, DataDriverEntrypoint.class)) {
            entrypoint.register();
        }
    }
}
