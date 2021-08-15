package io.github.jamalam360.test;

import io.github.jamalam360.DataDriverEntrypoint;
import io.github.jamalam360.DataDriverModInit;
import io.github.jamalam360.registration.DataDriverRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import java.io.File;

public class DataDriverModTest implements DataDriverEntrypoint {
    @Override
    public void register() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DataDriverModInit.LOGGER.info("DataDriver is registering test entries (only happens in a development environment)");
            FabricLoader.getInstance().getModContainer(DataDriverModInit.MOD_ID).ifPresent(modContainer -> DataDriverRegistry.registerDirectory(modContainer.getPath("data/datadriver/datadriver/test_enchantment.json")));
        }
    }

    public static void targetDamagedTest(LivingEntity user, Entity target, Integer level) {
        System.out.println("TEST");
    }
}
