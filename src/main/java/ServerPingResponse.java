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

        try (InputStream inputStream = socket.getInputStream()){
            BufferedReader bufferedReader
                    = new BufferedReader(
                        new InputStreamReader(inputStream)
            );
            BufferedWriter bufferedWriter =
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())
                    );
            String line = null;
            System.out.println("Here");
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(response);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
