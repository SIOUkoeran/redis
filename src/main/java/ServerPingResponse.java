import java.io.*;
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
            while (inputStream.read() != -1) {
                if (!socket.isClosed()) {
                    outputStream.write(response.getBytes());
                    outputStream.flush();
                }else break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
