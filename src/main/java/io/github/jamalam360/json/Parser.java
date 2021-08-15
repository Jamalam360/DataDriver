package io.github.jamalam360.json;

public interface Parser<T> {
    ParsedObject<T> parse(String json);
}
