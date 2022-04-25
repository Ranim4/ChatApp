package formationUdemy;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket = serverSocket.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Writer output = new OutputStreamWriter(socket.getOutputStream());
        Frame chatFrame = new Frame("Server", output);

        String message;
        while((message = input.readLine()) != null) {
            chatFrame.appendMessage(message);
        }
        serverSocket.close();
        chatFrame.appendMessage("Connection lost!");
    }
}
