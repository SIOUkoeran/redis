import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerPingResponse {

    private static final String response = "+PONG\r\n";
    public static void response(Socket socket) {

        try{
        DataOutputStream outputStream =
                new DataOutputStream(socket.getOutputStream());
        outputStream.writeBytes(response);
        outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void multipleResponse(Socket socket) {
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
            }
    }
}
