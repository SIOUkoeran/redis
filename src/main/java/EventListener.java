import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class EventListener extends Thread {

    private static final String response = "+PONG\r\n";

    private final Socket socket;

    public EventListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("listening");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1) {
                String result;
                if (socket.isConnected()) {
                    String s = toStringArray(buffer);
                    if (s.startsWith("*"))
                        result = CommandParser.commandParser(s);
                    else
                        result = CommandParser.justPing();
                    if (result.equals("+-1"))
                        socket.getOutputStream().write(("$-1\r\n").getBytes());
                    else
                        socket.getOutputStream().write((result + "\r\n").getBytes());
                    socket.getOutputStream().flush();
                    buffer = new byte[1024];
                }
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if (!socket.isClosed())
                    socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String toStringArray(byte[] buf) {
        return new String(buf);
    }
}
