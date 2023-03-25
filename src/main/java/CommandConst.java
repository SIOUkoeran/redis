import store.MemoryStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandConst {

    private static List<String> commands = new ArrayList<>(
            Arrays.asList("ECHO", "PONG")
    );

    private static void defaultCommandParser(String command) {

    }

    public static String echo(String commands) {
        return "+" + commands + "\r\n";
    }

    public static String ping() {
        return "+" + commands.get(1);
    }

    public static void set(String key, String value) {
        MemoryStore.set(key, value);
    }

    public static String get(String key) {
        return MemoryStore.get(key);
    }
}
