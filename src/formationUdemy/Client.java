package formationUdemy;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.44.140.10", 3000);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Writer output = new OutputStreamWriter(socket.getOutputStream());
        Frame chatFrame = new Frame("Client", output);

        String message;
        while((message = input.readLine()) != null) {
            chatFrame.appendMessage(message);
        }
        socket.close();
        chatFrame.appendMessage("Connection lost!");

    }
}
