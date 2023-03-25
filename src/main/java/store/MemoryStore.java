package store;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore {

    private static final ConcurrentHashMap<String, String> memoryStore
            =  new ConcurrentHashMap<String, String>();

    public static void set(String key, String value) {
        memoryStore.put(key, value);
    }

    public static String get(String key) {
        if (!memoryStore.containsKey(key))
            return "(nil)";
        return memoryStore.get(key);
    }
}
