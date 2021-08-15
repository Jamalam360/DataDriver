package io.github.jamalam360.registration;

import io.github.jamalam360.DataDriverModInit;
import io.github.jamalam360.json.JsonHelper;
import io.github.jamalam360.json.ParsedObject;
import io.github.jamalam360.json.Parser;
import io.github.jamalam360.json.parser.EnchantmentParser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceImpl;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.lwjgl.system.CallbackI;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class DataDriverRegistry {
    private static final List<File> jsonFiles = new ArrayList<>();
    private static final HashMap<String, Parser<?>> PARSERS = new HashMap<>();

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

    @ApiStatus.Internal
    public static ParsedObject<?>[] afterRegistry() {
        ArrayList<ParsedObject<?>> array = new ArrayList<>();

        for (File file : jsonFiles) {
            String type = JsonHelper.getType(file);
            array.add(PARSERS.get(type).parse(JsonHelper.getStringFromFile(file)));
        }

        return array.toArray(new ParsedObject[0]);
    }

    static {
        PARSERS.put("enchantment", new EnchantmentParser());
    }
}
