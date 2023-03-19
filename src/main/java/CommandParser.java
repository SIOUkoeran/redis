import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommandParser {

    private static final String response = "+PONG\r\n";


    private static List<String> bulkCommandParser(String command) {
        return Arrays.stream(command.split(Pattern.quote("\\" + "r" + "\\" + "n")))
                .filter(c ->
                        !c.trim().isEmpty() && !c.startsWith("*") &&
                                !c.startsWith("$")
                )
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
    }

    public static String commandParser(String command) {
        List<String> commandList = bulkCommandParser(command);
        String command0 = commandList.get(0);
        if ("ECHO".equalsIgnoreCase(command0))
            return CommandConst.echo(commandList.get(1));
        else if ("PING".equalsIgnoreCase(command0))
            return CommandConst.ping();
        else return command0;
    }

    public static String justPing() {
        return CommandConst.ping();
    }
}
