package io.github.jamalam360.json;

import net.minecraft.util.Identifier;

public class ParsedObject<T> {
    private T entry;
    private Identifier id;

    public ParsedObject(T entry, String id) {
        this.entry = entry;
        this.id = new Identifier(id);
    }

    public T get() {
        return this.entry;
    }

    public Identifier getId() {
        return this.id;
    }
}
