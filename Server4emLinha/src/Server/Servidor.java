package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket servidorSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void iniciarServidor() {
        Thread serverThread = new Thread(() -> {
            try {
                servidorSocket = new ServerSocket(8888);
                System.out.println("Servidor iniciado. Aguardando conexão do cliente...");
                while (true) {
                    socket = servidorSocket.accept();
                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();
    }
}
