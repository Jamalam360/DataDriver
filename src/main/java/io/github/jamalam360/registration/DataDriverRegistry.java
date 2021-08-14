package io.github.jamalam360.registration;

import io.github.jamalam360.DataDriverModInit;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class DataDriverRegistry {
    public static final List<File> jsonFiles = new ArrayList<>();

    public static void registerDirectory(Path path) {
        registerDirectory(path.toFile());
    }

    public static void registerDirectory(String path) {
        registerDirectory(new File(path));
    }

    public static void registerDirectory(File file) {
        if (!file.isDirectory()) {
            jsonFiles.add(file);
        } else {
            File[] files = file.listFiles();

            if(files != null && files.length != 0) {
                if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                    DataDriverModInit.LOGGER.warn("The directory you registered was empty (this message only appears in a development environment");
                }

                jsonFiles.addAll(Arrays.asList(files));
            }
        }
    }
}
