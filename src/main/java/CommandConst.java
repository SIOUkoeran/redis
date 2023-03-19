import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandConst {

    private static List<String> commands = new ArrayList<>(
            Arrays.asList("ECHO", "PING")
    );

    private static void defaultCommandParser(String command) {

    }

    public static String echo(List<String> commands) {
        return "+" + commands.get(1);
    }

    public static String ping() {
        return "+" + commands.get(1);
    }
}
