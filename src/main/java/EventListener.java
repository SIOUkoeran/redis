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
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1) {
                if (socket.isConnected()) {
                    outputStream.write(response.getBytes());
                    outputStream.flush();
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
}
