package io.github.jamalam360.json;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.jamalam360.DataDriverModInit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class JsonHelper {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final JsonParser PARSER = new JsonParser();

    public static String getStringFromFile(File file) {
        try {
            //noinspection UnstableApiUsage
            return Files.toString(file, Charset.defaultCharset());
        } catch (IOException ignored) {
            return "";
        }
    }

    public static JsonObject getAsJsonObject(String json) {
        return PARSER.parse(json).getAsJsonObject();
    }

    public static String getType(File file) {
        return getAsJsonObject(getStringFromFile(file)).get("type").getAsString();
    }

    public static Method getMethod(String input, Class<?>... argumentTypes) {
        String[] inputArr = input.split("::");

        try {
            Class<?> methodClass = Thread.currentThread().getContextClassLoader().loadClass(inputArr[0]);
            return methodClass.getDeclaredMethod(inputArr[1], argumentTypes);
        } catch (Exception e) {
            DataDriverModInit.LOGGER.warn("Couldn't find method '%s'".formatted(input));
            DataDriverModInit.LOGGER.warn(e.getMessage());
            return null;
        }
    }
}
