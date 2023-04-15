import store.MemoryStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandConst {

    private static List<String> commands = new ArrayList<>(
            Arrays.asList("ECHO", "PONG")
    );

    public static String echo(String commands) {
        return "+" + commands + "\r\n";
    }

    public static String ping() {
        return "+" + commands.get(1);
    }

    public static void set(String key, String... valueOrOptions) {
        if (valueOrOptions.length == 1)
            MemoryStore.set(key, valueOrOptions[0]);
        else if (valueOrOptions.length == 3) {
            if (!valueOrOptions[1].equalsIgnoreCase("PX"))
                throw new UnsupportedOperationException("지원되지 않는 옵션입니다.");
            MemoryStore.set(key, valueOrOptions[0], valueOrOptions[2]);
        }else {
            throw new RuntimeException("INVALID SET COMMAND");
        }
    }

    public static String get(String key) {
        return MemoryStore.get(key);
    }
}
