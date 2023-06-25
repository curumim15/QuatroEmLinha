package Client;

import Controllers.JogoTela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JogoTela jogoTela;

    public Client(JogoTela jogoTela){
        this.jogoTela = jogoTela;
    }

    public void conectarAoServidor(String ip) {
        Thread serverThread = new Thread(() -> {
            try {
                socket = new Socket(ip, 8888);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        serverThread.start();

    }
    public void enviarMensagem(String mensagem) {
        if (out != null) {
            out.println(mensagem);
        }
    }
    private void receberMensagens() {
        try {
            String mensagem;
            while ((mensagem = in.readLine()) != null) {
                System.out.println(mensagem);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}