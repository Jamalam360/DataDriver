package io.github.jamalam360.test;

import io.github.jamalam360.DataDriverEntrypoint;
import io.github.jamalam360.DataDriverModInit;
import net.fabricmc.loader.api.FabricLoader;

public class DataDriverModTest implements DataDriverEntrypoint {
    @Override
    public void register() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DataDriverModInit.LOGGER.info("DataDriver is registering test entries (only happens in a development environment)");
        }
    }
}
