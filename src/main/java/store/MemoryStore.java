package store;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore {

    private static final ConcurrentHashMap<String, Value> memoryStore
            =  new ConcurrentHashMap<String, Value>();

    public static void set(String key, String value) {
        memoryStore.put(key, new Value(value, null));
    }

    public static void set(String key, String value, String expiry){
        memoryStore.put(key, new Value(value, LocalDateTime.now(), expiry));
    }

    public static String get(String key) {
        if (!memoryStore.containsKey(key))
            return "(nil)";
        LocalDateTime now = LocalDateTime.now();
        Value value = memoryStore.get(key);
        LocalDateTime createdAt = value.getMills();
        if (createdAt == null)
            return memoryStore.get(key).getKey();
        String expiry = value.getExpiry();
        if (ChronoUnit.MILLIS.between(createdAt, now) >= Long.parseLong(expiry)) {
            memoryStore.remove(key);
            return "-1";
        }
        return memoryStore.get(key).getKey();
    }
}
